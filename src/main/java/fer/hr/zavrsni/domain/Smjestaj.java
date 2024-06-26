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


	public Smjestaj(@NotNull(message = "Naziv smjestaja ne smije biti null") String nazivSmjestaja,
			@NotNull(message = "Cijena smjestaja ne smije biti null") Long cijena,
			@NotNull(message = "Datum prijave ne smije biti null") Date datumPrijave,
			@NotNull(message = "Datum odjave ne smije biti null") Date datumOdjave,
			@NotNull(message = "Adresa ne smije biti null") String adresa,
			@NotNull(message = "Broj mobitela ne smije biti null") String brMobitel,
			@NotNull(message = "URL ne smije biti null") String uRL, VrstaSmjestaja vrstaSmjestaja) {
		super();
		this.nazivSmjestaja = nazivSmjestaja;
		this.cijena = cijena;
		this.datumPrijave = datumPrijave;
		this.datumOdjave = datumOdjave;
		this.adresa = adresa;
		this.brMobitel = brMobitel;
		URL = uRL;
		this.vrstaSmjestaja = vrstaSmjestaja;
	}
	
	public Smjestaj() {}

	public Long getIdSmjestaja() {
		return idSmjestaja;
	}

	public void setIdSmjestaja(Long idSmjestaja) {
		this.idSmjestaja = idSmjestaja;
	}

	public String getNazivSmjestaja() {
		return nazivSmjestaja;
	}

	public void setNazivSmjestaja(String nazivSmjestaja) {
		this.nazivSmjestaja = nazivSmjestaja;
	}

	public Long getCijena() {
		return cijena;
	}

	public void setCijena(Long cijena) {
		this.cijena = cijena;
	}

	public Date getDatumPrijave() {
		return datumPrijave;
	}

	public void setDatumPrijave(Date datumPrijave) {
		this.datumPrijave = datumPrijave;
	}

	public Date getDatumOdjave() {
		return datumOdjave;
	}

	public void setDatumOdjave(Date datumOdjave) {
		this.datumOdjave = datumOdjave;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrMobitel() {
		return brMobitel;
	}

	public void setBrMobitel(String brMobitel) {
		this.brMobitel = brMobitel;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public VrstaSmjestaja getVrstaSmjestaja() {
		return vrstaSmjestaja;
	}

	public void setVrstaSmjestaja(VrstaSmjestaja vrstaSmjestaja) {
		this.vrstaSmjestaja = vrstaSmjestaja;
	}

}
