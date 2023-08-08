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

import fer.hr.zavrsni.dto.PrijevozDTO;
import fer.hr.zavrsni.service.PrijevozService;

@RestController
@RequestMapping("/prijevoz")
public class PrijevozController {

	@Autowired
	PrijevozService prijevozService;
	
	@GetMapping("/{idPutovanje}")
	public ResponseEntity<List<PrijevozDTO>> dohvatiPrijevoz(@PathVariable Long idPutovanje){
		return new ResponseEntity<>(prijevozService.dohvatiPrijevoz(idPutovanje), HttpStatus.OK);
	}
	
	@GetMapping("/prijevoz/{idPrijevoz}")
	public ResponseEntity<PrijevozDTO> dohvatiPrijevozPoId(@PathVariable Long idPrijevoz){
		return new ResponseEntity<>(prijevozService.dohvatiPrijevozPoId(idPrijevoz), HttpStatus.OK);
	}
	
	@PutMapping("/uredi/{idPrijevoz}")
	public ResponseEntity<PrijevozDTO> uredi(@PathVariable Long idPrijevoz, @RequestBody PrijevozDTO prijevoz){
		return new ResponseEntity<>(prijevozService.uredi(idPrijevoz, prijevoz), HttpStatus.OK);
	}

	@PostMapping("/dodajPrijevoz/{idPutovanje}")
	public ResponseEntity<PrijevozDTO> dodajPrijevoz(@PathVariable Long idPutovanje,
			@RequestBody PrijevozDTO prijevoz) {
		try {
			return new ResponseEntity<>(prijevozService.dodajPrijevoz(idPutovanje, prijevoz), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/izbrisi/{idPrijevoz}")
	public ResponseEntity<Boolean> izbrisiPrijevoz(@PathVariable Long idPrijevoz){
		return new ResponseEntity<>(prijevozService.izbrisiPrijevoz(idPrijevoz), HttpStatus.OK);
	}

}
