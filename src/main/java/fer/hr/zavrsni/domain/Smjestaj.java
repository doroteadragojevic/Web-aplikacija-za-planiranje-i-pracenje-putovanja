package fer.hr.zavrsni.domain;

import java.util.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor
@Data
public class Smjestaj {

	@Id
	@GeneratedValue
	private Long idSmjestaja;

	@NotNull(message = "Naziv smjestaja ne smije biti null")
	private String nazivSmjestaja;

	@NotNull(message = "Cijena smjestaja ne smije biti null")
	private Long cijena;

	@NotNull(message = "Datum prijave ne smije biti null")
	private Date datumPrijave;

	@NotNull(message = "Datum odjave ne smije biti null")
	private Date datumOdjave;

	@NotNull(message = "Adresa ne smije biti null")
	private String adresa;

	@NotNull(message = "Broj mobitela ne smije biti null")
	private String brMobitel;

	@NotNull(message = "URL ne smije biti null")
	private String URL;

	@Enumerated(EnumType.STRING)
	VrstaSmjestaja vrstaSmjestaja;

	@ManyToOne
	Mjesto mjesto;

	public Smjestaj(@NotNull(message = "Naziv smjestaja ne smije biti null") String nazivSmjestaja,
			@NotNull(message = "Cijena smjestaja ne smije biti null") Long cijena,
			@NotNull(message = "Datum prijave ne smije biti null") Date datumPrijave,
			@NotNull(message = "Datum odjave ne smije biti null") Date datumOdjave,
			@NotNull(message = "Adresa ne smije biti null") String adresa,
			@NotNull(message = "Broj mobitela ne smije biti null") String brMobitel,
			@NotNull(message = "URL ne smije biti null") String uRL, VrstaSmjestaja vrstaSmjestaja, Mjesto mjesto) {
		super();
		this.nazivSmjestaja = nazivSmjestaja;
		this.cijena = cijena;
		this.datumPrijave = datumPrijave;
		this.datumOdjave = datumOdjave;
		this.adresa = adresa;
		this.brMobitel = brMobitel;
		URL = uRL;
		this.vrstaSmjestaja = vrstaSmjestaja;
		this.mjesto = mjesto;
	}

}
