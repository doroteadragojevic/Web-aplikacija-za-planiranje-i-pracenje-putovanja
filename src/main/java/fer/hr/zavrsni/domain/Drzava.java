package fer.hr.zavrsni.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class Drzava {

	@Id
	private String ISOOznaka;

	@NotNull(message = "Naziv drzave ne smije biti null")
	private String nazivDrzave;

	public Drzava() {}
	
	public Drzava(String iSOOznaka, @NotNull(message = "Naziv drzave ne smije biti null") String nazivDrzave) {
		super();
		ISOOznaka = iSOOznaka;
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

}
