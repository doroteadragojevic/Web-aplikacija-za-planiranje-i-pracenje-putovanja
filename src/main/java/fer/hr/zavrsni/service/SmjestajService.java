package fer.hr.zavrsni.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fer.hr.zavrsni.dao.SmjestajRepository;
import fer.hr.zavrsni.domain.Aktivnost;
import fer.hr.zavrsni.domain.Putovanje;
import fer.hr.zavrsni.domain.Smjestaj;
import fer.hr.zavrsni.domain.VrstaSmjestaja;
import fer.hr.zavrsni.dto.PutovanjeDTO;
import fer.hr.zavrsni.dto.SmjestajDTO;
import jakarta.validation.constraints.NotNull;

@Service
public class SmjestajService {

	@Autowired
	SmjestajRepository smjestajRepository;

	@Autowired
	PutovanjeService putovanjeService;
	@Autowired
	MjestoService mjestoService;

	public SmjestajDTO novoPutovanje(Long idPutovanje, SmjestajDTO smjestaj) {
		if (smjestaj.getDatumPrijave().after(smjestaj.getDatumOdjave()))
			throw new IllegalArgumentException();
		if (smjestaj.getCijena() < 0)
			throw new IllegalArgumentException();
		VrstaSmjestaja vs = null;
		switch (smjestaj.getIdVrsteSmjestaja().intValue()) {
		case 1:
			vs = VrstaSmjestaja.HOTEL;
			break;
		case 2:
			vs = VrstaSmjestaja.APARTMAN;
			break;
		case 3:
			vs = VrstaSmjestaja.PRIVATNI_SMJESTAJ;
		}
		Smjestaj s = new Smjestaj(smjestaj.getNazivSmjestaja(), smjestaj.getCijena(), smjestaj.getDatumPrijave(),
				smjestaj.getDatumOdjave(), smjestaj.getAdresa(), smjestaj.getBrMobitel(), smjestaj.getURL(), vs);
		smjestajRepository.save(s);
		PutovanjeDTO p = putovanjeService.dohvatiPutovanje(idPutovanje);
		if(s.getDatumPrijave().before(p.getDatumPocetak()) || s.getDatumPrijave().after(p.getDatumKraj())) throw new IllegalArgumentException();
		if(s.getDatumOdjave().before(p.getDatumPocetak()) || s.getDatumOdjave().after(p.getDatumKraj())) throw new IllegalArgumentException();

		putovanjeService.dodajSmjestajPutovanju(idPutovanje, s);
		try {
			provjeriPreklapanje(s);
		} catch(IllegalArgumentException e) {
			putovanjeService.ukloniSmjestajSPutovanja(s);
			smjestajRepository.delete(s);
			throw new IllegalArgumentException();
		}
		return SmjestajDTO.toDto(s);
	}

	private void provjeriPreklapanje(Smjestaj a) {
		 
		Putovanje p = putovanjeService.dohvatiPutovanjePoSmjestaju(a.getIdSmjestaja());
		Set<Smjestaj> smjestaj = p.getSmjestajNaPutovanju();
		for(Smjestaj s : smjestaj) {
			if(s.getIdSmjestaja() != a.getIdSmjestaja()) {
				if(a.getDatumPrijave().after(s.getDatumPrijave()) && a.getDatumPrijave().before( s.getDatumPrijave())) {
					throw new IllegalArgumentException();
				}
				if(a.getDatumOdjave().after(s.getDatumOdjave()) && a.getDatumOdjave().before( s.getDatumOdjave())) throw new IllegalArgumentException();
				if(a.getDatumPrijave().before(s.getDatumPrijave()) && a.getDatumOdjave().after( s.getDatumOdjave())) throw new IllegalArgumentException();

			}
		}
		
	}
	
	public List<SmjestajDTO> dohvatiSmjestaj(Long idPutovanje) {
		return putovanjeService.dohvatiSmjestajNaPutovanje(idPutovanje);
	}

	public SmjestajDTO dohvatiSmjestajPoIdu(Long idSmjestaj) {
		return SmjestajDTO.toDto(smjestajRepository.findById(idSmjestaj).get());
	}

	public SmjestajDTO uredi(Long idSmjestaj, SmjestajDTO smjestaj) {
		Smjestaj s = smjestajRepository.findById(idSmjestaj).get();
		
		if (smjestaj.getIdVrsteSmjestaja() != null) {
			VrstaSmjestaja vs = null;
			switch (smjestaj.getIdVrsteSmjestaja().intValue()) {
			case 1:
				vs = VrstaSmjestaja.HOTEL;
				break;
			case 2:
				vs = VrstaSmjestaja.APARTMAN;
				break;
			case 3:
				vs = VrstaSmjestaja.PRIVATNI_SMJESTAJ;
			}
			s.setVrstaSmjestaja(vs);
		}
		if (smjestaj.getCijena() != null)
			s.setCijena(smjestaj.getCijena());
		if (smjestaj.getDatumPrijave() != null)
			s.setDatumPrijave(smjestaj.getDatumPrijave());
		if (smjestaj.getDatumOdjave() != null)
			s.setDatumOdjave(smjestaj.getDatumOdjave());
		if (smjestaj.getAdresa() != null)
			s.setAdresa(smjestaj.getAdresa());
		if (smjestaj.getBrMobitel() != null)
			s.setBrMobitel(smjestaj.getBrMobitel());
		if (smjestaj.getURL() != null)
			s.setURL(smjestaj.getURL());
		
		provjeriPreklapanje(s);
		smjestajRepository.save(s);
		return SmjestajDTO.toDto(s);

	}

	public Boolean izbrisiSmjestaj(Long idSmjestaj) {
		Smjestaj s = smjestajRepository.findById(idSmjestaj).get();
		putovanjeService.ukloniSmjestajSPutovanja(s);
		smjestajRepository.deleteById(idSmjestaj);
		return true;
	}

}
