package fer.hr.zavrsni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fer.hr.zavrsni.dao.DrzavaRepository;
import fer.hr.zavrsni.dao.MjestoRepository;
import fer.hr.zavrsni.domain.Drzava;
import fer.hr.zavrsni.domain.Mjesto;
import fer.hr.zavrsni.dto.MjestoDTO;

@Service
public class MjestoService {

	@Autowired
	DrzavaRepository drzavaRepository;
	@Autowired
	MjestoRepository mjestoRepository;

	public Mjesto getMjestoById(Long idMjesto) {
		return mjestoRepository.findById(idMjesto).get();
	}

	public List<MjestoDTO> dohvatiSvaMjesta() {
		return mjestoRepository.findAll().stream().map(mjesto -> MjestoDTO.toDto(mjesto)).toList();
	}

	public MjestoDTO novoMjesto(MjestoDTO mjesto) {
		Drzava d = drzavaRepository.findAll().stream()
				.filter(drzava -> drzava.getISOOznaka().equals(mjesto.getISOOznakaDrzave())).findFirst().get();
		Mjesto m = new Mjesto(mjesto.getPbr(), mjesto.getNazivMjesta(), d);
		mjestoRepository.save(m);
		return MjestoDTO.toDto(m);
	}

}
