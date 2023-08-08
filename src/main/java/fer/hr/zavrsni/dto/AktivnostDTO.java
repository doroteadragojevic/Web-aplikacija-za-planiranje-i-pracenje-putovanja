package fer.hr.zavrsni.dto;

import java.time.LocalTime;
import java.util.Date;

import fer.hr.zavrsni.domain.Aktivnost;
import fer.hr.zavrsni.domain.Mjesto;
import jakarta.validation.constraints.NotNull;

public class AktivnostDTO {
	private Long idAktivnost;

	@NotNull(message = "Naziv aktivnost ne smije biti null")
	private String nazivAktivnosti;

	@NotNull(message = "Datum odrzavanja aktivnosti ne smije biti null.")
	private Date datumOdrzavanja;

	@NotNull(message = "Vrijeme pocetka ne smije biti null")
	private LocalTime vrijemePocetka;

	@NotNull(message = "Vrijeme zavrsetka ne smije biti null")
	private LocalTime vrijemeZavrsetka;

	private Long cijena;

	private String URL;

	private String detalji;
	

	public AktivnostDTO(Long idAktivnost, @NotNull(message = "Naziv aktivnost ne smije biti null") String nazivAktivnosti,
			@NotNull(message = "Datum odrzavanja aktivnosti ne smije biti null.") Date datumOdrzavanja,
			@NotNull(message = "Vrijeme pocetka ne smije biti null") LocalTime vrijemePocetka,
			@NotNull(message = "Vrijeme zavrsetka ne smije biti null") LocalTime vrijemeZavrsetka, Long cijena, String uRL,
			String detalji) {
		super();
		this.idAktivnost = idAktivnost;
		this.nazivAktivnosti = nazivAktivnosti;
		this.datumOdrzavanja = datumOdrzavanja;
		this.vrijemePocetka = vrijemePocetka;
		this.vrijemeZavrsetka = vrijemeZavrsetka;
		this.cijena = cijena;
		URL = uRL;
		this.detalji = detalji;
	}

	public Long getIdAktivnost() {
		return idAktivnost;
	}

	public void setIdAktivnost(Long idAktivnost) {
		this.idAktivnost = idAktivnost;
	}

	public String getNazivAktivnosti() {
		return nazivAktivnosti;
	}

	public void setNazivAktivnosti(String nazivAktivnosti) {
		this.nazivAktivnosti = nazivAktivnosti;
	}

	public Date getDatumOdrzavanja() {
		return datumOdrzavanja;
	}

	public void setDatumOdrzavanja(Date datumOdrzavanja) {
		this.datumOdrzavanja = datumOdrzavanja;
	}

	public LocalTime getVrijemePocetka() {
		return vrijemePocetka;
	}

	public void setVrijemePocetka(LocalTime vrijemePocetka) {
		this.vrijemePocetka = vrijemePocetka;
	}

	public LocalTime getVrijemeZavrsetka() {
		return vrijemeZavrsetka;
	}

	public void setVrijemeZavrsetka(LocalTime vrijemeZavrsetka) {
		this.vrijemeZavrsetka = vrijemeZavrsetka;
	}

	public Long getCijena() {
		return cijena;
	}

	public void setCijena(Long cijena) {
		this.cijena = cijena;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getDetalji() {
		return detalji;
	}

	public void setDetalji(String detalji) {
		this.detalji = detalji;
	}
	
	public static AktivnostDTO toDto(Aktivnost aktivnost) {
		return new AktivnostDTO(aktivnost.getIdAktivnost(), aktivnost.getNazivAktivnosti(), aktivnost.getDatumOdrzavanja(), aktivnost.getVrijemePocetka(), aktivnost.getVrijemeZavrsetka(), aktivnost.getCijena(), aktivnost.getURL(), aktivnost.getDetalji());
	}


}
