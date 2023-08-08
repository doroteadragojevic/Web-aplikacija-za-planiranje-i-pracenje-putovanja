package fer.hr.zavrsni.dto;

import fer.hr.zavrsni.domain.Drzava;
import jakarta.validation.constraints.NotNull;

public class DrzavaDTO {

	@NotNull
	private String ISOOznaka;

	@NotNull(message = "Naziv drzave ne smije biti null")
	private String nazivDrzave;

	public DrzavaDTO(String ISOOznaka, String nazivDrzave) {
		this.ISOOznaka = ISOOznaka;
		this.nazivDrzave = nazivDrzave;
	}

	public String getISOOznaka() {
		return ISOOznaka;
	}

	public void setISOOznaka(String iSOOznaka) {
		ISOOznaka = iSOOznaka;
	}

	public String getNazivDrzave() {
		return nazivDrzave;
	}

	public void setNazivDrzave(String nazivDrzave) {
		this.nazivDrzave = nazivDrzave;
	}

	public static DrzavaDTO toDto(Drzava d) {
		return new DrzavaDTO(d.getISOOznaka(), d.getNazivDrzave());
	}

}
