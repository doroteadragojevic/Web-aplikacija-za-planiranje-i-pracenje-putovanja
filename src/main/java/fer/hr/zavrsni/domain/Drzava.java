package fer.hr.zavrsni.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class Drzava {

	@Id
	private Long ISOOznaka;

	@NotNull(message = "Naziv drzave ne smije biti null")
	private String nazivDrzave;

	public Drzava(Long iSOOznaka, @NotNull(message = "Naziv drzave ne smije biti null") String nazivDrzave) {
		super();
		ISOOznaka = iSOOznaka;
		this.nazivDrzave = nazivDrzave;
	}

}
