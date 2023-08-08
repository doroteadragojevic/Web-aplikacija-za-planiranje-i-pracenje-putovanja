package fer.hr.zavrsni.dto;

import java.sql.Date;

import fer.hr.zavrsni.domain.Prijevoz;
import fer.hr.zavrsni.domain.VrstaPrijevoza;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class PrijevozDTO {

	private Long idPrijevoza;

	@NotNull(message = "Naziv prijevoza ne smije biti null")
	private String nazivPrijevoza;

	@NotNull(message = "Cijena prijevoza ne smije biti null")
	private Long cost;
	
	@NotNull
	private Date datumPrijevoza;

	@NotNull
	private Long idOd;
	@NotNull
	private Long idDo;

	private String nazivOd;
	private String nazivDo;

	@NotNull
	Long idVrstaPrijevoza;

	VrstaPrijevoza vrstaPrijevoza;

	public PrijevozDTO(Long idPrijevoza, @NotNull(message = "Naziv prijevoza ne smije biti null") String nazivPrijevoza,
			@NotNull(message = "Cijena prijevoza ne smije biti null") Long cost, VrstaPrijevoza vrstaPrijevoza,
			Long idOd, String nazivOd, Long idDo, String nazivDo, Date datumPrijevoza) {
		super();
		this.idPrijevoza = idPrijevoza;
		this.nazivPrijevoza = nazivPrijevoza;
		this.cost = cost;
		this.vrstaPrijevoza = vrstaPrijevoza;
		this.idOd = idOd;
		this.idDo = idDo;
		this.nazivDo = nazivDo;
		this.nazivOd = nazivOd;
		this.datumPrijevoza = datumPrijevoza;
	}

	public Long getIdOd() {
		return idOd;
	}

	public Date getDatumPrijevoza() {
		return datumPrijevoza;
	}

	public void setDatumPrijevoza(Date datumPrijevoza) {
		this.datumPrijevoza = datumPrijevoza;
	}

	public void setIdOd(Long idOd) {
		this.idOd = idOd;
	}

	public Long getIdDo() {
		return idDo;
	}

	public void setIdDo(Long idDo) {
		this.idDo = idDo;
	}

	public String getNazivOd() {
		return nazivOd;
	}

	public void setNazivOd(String nazivOd) {
		this.nazivOd = nazivOd;
	}

	public String getNazivDo() {
		return nazivDo;
	}

	public void setNazivDo(String nazivDo) {
		this.nazivDo = nazivDo;
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

	public Long getIdVrstaPrijevoza() {
		return idVrstaPrijevoza;
	}

	public void setIdVrstaPrijevoza(Long idVrstaPrijevoza) {
		this.idVrstaPrijevoza = idVrstaPrijevoza;
	}

	public static PrijevozDTO toDto(Prijevoz prijevoz) {
		return new PrijevozDTO(prijevoz.getIdPrijevoza(), prijevoz.getNazivPrijevoza(), prijevoz.getCost(),
				prijevoz.getVrstaPrijevoza(), prijevoz.getOdMjesta().getIdMjesta(),
				prijevoz.getOdMjesta().getNazivMjesta(), prijevoz.getDoMjesta().getIdMjesta(),
				prijevoz.getDoMjesta().getNazivMjesta(), prijevoz.getDatumPrijevoza());
	}

}
