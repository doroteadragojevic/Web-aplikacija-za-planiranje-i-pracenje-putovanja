package fer.hr.zavrsni.domain;

import java.util.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class Putovanje {

	@Id
	@GeneratedValue
	private Long idPutovanja;

	@NotNull(message = "Naziv putovanja ne smije biti null")
	private String naziv;

	@NotNull(message = "Datum pocetka putovanja ne smije biti null")
	private Date datumPocetak;

	@NotNull(message = "Datum kraja putovanja ne smije biti null")
	private Date datumKraj;
	
	@NotNull(message="Mjesto putovanja ne smije biti null")
	@ManyToOne
	private Mjesto mjesto;

	public Putovanje(String naziv, Date datumPocetak, Date datumKraj, Mjesto mjesto) {
		this.naziv = naziv;
		this.datumPocetak = datumPocetak;
		this.datumKraj = datumKraj;
		this.mjesto = mjesto;
	}

	public Mjesto getMjesto() {
		return mjesto;
	}

	public void setMjesto(Mjesto mjesto) {
		this.mjesto = mjesto;
	}

	@OneToMany
	Set<Prijevoz> prijevozNaPutovanju = new HashSet<>();

	@ManyToMany
	Set<Smjestaj> smjestajNaPutovanju = new HashSet<>();

	@OneToMany
	Set<Aktivnost> aktivnostiNaPutovanju = new HashSet<>();

	public void dodajPrijevoz(Prijevoz prijevoz) {
		this.prijevozNaPutovanju.add(prijevoz);
	}

	public void dodajSmjestaj(Smjestaj smjestaj) {
		this.smjestajNaPutovanju.add(smjestaj);
	}

	public void dodajAktivnost(Aktivnost aktivnost) {
		this.aktivnostiNaPutovanju.add(aktivnost);
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

	public Set<Prijevoz> getPrijevozNaPutovanju() {
		return prijevozNaPutovanju;
	}

	public void setPrijevozNaPutovanju(Set<Prijevoz> prijevozNaPutovanju) {
		this.prijevozNaPutovanju = prijevozNaPutovanju;
	}

	public Set<Smjestaj> getSmjestajNaPutovanju() {
		return smjestajNaPutovanju;
	}

	public void setSmjestajNaPutovanju(Set<Smjestaj> smjestajNaPutovanju) {
		this.smjestajNaPutovanju = smjestajNaPutovanju;
	}

	public Set<Aktivnost> getAktivnostiNaPutovanju() {
		return aktivnostiNaPutovanju;
	}

	public void setAktivnostiNaPutovanju(Set<Aktivnost> aktivnostiNaPutovanju) {
		this.aktivnostiNaPutovanju = aktivnostiNaPutovanju;
	}

	public void ukloniAktivnost(Aktivnost s) {
		this.aktivnostiNaPutovanju.remove(s);
	}
	public Putovanje() {}

	public void ukloniSmjestaj(Smjestaj s) {
		this.smjestajNaPutovanju.remove(s);
		
	}

	public void ukloniPrijevoz(Prijevoz p) {
		this.prijevozNaPutovanju.remove(p);
		
	}
}
