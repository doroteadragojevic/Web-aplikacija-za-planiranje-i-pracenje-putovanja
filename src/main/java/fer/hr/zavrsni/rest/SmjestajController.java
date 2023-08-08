package fer.hr.zavrsni.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fer.hr.zavrsni.dto.SmjestajDTO;
import fer.hr.zavrsni.service.SmjestajService;

@RestController
@RequestMapping("/smjestaj")
public class SmjestajController {

	@Autowired
	SmjestajService smjestajService;
	
	@GetMapping("/{idPutovanje}")
	public ResponseEntity<List<SmjestajDTO>> dohvatiSmjestaj( @PathVariable Long idPutovanje){
		return new ResponseEntity<>(smjestajService.dohvatiSmjestaj(idPutovanje), HttpStatus.OK);
	}
	
	@GetMapping("/smjestaj/{idSmjestaj}")
	public ResponseEntity<SmjestajDTO> dohvatiSmjestajZaId(@PathVariable Long idSmjestaj){
		return new ResponseEntity<>(smjestajService.dohvatiSmjestajPoIdu(idSmjestaj), HttpStatus.OK);
	}
	
	@PutMapping("/uredi/{idSmjestaj}")
	public ResponseEntity<SmjestajDTO> urediSmjestaj(@PathVariable Long idSmjestaj, @RequestBody SmjestajDTO smjestaj){
		return new ResponseEntity<>(smjestajService.uredi(idSmjestaj, smjestaj), HttpStatus.OK);
	}

	@PostMapping("/noviSmjestaj/{idPutovanje}")
	public ResponseEntity<SmjestajDTO> novoPutovanje(@PathVariable Long idPutovanje,
			@RequestBody SmjestajDTO smjestaj) {
		try {
			return new ResponseEntity<>(smjestajService.novoPutovanje(idPutovanje, smjestaj), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/izbrisi/{idSmjestaj}")
	public ResponseEntity<Boolean> izbrisiSmjestaj(@PathVariable Long idSmjestaj){
		return new ResponseEntity<>(smjestajService.izbrisiSmjestaj(idSmjestaj), HttpStatus.OK);
	}

}
