package fer.hr.zavrsni.dto;

import fer.hr.zavrsni.domain.Mjesto;
import jakarta.validation.constraints.NotNull;

public class MjestoDTO {

	private Long idMjesta;

	@NotNull(message = "PBR mjesta ne smije biti null")
	private Integer pbr;

	@NotNull(message = "Naziv mjesta ne smije biti null")
	private String nazivMjesta;
	
	@NotNull
	private String ISOOznakaDrzave;

	public MjestoDTO(Long idMjesta, @NotNull(message = "PBR mjesta ne smije biti null") Integer pbr,
			@NotNull(message = "Naziv mjesta ne smije biti null") String nazivMjesta, String ISOOznakaDrzave) {
		super();
		this.idMjesta = idMjesta;
		this.pbr = pbr;
		this.nazivMjesta = nazivMjesta;
		this.ISOOznakaDrzave = ISOOznakaDrzave;
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

	public String getISOOznakaDrzave() {
		return ISOOznakaDrzave;
	}

	public void setISOOznakaDrzave(String iSOOznakaDrzave) {
		ISOOznakaDrzave = iSOOznakaDrzave;
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

	public static MjestoDTO toDto(Mjesto mjesto) {
		return new MjestoDTO(mjesto.getIdMjesta(), mjesto.getPbr(), mjesto.getNazivMjesta(), mjesto.getDrzava().getISOOznaka());
	}

}
