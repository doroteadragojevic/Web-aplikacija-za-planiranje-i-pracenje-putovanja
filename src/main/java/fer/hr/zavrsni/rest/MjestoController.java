package fer.hr.zavrsni.rest;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fer.hr.zavrsni.dto.MjestoDTO;
import fer.hr.zavrsni.service.MjestoService;

@RestController
@RequestMapping("/mjesta")
public class MjestoController {

	@Autowired
	MjestoService mjestoService;

	@GetMapping("/")
	public ResponseEntity<List<MjestoDTO>> dohvatiSvaMjesta() {
		return new ResponseEntity<>(mjestoService.dohvatiSvaMjesta(), HttpStatus.OK);
	}
	
	@PostMapping("/novoMjesto")
	public ResponseEntity<MjestoDTO> novoMjesto(@RequestBody MjestoDTO mjesto){
		
		return new ResponseEntity<>(mjestoService.novoMjesto(mjesto), HttpStatus.OK);
		
	}

}
