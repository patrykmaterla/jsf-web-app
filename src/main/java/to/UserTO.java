/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package to;

/**
 *
 * @author Patryk
 */
public class UserTO {
	
	Long id = 0l;
	String imie = "";
	String nazwisko = "";
	boolean edited = false;

	public UserTO() {
	}
	
	public UserTO(Long id, String imie, String nazwisko, boolean edited) {
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.edited = edited;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isEdited() {
		return edited;
	}

	public void setEdited(boolean edited) {
		this.edited = edited;
	}

}
