/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Patryk
 */
@Entity
@Table(name = "t_uzytkownik", catalog = "tomcat", schema = "dbo")
@NamedQueries({
	@NamedQuery(name = "TUzytkownik.findAll", query = "SELECT t FROM TUzytkownik t")})
public class TUzytkownik implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
   @Basic(optional = false)
   @NotNull
   @Column(name = "id")
	private Long id;
	@Lob
   @Column(name = "dane")
	private byte[] dane;
	@Size(max = 255)
   @Column(name = "opis")
	private String opis;
	@Basic(optional = false)
   @NotNull
   @Size(min = 1, max = 50)
   @Column(name = "imie")
	private String imie;
	@Basic(optional = false)
   @NotNull
   @Size(min = 1, max = 50)
   @Column(name = "nazwisko")
	private String nazwisko;

	public TUzytkownik() {
	}

	public TUzytkownik(Long id) {
		this.id = id;
	}

	public TUzytkownik(Long id, String imie, String nazwisko) {
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getDane() {
		return dane;
	}

	public void setDane(byte[] dane) {
		this.dane = dane;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TUzytkownik)) {
			return false;
		}
		TUzytkownik other = (TUzytkownik) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entity.TUzytkownik[ id=" + id + " ]";
	}
	
}
