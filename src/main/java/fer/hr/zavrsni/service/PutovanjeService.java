package fer.hr.zavrsni.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fer.hr.zavrsni.dao.PutovanjeRepository;
import fer.hr.zavrsni.domain.Aktivnost;
import fer.hr.zavrsni.domain.Korisnik;
import fer.hr.zavrsni.domain.Mjesto;
import fer.hr.zavrsni.domain.Prijevoz;
import fer.hr.zavrsni.domain.Putovanje;
import fer.hr.zavrsni.domain.Smjestaj;
import fer.hr.zavrsni.dto.AktivnostDTO;
import fer.hr.zavrsni.dto.PrijevozDTO;
import fer.hr.zavrsni.dto.PutovanjeDTO;
import fer.hr.zavrsni.dto.SmjestajDTO;

@Service
public class PutovanjeService {

	@Autowired
	PutovanjeRepository putovanjeRepository;

	@Autowired
	KorisnikService korisnikService;

	@Autowired
	MjestoService mjestoService;

	public PutovanjeDTO kreirajPutovanje(Long idKorisnik, String naziv, Date datumPocetak, Date datumKraj,
			Long idMjesto) {
		if (datumKraj.before(datumPocetak))
			throw new IllegalArgumentException("Datum pocetka je nakon datuma kraja.");
		Mjesto m = mjestoService.getMjestoById(idMjesto);
		Putovanje p = new Putovanje(naziv, datumPocetak, datumKraj, m);
		putovanjeRepository.save(p);
		korisnikService.dodajPutovanjeKorisniku(idKorisnik, p);
		return PutovanjeDTO.toDTO(p);
	}

	public List<PutovanjeDTO> dohvatiPutovanjaZaKorisnika(Long idKorisnika) {
		return korisnikService.korisnikRepository.findById(idKorisnika).get().getPutovanja().stream()
				.map(putovanje -> PutovanjeDTO.toDTO(putovanje)).collect(Collectors.toList());
	}

	public void dodajAktivnostPutovanju(Long idPutovanje, Aktivnost a) {
		Putovanje putovanje = putovanjeRepository.findById(idPutovanje).get();
		putovanje.dodajAktivnost(a);
		putovanjeRepository.save(putovanje);
	}

	public PutovanjeDTO dohvatiPutovanje(Long idPutovanje) {
		return PutovanjeDTO.toDTO(putovanjeRepository.findById(idPutovanje).get());
	}

	public void dodajSmjestajPutovanju(Long idPutovanje, Smjestaj s) {
		Putovanje p = putovanjeRepository.findById(idPutovanje).get();
		p.dodajSmjestaj(s);
		putovanjeRepository.save(p);

	}

	public List<SmjestajDTO> dohvatiSmjestajNaPutovanje(Long idPutovanje) {
		Putovanje p = putovanjeRepository.findById(idPutovanje).get();
		return p.getSmjestajNaPutovanju().stream().map(smjestaj -> SmjestajDTO.toDto(smjestaj)).toList();

	}

	public void dodajPrijevozPutovanju(Long idPutovanje, Prijevoz p) {
		Putovanje putovanje = putovanjeRepository.findById(idPutovanje).get();
		putovanje.dodajPrijevoz(p);
		putovanjeRepository.save(putovanje);

	}

	public List<PrijevozDTO> dohvatiPrijevoz(Long idPutovanje) {
		Putovanje p = putovanjeRepository.findById(idPutovanje).get();
		return p.getPrijevozNaPutovanju().stream().map(prijevoz -> PrijevozDTO.toDto(prijevoz)).toList();
	}

