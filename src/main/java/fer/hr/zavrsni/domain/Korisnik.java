package fer.hr.zavrsni.domain;

import java.util.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
public class Korisnik {

	@Id
	@GeneratedValue
	private Long idKorisnik;

	@NotNull(message = "Username should not be null.")
	@Getter
	private String username;

	@NotNull(message = "Lozinka should not be null.")
	private String lozinka;

	@NotNull(message = "Ime should not be null")
	private String ime;

	@NotNull(message = "Prezime should not be null")
	private String prezime;

	private Date datumRodenja;

	private String email;

	@OneToMany
	private Set<Putovanje> putovanja = new HashSet<>();
	
	public Korisnik() {}

	public Korisnik(String username, String lozinka, String ime, String prezime, Date datumRodenja, String email) {
		this.username = username;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodenja = datumRodenja;
		this.email = email;
	}

	public void dodajPutovanjeKorisniku(Putovanje p) {
		this.putovanja.add(p);
	}

	public Long getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(Long idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Date getDatumRodenja() {
		return datumRodenja;
	}

	public void setDatumRodenja(Date datumRodenja) {
		this.datumRodenja = datumRodenja;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Putovanje> getPutovanja() {
		return putovanja;
	}

	public void setPutovanja(Set<Putovanje> putovanja) {
		this.putovanja = putovanja;
	}

	

}
