package fer.hr.zavrsni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fer.hr.zavrsni.dao.PrijevozRepository;
import fer.hr.zavrsni.domain.Prijevoz;
import fer.hr.zavrsni.domain.VrstaPrijevoza;
import fer.hr.zavrsni.dto.PrijevozDTO;

@Service
public class PrijevozService {

	@Autowired
	PrijevozRepository prijevozRepository;

	@Autowired
	PutovanjeService putovanjeService;
	
	@Autowired
	MjestoService mjestoService;

	public PrijevozDTO dodajPrijevoz(Long idPutovanje, PrijevozDTO prijevoz) {
		if (prijevoz.getCost() < 0)
			throw new IllegalArgumentException();
		VrstaPrijevoza vp = null;
		switch (prijevoz.getIdVrstaPrijevoza().intValue()) {
		case 1:
			vp = VrstaPrijevoza.AUTOBUS;
			break;
		case 2:
			vp = VrstaPrijevoza.VLAK;
			break;
		case 3:
			vp = VrstaPrijevoza.BROD;
			break;
		case 4:
			vp = VrstaPrijevoza.AVION;
			break;
		case 5:
			vp = VrstaPrijevoza.AUTOMOBIL;
			break;
		}
		Prijevoz p = new Prijevoz(prijevoz.getNazivPrijevoza(), prijevoz.getCost(), vp, prijevoz.getDatumPrijevoza(), mjestoService.getMjestoById(prijevoz.getIdOd()), mjestoService.getMjestoById(prijevoz.getIdDo()));

		prijevozRepository.save(p);
		putovanjeService.dodajPrijevozPutovanju(idPutovanje, p);
		return PrijevozDTO.toDto(p);

	}

	public List<PrijevozDTO> dohvatiPrijevoz(Long idPutovanje) {
		return putovanjeService.dohvatiPrijevoz(idPutovanje);
	}

	public PrijevozDTO dohvatiPrijevozPoId(Long idPrijevoz) {
		return PrijevozDTO.toDto(prijevozRepository.findById(idPrijevoz).get());
	}

	public PrijevozDTO uredi(Long idPrijevoz, PrijevozDTO prijevoz) {
		Prijevoz p = prijevozRepository.findById(idPrijevoz).get();
		if (prijevoz.getNazivPrijevoza() != null)
			p.setNazivPrijevoza(prijevoz.getNazivPrijevoza());
		if (prijevoz.getIdVrstaPrijevoza() != null) {
			VrstaPrijevoza vp = null;
			switch (prijevoz.getIdVrstaPrijevoza().intValue()) {
			case 1:
				vp = VrstaPrijevoza.AUTOBUS;
				break;
			case 2:
				vp = VrstaPrijevoza.VLAK;
				break;
			case 3:
				vp = VrstaPrijevoza.BROD;
				break;
			case 4:
				vp = VrstaPrijevoza.AVION;
				break;
			case 5:
				vp = VrstaPrijevoza.AUTOMOBIL;
				break;
			}

			p.setVrstaPrijevoza(vp);
		}
		
		if(prijevoz.getDatumPrijevoza() != null) p.setDatumPrijevoza(prijevoz.getDatumPrijevoza());
		
		if(prijevoz.getIdOd() != null) p.setOdMjesta(mjestoService.getMjestoById(prijevoz.getIdOd()));
		if(prijevoz.getIdDo() != null) p.setDoMjesta(mjestoService.getMjestoById(prijevoz.getIdDo()));

		
		

		prijevozRepository.save(p);
		return PrijevozDTO.toDto(p);
	}

	public Boolean izbrisiPrijevoz(Long idPrijevoz) {
		Prijevoz p = prijevozRepository.findById(idPrijevoz).get();
		putovanjeService.ukloniPrijevozNaPutovanju(p);
		prijevozRepository.deleteById(idPrijevoz);
		return true;
	}

}