	public Long troskovnik(Long idPutovanje) {
		Set<Aktivnost> aktivnostiNaPutovanju = putovanjeRepository.findById(idPutovanje).get()
				.getAktivnostiNaPutovanju();
		Set<Smjestaj> smjestajNaPutovanju = putovanjeRepository.findById(idPutovanje).get().getSmjestajNaPutovanju();
		Set<Prijevoz> prijevozNaPutovanju = putovanjeRepository.findById(idPutovanje).get().getPrijevozNaPutovanju();
		Long cijena = 0L;
		for (Aktivnost a : aktivnostiNaPutovanju) {
			if (a.getCijena() != null)
				cijena += a.getCijena();
		}
		for (Smjestaj a : smjestajNaPutovanju) {
			if (a.getCijena() != null)
				cijena += a.getCijena();
		}
		for (Prijevoz a : prijevozNaPutovanju) {
			if (a.getCost() != null)
				cijena += a.getCost();
		}
		return cijena;

	}

	public PutovanjeDTO urediPutovanje(Long idPutovanja, PutovanjeDTO putovanje) {
		Putovanje p = putovanjeRepository.findById(idPutovanja).get();
		if (putovanje.getNaziv() != null)
			p.setNaziv(putovanje.getNaziv());
		if (putovanje.getDatumPocetak() != null)
			p.setDatumPocetak(putovanje.getDatumPocetak());
		if (putovanje.getDatumKraj() != null)
			p.setDatumKraj(putovanje.getDatumKraj());
		putovanjeRepository.save(p);
		return PutovanjeDTO.toDTO(p);
	}

	public Boolean izbrisi(Long idPutovanje) {
		Putovanje p = putovanjeRepository.findById(idPutovanje).get();
		Korisnik k = korisnikService.dohvatiKorisnikaPoPutovanju(idPutovanje);
		Set<Putovanje> putovanja = k.getPutovanja();
		putovanja.remove(p);
		k.setPutovanja(putovanja);
		korisnikService.korisnikRepository.save(k);
		putovanjeRepository.deleteById(idPutovanje);
		return true;
	}

	public List<Putovanje> dohvatiSvaPutovanja() {
		return putovanjeRepository.findAll();
	}

	public Putovanje dohvatiPutovanjePoAktivnosti(Long idAktivnost) {
		List<Putovanje> putovanja = putovanjeRepository.findAll();
		for (Putovanje p : putovanja) {
			if (!p.getAktivnostiNaPutovanju().stream().filter(aktivnost -> aktivnost.getIdAktivnost() == idAktivnost)
					.toList().isEmpty())
				return p;
		}
		return null;
	}

	public void ukloniSmjestajSPutovanja(Smjestaj s) {
		List<Putovanje> putovanja = dohvatiSvaPutovanja();
		for (Putovanje p : putovanja) {
			if (p.getSmjestajNaPutovanju().contains(s)) {
				p.ukloniSmjestaj(s);
				putovanjeRepository.save(p);
			}
		}

	}

	public void ukloniPrijevozNaPutovanju(Prijevoz p) {
		List<Putovanje> putovanja = dohvatiSvaPutovanja();
		for (Putovanje pu : putovanja) {
			if (pu.getPrijevozNaPutovanju().contains(p)) {
				pu.ukloniPrijevoz(p);
				putovanjeRepository.save(pu);
			}
		}

	}

	public void ukloniAktivnostNaPutovanju(Long idPutovanje, Aktivnost a) {
		Putovanje p = putovanjeRepository.findById(idPutovanje).get();
		p.ukloniAktivnost(a);
		putovanjeRepository.save(p);

	}

	public Putovanje dohvatiPutovanjePoSmjestaju(Long idSmjestaja) {
		List<Putovanje> putovanja = putovanjeRepository.findAll();
		for (Putovanje p : putovanja) {
			if (!p.getSmjestajNaPutovanju().stream().filter(aktivnost -> aktivnost.getIdSmjestaja() == idSmjestaja)
					.toList().isEmpty())
				return p;
		}
		return null;
	}

