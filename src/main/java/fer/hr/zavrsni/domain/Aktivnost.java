package fer.hr.zavrsni.domain;

import java.sql.Time;
import java.util.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor
@Data
public class Aktivnost {

	@Id
	@GeneratedValue
	private Long idAktivnost;

	@NotNull(message = "Naziv aktivnost ne smije biti null")
	private String nazivAktivnosti;

	@NotNull(message = "Datum odrzavanja aktivnosti ne smije biti null.")
	private Date datumOdrzavanja;

	@NotNull(message = "Vrijeme pocetka ne smije biti null")
	private Time vrijemePocetka;

	@NotNull(message = "Vrijeme zavrsetka ne smije biti null")
	private Time vrijemeZavrsetka;

	private Long cijena;

	private String URL;

	private String detalji;

	@ManyToOne
	private Mjesto mjesto;

	public Aktivnost(@NotNull(message = "Naziv aktivnost ne smije biti null") String nazivAktivnosti,
			@NotNull(message = "Datum odrzavanja aktivnosti ne smije biti null.") Date datumOdrzavanja,
			@NotNull(message = "Vrijeme pocetka ne smije biti null") Time vrijemePocetka,
			@NotNull(message = "Vrijeme zavrsetka ne smije biti null") Time vrijemeZavrsetka, Long cijena, String uRL,
			String detalji, Mjesto mjesto) {
		super();
		this.nazivAktivnosti = nazivAktivnosti;
		this.datumOdrzavanja = datumOdrzavanja;
		this.vrijemePocetka = vrijemePocetka;
		this.vrijemeZavrsetka = vrijemeZavrsetka;
		this.cijena = cijena;
		URL = uRL;
		this.detalji = detalji;
		this.mjesto = mjesto;
	}

}
