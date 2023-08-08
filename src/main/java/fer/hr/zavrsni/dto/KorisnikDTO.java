package fer.hr.zavrsni.dto;

import java.util.Date;

import fer.hr.zavrsni.domain.Korisnik;

public class KorisnikDTO {

	Long id;
	private String username;
	private String lozinka;
	private String ime;
	private String prezime;
	private Date datumRodenja;
	private String email;

	public KorisnikDTO(Long idKorisnik, String username, String lozinka, String ime, String prezime, String email, Date datumRodenja) {
		this.username = username;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.datumRodenja = datumRodenja;
		this.id = idKorisnik;
	}

	public static KorisnikDTO toDto(Korisnik k) {
		return new KorisnikDTO(k.getIdKorisnik() ,k.getUsername(), k.getLozinka(), k.getIme(), k.getPrezime(), k.getEmail(),
				k.getDatumRodenja());
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDatumRodenja() {
		return datumRodenja;
	}

	public void setDatumRodenja(Date datumRodenja) {
		this.datumRodenja = datumRodenja;
	}

	public Long getId() {
		return this.id;
	}
}
