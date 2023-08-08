package fer.hr.zavrsni.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fer.hr.zavrsni.dao.AktivnostRepository;
import fer.hr.zavrsni.domain.Aktivnost;
import fer.hr.zavrsni.domain.Putovanje;
import fer.hr.zavrsni.dto.AktivnostDTO;
import fer.hr.zavrsni.dto.PutovanjeDTO;

@Service
public class AktivnostService {

	@Autowired
	AktivnostRepository aktivnostRepository;

	@Autowired
	PutovanjeService putovanjeService;
	@Autowired
	MjestoService mjestoService;

	public AktivnostDTO novaAktivnost(Long idPutovanje, AktivnostDTO aktivnost) {
		if (aktivnost.getVrijemePocetka().isAfter(aktivnost.getVrijemeZavrsetka()))
			throw new IllegalArgumentException();
		if (aktivnost.getCijena() != null && aktivnost.getCijena() < 0)
			throw new IllegalArgumentException();
		if (aktivnost.getCijena() == null)
			aktivnost.setCijena((long) 0);
		PutovanjeDTO p = putovanjeService.dohvatiPutovanje(idPutovanje);
		Aktivnost a = new Aktivnost(aktivnost.getNazivAktivnosti(), aktivnost.getDatumOdrzavanja(),
				aktivnost.getVrijemePocetka(), aktivnost.getVrijemeZavrsetka(), aktivnost.getCijena(),
				aktivnost.getURL(), aktivnost.getDetalji());
		aktivnostRepository.save(a);
		putovanjeService.dodajAktivnostPutovanju(idPutovanje, a);
		try {
			provjeriPreklapanje(a);
		} catch(IllegalArgumentException e) {
			putovanjeService.ukloniAktivnostNaPutovanju(idPutovanje, a);
			aktivnostRepository.delete(a);
			throw new IllegalArgumentException();
		}
		return AktivnostDTO.toDto(a);
	}

	private void provjeriPreklapanje(Aktivnost a) {
		 
		Putovanje p = putovanjeService.dohvatiPutovanjePoAktivnosti(a.getIdAktivnost());
		Set<Aktivnost> aktivnosti = p.getAktivnostiNaPutovanju();
		for(Aktivnost aktivnost : aktivnosti) {
			if(aktivnost.getIdAktivnost() != a.getIdAktivnost() && aktivnost.getDatumOdrzavanja() == a.getDatumOdrzavanja()) {
				if(a.getVrijemePocetka().isAfter(aktivnost.getVrijemePocetka()) && a.getVrijemePocetka().isBefore( aktivnost.getVrijemeZavrsetka())) {
					throw new IllegalArgumentException();
				}
				if(a.getVrijemeZavrsetka().isAfter(aktivnost.getVrijemePocetka()) && a.getVrijemeZavrsetka().isBefore( aktivnost.getVrijemeZavrsetka())) throw new IllegalArgumentException();
				if(a.getVrijemePocetka().isBefore(aktivnost.getVrijemePocetka()) && a.getVrijemeZavrsetka().isAfter( aktivnost.getVrijemeZavrsetka())) throw new IllegalArgumentException();

			}
		}
		
	}

	public List<AktivnostDTO> dohvatiAktivnosti(Long idPutovanja) {
		Putovanje p = putovanjeService.putovanjeRepository.findById(idPutovanja).get();
		return p.getAktivnostiNaPutovanju().stream().map(aktivnost -> AktivnostDTO.toDto(aktivnost)).toList();
	}

	public AktivnostDTO dohvatiAktivnost(Long idAktivnost) {
		return AktivnostDTO.toDto(aktivnostRepository.findById(idAktivnost).get());
	}

	public AktivnostDTO urediAktivnost(Long idAktivnost, AktivnostDTO aktivnost) {
		Aktivnost a = aktivnostRepository.findById(idAktivnost).get();
		Putovanje p = putovanjeService.dohvatiPutovanjePoAktivnosti(idAktivnost);
		if(aktivnost.getCijena() < 0 || aktivnost.getVrijemePocetka().isAfter(aktivnost.getVrijemeZavrsetka())) throw new IllegalArgumentException();
		if(aktivnost.getDatumOdrzavanja().before(p.getDatumPocetak()) || aktivnost.getDatumOdrzavanja().after(p.getDatumKraj())) throw new IllegalArgumentException();
		if (aktivnost.getNazivAktivnosti() != null)
			a.setNazivAktivnosti(aktivnost.getNazivAktivnosti());
		if (aktivnost.getDatumOdrzavanja() != null)
			a.setDatumOdrzavanja(aktivnost.getDatumOdrzavanja());
		if (aktivnost.getVrijemePocetka() != null)
			a.setVrijemePocetka(aktivnost.getVrijemePocetka());
		if (aktivnost.getVrijemeZavrsetka() != null)
			a.setVrijemeZavrsetka(aktivnost.getVrijemeZavrsetka());
		if (aktivnost.getCijena() != null)
			a.setCijena(aktivnost.getCijena());
		if (aktivnost.getURL() != null)
			a.setURL(aktivnost.getURL());
		if (aktivnost.getDetalji() != null)
			a.setDetalji(aktivnost.getDetalji());
		provjeriPreklapanje(a);
		aktivnostRepository.save(a);
		return AktivnostDTO.toDto(a);

	}

	public Boolean izbrisiAktivnost(Long idAktivnost) {
		Aktivnost a = aktivnostRepository.findById(idAktivnost).get();
		Putovanje p = putovanjeService.dohvatiPutovanjePoAktivnosti(idAktivnost);
		p.ukloniAktivnost(a);
		putovanjeService.putovanjeRepository.save(p);
		aktivnostRepository.deleteById(idAktivnost);
		return true;
	}

	public List<AktivnostDTO> dohvatiAktivnostiZaDatum(Long idPutovanje, Date date) {
		return putovanjeService.dohvatiAktivnostiNaPutovanju(idPutovanje).stream().filter(aktivnost -> aktivnost.getDatumOdrzavanja().equals(date)).toList();
	}

}
