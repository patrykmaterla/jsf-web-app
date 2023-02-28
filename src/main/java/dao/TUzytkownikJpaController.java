/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import entity.TUzytkownik;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author Patryk
 */
public class TUzytkownikJpaController implements Serializable {

	public TUzytkownikJpaController(UserTransaction utx, EntityManagerFactory emf) {
		this.utx = utx;
		this.emf = emf;
	}
	private UserTransaction utx = null;
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(TUzytkownik TUzytkownik) throws PreexistingEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			em.persist(TUzytkownik);
			utx.commit();
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
			}
			if (findTUzytkownik(TUzytkownik.getId()) != null) {
				throw new PreexistingEntityException("TUzytkownik " + TUzytkownik + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(TUzytkownik TUzytkownik) throws NonexistentEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			TUzytkownik = em.merge(TUzytkownik);
			utx.commit();
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
			}
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = TUzytkownik.getId();
				if (findTUzytkownik(id) == null) {
					throw new NonexistentEntityException("The tUzytkownik with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			TUzytkownik TUzytkownik;
			try {
				TUzytkownik = em.getReference(TUzytkownik.class, id);
				TUzytkownik.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The TUzytkownik with id " + id + " no longer exists.", enfe);
			}
			em.remove(TUzytkownik);
			utx.commit();
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<TUzytkownik> findTUzytkownikEntities() {
		return findTUzytkownikEntities(true, -1, -1);
	}

	public List<TUzytkownik> findTUzytkownikEntities(int maxResults, int firstResult) {
		return findTUzytkownikEntities(false, maxResults, firstResult);
	}

	private List<TUzytkownik> findTUzytkownikEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(TUzytkownik.class));
			Query q = em.createQuery(cq);
			if (!all) {
				q.setMaxResults(maxResults);
				q.setFirstResult(firstResult);
			}
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	public TUzytkownik findTUzytkownik(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(TUzytkownik.class, id);
		} finally {
			em.close();
		}
	}

	public int getTUzytkownikCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<TUzytkownik> rt = cq.from(TUzytkownik.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
