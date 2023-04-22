package fer.hr.zavrsni.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class Mjesto {

	@Id
	@GeneratedValue
	private Long idMjesta;

	@NotNull(message = "PBR mjesta ne smije biti null")
	private Integer pbr;

	@NotNull(message = "Naziv mjesta ne smije biti null")
	private String nazivMjesta;

	@ManyToOne
	@NotNull(message = "Drzava ne smije biti null")
	Drzava drzava;

	public Mjesto(@NotNull(message = "PBR mjesta ne smije biti null") Integer pbr,
			@NotNull(message = "Naziv mjesta ne smije biti null") String nazivMjesta, Drzava drzava) {
		super();
		this.pbr = pbr;
		this.nazivMjesta = nazivMjesta;
		this.drzava = drzava;
	}

}
