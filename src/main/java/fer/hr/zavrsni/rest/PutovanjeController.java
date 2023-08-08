package fer.hr.zavrsni.rest;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fer.hr.zavrsni.dto.AktivnostDTO;
import fer.hr.zavrsni.dto.PutovanjeDTO;
import fer.hr.zavrsni.dto.SmjestajDTO;
import fer.hr.zavrsni.service.PutovanjeService;

@RestController
@RequestMapping("/putovanja")
public class PutovanjeController {

	@Autowired
	PutovanjeService putovanjeService;
	
	@GetMapping("/search/{searchTerm}")
	public ResponseEntity<List<PutovanjeDTO>> dohvatiPutovanjaSearch(@PathVariable String searchTerm){
		return new ResponseEntity<>(putovanjeService.dohvatiPutovanjaSearch(searchTerm), HttpStatus.OK);
	}

	@GetMapping("/{idKorisnik}")
	public ResponseEntity<List<PutovanjeDTO>> dohvatiPutovanjaZaKorisnika(@PathVariable Long idKorisnik) {
		return new ResponseEntity<>(putovanjeService.dohvatiPutovanjaZaKorisnika(idKorisnik), HttpStatus.OK);
	}

	@GetMapping("/putovanje/{idPutovanje}")
	public ResponseEntity<PutovanjeDTO> dohvatiPutovanje(@PathVariable Long idPutovanje) {
		PutovanjeDTO putovanje = putovanjeService.dohvatiPutovanje(idPutovanje);
		if (putovanje != null)
			return new ResponseEntity<>(putovanje, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/troskovnik/{idPutovanje}")
	public ResponseEntity<Long> troskovnik(@PathVariable Long idPutovanje){
		return new ResponseEntity<>(putovanjeService.troskovnik(idPutovanje), HttpStatus.OK);
	}
	
	@GetMapping("/pdf/{idPutovanje}")
	public ResponseEntity<String> podatciZaPdf(@PathVariable Long idPutovanje){
		return new ResponseEntity<>(putovanjeService.podatciZaPdf(idPutovanje), HttpStatus.OK);
	}
	
	@PutMapping("/uredi/{idPutovanja}")
	public ResponseEntity<PutovanjeDTO> urediPutovanje(@PathVariable Long idPutovanja, @RequestBody PutovanjeDTO putovanje){
		return new ResponseEntity<>(putovanjeService.urediPutovanje(idPutovanja, putovanje), HttpStatus.OK);
	}

	@PostMapping("/novoPutovanje/{idKorisnik}")
	public ResponseEntity<PutovanjeDTO> createPutovanje(@PathVariable Long idKorisnik,
			@RequestBody PutovanjeDTO putovanje) {
		try {
			PutovanjeDTO p = putovanjeService.kreirajPutovanje(idKorisnik, putovanje.getNaziv(),
					putovanje.getDatumPocetak(), putovanje.getDatumKraj(), putovanje.getIdMjesto());

			return new ResponseEntity<>(p, HttpStatus.OK);
		} catch (IllegalArgumentException | NoSuchElementException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/izbrisi/{idPutovanje}")
	public ResponseEntity<Boolean> izbrisiPutovanje(@PathVariable Long idPutovanje){
		return new ResponseEntity<>(putovanjeService.izbrisi(idPutovanje), HttpStatus.OK);
	}
}
