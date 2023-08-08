package fer.hr.zavrsni.domain;

import java.sql.Date;

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
	
	@NotNull(message = "Datum prijevoza ne smije biti null")
	private Date datumPrijevoza;
	
	@NotNull
	@ManyToOne
	private Mjesto odMjesta;
	
	@NotNull
	@ManyToOne
	private Mjesto doMjesta;

	@Enumerated(EnumType.STRING)
	VrstaPrijevoza vrstaPrijevoza;

	public Prijevoz(@NotNull(message = "Naziv prijevoza ne smije biti null") String nazivPrijevoza,
			@NotNull(message = "Cijena prijevoza ne smije biti null") Long cost,
			VrstaPrijevoza vrstaPrijevoza, Date datumPrijevoza, Mjesto odMjesta, Mjesto doMjesta) {
		super();
		this.nazivPrijevoza = nazivPrijevoza;
		this.cost = cost;
		this.vrstaPrijevoza = vrstaPrijevoza;
		this.datumPrijevoza = datumPrijevoza;
		this.odMjesta = odMjesta;
		this.doMjesta = doMjesta;
	}
	
	public Prijevoz() {}

	public Date getDatumPrijevoza() {
		return datumPrijevoza;
	}

	public void setDatumPrijevoza(Date datumPrijevoza) {
		this.datumPrijevoza = datumPrijevoza;
	}

	public Mjesto getOdMjesta() {
		return odMjesta;
	}

	public void setOdMjesta(Mjesto odMjesta) {
		this.odMjesta = odMjesta;
	}

	public Mjesto getDoMjesta() {
		return doMjesta;
	}

	public void setDoMjesta(Mjesto doMjesta) {
		this.doMjesta = doMjesta;
	}

	public Long getIdPrijevoza() {
		return idPrijevoza;
	}

	public void setIdPrijevoza(Long idPrijevoza) {
		this.idPrijevoza = idPrijevoza;
	}

	public String getNazivPrijevoza() {
		return nazivPrijevoza;
	}

	public void setNazivPrijevoza(String nazivPrijevoza) {
		this.nazivPrijevoza = nazivPrijevoza;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public VrstaPrijevoza getVrstaPrijevoza() {
		return vrstaPrijevoza;
	}

	public void setVrstaPrijevoza(VrstaPrijevoza vrstaPrijevoza) {
		this.vrstaPrijevoza = vrstaPrijevoza;
	}
	
	

}
