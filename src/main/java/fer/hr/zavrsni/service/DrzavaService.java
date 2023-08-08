package fer.hr.zavrsni.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fer.hr.zavrsni.dao.DrzavaRepository;
import fer.hr.zavrsni.dto.DrzavaDTO;

@Service
public class DrzavaService {

	@Autowired
	DrzavaRepository drzavaRepository;

	public List<DrzavaDTO> getAllDrzave() {
		return drzavaRepository.findAll().stream().map(drzava -> DrzavaDTO.toDto(drzava)).collect(Collectors.toList());
	}

}