	public List<AktivnostDTO> dohvatiAktivnostiNaPutovanju(Long idPutovanje) {
		Putovanje p = putovanjeRepository.findById(idPutovanje).get();
		return p.getAktivnostiNaPutovanju().stream().map(aktivnost -> AktivnostDTO.toDto(aktivnost)).toList();
	}

	public List<PutovanjeDTO> dohvatiPutovanjaSearch(String searchTerm) {
		return putovanjeRepository.findAll().stream().filter(putovanje -> putovanje.getNaziv().contains(searchTerm))
				.map(putovanje -> PutovanjeDTO.toDTO(putovanje)).toList();
	}

	public String podatciZaPdf(Long idPutovanje) {
		String podatci = "";
		Putovanje p = putovanjeRepository.findById(idPutovanje).get();
		List<Date> datumiNaPutovanju = generirajListuDatuma(p.getDatumPocetak(), p.getDatumKraj());
		List<Aktivnost> aktivnostiNaPutovanju = p.getAktivnostiNaPutovanju().stream().toList();
		List<Smjestaj> smjestajNaPutovanju = p.getSmjestajNaPutovanju().stream().toList();
		List<Prijevoz> prijevozNaPutovanju = p.getPrijevozNaPutovanju().stream().toList();

		podatci += "Itinerar\n\n";
		for(Date d : datumiNaPutovanju) {
			podatci += "  " + d.toLocaleString() + "\n\n";
			for(Aktivnost a : aktivnostiNaPutovanju.stream().filter(a -> a.getDatumOdrzavanja().getDate() == d.getDate() && a.getDatumOdrzavanja().getDay() == d.getDay()).toList()) {
				podatci += "    Naziv aktivnosti: " + a.getNazivAktivnosti() + "\n"
						+  "    Vrijeme početka: " + a.getVrijemePocetka().toString() + "\n"
						+  "    Vrijeme završetka: " + a.getVrijemeZavrsetka().toString() + "\n"
						+  "    Cijena: " + a.getCijena() + "\n"
						+  "    URL: " + a.getURL() + "\n"
						+  "    Detalji: " + a.getDetalji() + "\n\n";
			}
		}
		
		podatci += "Smještaj\n\n";
		for(Smjestaj s : smjestajNaPutovanju) {
			podatci += "    Naziv smještaja: " + s.getNazivSmjestaja() + "\n"
					+  "    Datum prijave: " + s.getDatumPrijave().toLocaleString() + "\n"
					+  "    Datum odjave: " + s.getDatumOdjave().toLocaleString() + "\n"
					+  "    Cijena: " + s.getCijena() + "\n"
					+  "    URL: " + s.getURL() + "\n"
					+  "    Broj mobitela: " + s.getBrMobitel()+ "\n"
					+  "    Adresa: " + s.getAdresa()+ "\n\n";
		}
		
		podatci += "Prijevoz\n\n";
		for(Prijevoz pr : prijevozNaPutovanju) {
			podatci += "    Naziv prijevoza: " + pr.getNazivPrijevoza() + "\n"
					+  "    Datum prijevoza: " + pr.getDatumPrijevoza().toLocaleString() + "\n"
					+  "    Cijena: " + pr.getCost() + "\n"
					+  "    Od: " + pr.getOdMjesta().getNazivMjesta() + "\n"
					+  "    Do: " + pr.getDoMjesta().getNazivMjesta()+ "\n\n";
		}
		
		return podatci;
		
	}
	
	public static List<Date> generirajListuDatuma(Date pocetniDatum, Date zavrsniDatum) {
        List<Date> listaDatuma = new ArrayList<>();
        Calendar trenutniKalendar = Calendar.getInstance();
        trenutniKalendar.setTime(pocetniDatum);

        while (!trenutniKalendar.getTime().after(zavrsniDatum)) {
            Date trenutniDatum = trenutniKalendar.getTime();
            listaDatuma.add(trenutniDatum);
            trenutniKalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return listaDatuma;
    }

}
