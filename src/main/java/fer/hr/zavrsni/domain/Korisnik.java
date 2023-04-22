package fer.hr.zavrsni.domain;

import java.util.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor
@Data
public class Korisnik {

	@Id
	@GeneratedValue
	private Long idKorisnik;

	@NotNull(message = "Username should not be null.")
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

}
