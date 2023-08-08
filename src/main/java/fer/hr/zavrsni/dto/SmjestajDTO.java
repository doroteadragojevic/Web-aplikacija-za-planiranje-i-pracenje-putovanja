package fer.hr.zavrsni.dto;

import java.util.Date;

import fer.hr.zavrsni.domain.Smjestaj;
import fer.hr.zavrsni.domain.VrstaSmjestaja;
import jakarta.validation.constraints.NotNull;

public class SmjestajDTO {

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
	@NotNull
	private Long idVrsteSmjestaja;

	private VrstaSmjestaja vs;

	public SmjestajDTO(Long id, @NotNull(message = "Naziv smjestaja ne smije biti null") String nazivSmjestaja,
			@NotNull(message = "Cijena smjestaja ne smije biti null") Long cijena,
			@NotNull(message = "Datum prijave ne smije biti null") Date datumPrijave,
			@NotNull(message = "Datum odjave ne smije biti null") Date datumOdjave,
			@NotNull(message = "Adresa ne smije biti null") String adresa,
			@NotNull(message = "Broj mobitela ne smije biti null") String brMobitel,
			@NotNull(message = "URL ne smije biti null") String uRL, VrstaSmjestaja vs) {
		super();
		this.idSmjestaja = id;
		this.nazivSmjestaja = nazivSmjestaja;
		this.cijena = cijena;
		this.datumPrijave = datumPrijave;
		this.datumOdjave = datumOdjave;
		this.adresa = adresa;
		this.brMobitel = brMobitel;
		URL = uRL;
		this.vs = vs;
	}

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

	public Long getIdVrsteSmjestaja() {
		return idVrsteSmjestaja;
	}

	public void setIdVrsteSmjestaja(Long idVrsteSmjestaja) {
		this.idVrsteSmjestaja = idVrsteSmjestaja;
	}

	public VrstaSmjestaja getVs() {
		return vs;
	}

	public void setVs(VrstaSmjestaja vs) {
		this.vs = vs;
	}

	public static SmjestajDTO toDto(Smjestaj smjestaj) {
		return new SmjestajDTO(smjestaj.getIdSmjestaja(), smjestaj.getNazivSmjestaja(), smjestaj.getCijena(),
				smjestaj.getDatumPrijave(), smjestaj.getDatumOdjave(), smjestaj.getAdresa(), smjestaj.getBrMobitel(),
				smjestaj.getURL(), smjestaj.getVrstaSmjestaja());
	}

}
