package fer.hr.zavrsni.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor
@Data
public class Prijevoz {

	@Id
	@GeneratedValue
	private Long idPrijevoza;

	@NotNull(message = "Naziv prijevoza ne smije biti null")
	private String nazivPrijevoza;

	@NotNull(message = "Cijena prijevoza ne smije biti null")
	private Long cost;

	@Enumerated(EnumType.STRING)
	VrstaPrijevoza vrstaPrijevoza;

	public Prijevoz(@NotNull(message = "Naziv prijevoza ne smije biti null") String nazivPrijevoza,
			@NotNull(message = "Cijena prijevoza ne smije biti null") Long cost,
			VrstaPrijevoza vrstaPrijevoza) {
		super();
		this.nazivPrijevoza = nazivPrijevoza;
		this.cost = cost;
		this.vrstaPrijevoza = vrstaPrijevoza;
	}

}
