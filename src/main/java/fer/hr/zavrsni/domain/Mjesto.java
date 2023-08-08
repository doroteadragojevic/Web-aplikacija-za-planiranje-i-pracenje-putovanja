package fer.hr.zavrsni.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Mjesto")
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

	public Mjesto() {}
	public Mjesto(@NotNull(message = "PBR mjesta ne smije biti null") Integer pbr,
			@NotNull(message = "Naziv mjesta ne smije biti null") String nazivMjesta, Drzava drzava) {
		super();
		this.pbr = pbr;
		this.nazivMjesta = nazivMjesta;
		this.drzava = drzava;
	}

	public Long getIdMjesta() {
		return idMjesta;
	}

	public void setIdMjesta(Long idMjesta) {
		this.idMjesta = idMjesta;
	}

	public Integer getPbr() {
		return pbr;
	}

	public void setPbr(Integer pbr) {
		this.pbr = pbr;
	}

	public String getNazivMjesta() {
		return nazivMjesta;
	}

	public void setNazivMjesta(String nazivMjesta) {
		this.nazivMjesta = nazivMjesta;
	}

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}
	
	

}
