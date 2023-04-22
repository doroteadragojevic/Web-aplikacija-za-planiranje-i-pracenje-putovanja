package fer.hr.zavrsni.domain;

import java.util.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class Putovanje {

	
	@Id
	@GeneratedValue
	private Long idPutovanja;
	
	@NotNull(message="Naziv putovanja ne smije biti null")
	private String naziv;
	
	@NotNull(message="Datum pocetka putovanja ne smije biti null")
	private Date datumPocetak;
	
	@NotNull(message="Datum kraja putovanja ne smije biti null")
	private Date datumKraj;
	
	public Putovanje(String naziv, Date datumPocetak, Date datumKraj) {
		this.naziv =  naziv;
		this.datumPocetak = datumPocetak;
		this.datumKraj = datumKraj;
	}
	
	@OneToMany
	Set<Prijevoz> prijevozNaPutovanju = new HashSet<>();
	
	@ManyToMany
	Set<Smjestaj> smjestajNaPutovanju = new HashSet<>();
	
	@OneToMany
	Set<Aktivnost> aktivnostiNaPutovanju = new HashSet<>();
	
	public void dodajPrijevoz(Prijevoz prijevoz) {
		this.prijevozNaPutovanju.add(prijevoz);
	}
	
	public void dodajSmjestaj(Smjestaj smjestaj) {
		this.smjestajNaPutovanju.add(smjestaj);
	}
	
	public void dodajAktivnost(Aktivnost aktivnost) {
		this.aktivnostiNaPutovanju.add(aktivnost);
	}
	
}
