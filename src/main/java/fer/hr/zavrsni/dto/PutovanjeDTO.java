package fer.hr.zavrsni.dto;

import java.util.Date;

import fer.hr.zavrsni.domain.Putovanje;
import jakarta.validation.constraints.NotNull;

public class PutovanjeDTO {

	private Long idPutovanja;

	@NotNull(message = "Naziv putovanja ne smije biti null")
	private String naziv;

	@NotNull(message = "Datum pocetka putovanja ne smije biti null")
	private Date datumPocetak;

	@NotNull(message = "Datum kraja putovanja ne smije biti null")
	private Date datumKraj;
	
	@NotNull
	private Long idMjesto;
	private String nazivMjesta;

	public PutovanjeDTO(Long long1, @NotNull(message = "Naziv putovanja ne smije biti null") String naziv,
			@NotNull(message = "Datum pocetka putovanja ne smije biti null") Date datumPocetak,
			@NotNull(message = "Datum kraja putovanja ne smije biti null") Date datumKraj, Long idMjesta, String nazivMjesta) {
		super();
		this.idPutovanja = long1;
		this.naziv = naziv;
		this.datumPocetak = datumPocetak;
		this.datumKraj = datumKraj;
		this.idMjesto = idMjesta;
		this.nazivMjesta = nazivMjesta;
	}

	public static PutovanjeDTO toDTO(Putovanje p) {
		return new PutovanjeDTO(p.getIdPutovanja(),p.getNaziv(), p.getDatumPocetak(), p.getDatumKraj(), p.getMjesto().getIdMjesta(), p.getMjesto().getNazivMjesta());
	}

	public Long getIdPutovanja() {
		return idPutovanja;
	}

	public void setIdPutovanja(Long idPutovanja) {
		this.idPutovanja = idPutovanja;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Date getDatumPocetak() {
		return datumPocetak;
	}

	public void setDatumPocetak(Date datumPocetak) {
		this.datumPocetak = datumPocetak;
	}

	public Date getDatumKraj() {
		return datumKraj;
	}

	public void setDatumKraj(Date datumKraj) {
		this.datumKraj = datumKraj;
	}

	public Long getIdMjesto() {
		return idMjesto;
	}

	public void setIdMjesto(Long idMjesta) {
		this.idMjesto = idMjesta;
	}

	public String getNazivMjesta() {
		return nazivMjesta;
	}

	public void setNazivMjesta(String nazivMjesta) {
		this.nazivMjesta = nazivMjesta;
	}
	
	

}
