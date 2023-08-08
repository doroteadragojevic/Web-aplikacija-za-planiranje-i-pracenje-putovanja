package fer.hr.zavrsni.rest;

import java.sql.Date;
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

import fer.hr.zavrsni.dto.AktivnostDTO;
import fer.hr.zavrsni.service.AktivnostService;

@RestController
@RequestMapping("/aktivnosti")
public class AktivnostController {

	@Autowired
	AktivnostService aktivnostService;

	@GetMapping("/{idPutovanje}")
	public ResponseEntity<List<AktivnostDTO>> dohvatiSveAktivnosti(@PathVariable Long idPutovanje) {
		return new ResponseEntity<>(aktivnostService.dohvatiAktivnosti(idPutovanje), HttpStatus.OK);
	}
	
	@GetMapping("/aktivnost/{idAktivnost}")
	public ResponseEntity<AktivnostDTO> dohvatiAktivnost(@PathVariable Long idAktivnost){
		return new ResponseEntity<>(aktivnostService.dohvatiAktivnost(idAktivnost), HttpStatus.OK);
	}
	
	@GetMapping("/aktivnostiZaDatum/{idPutovanje}/{datum}")
	public ResponseEntity<List<AktivnostDTO>> dohvatiAktivnostiZaDatum(@PathVariable Long idPutovanje, @PathVariable Date date){
		return new ResponseEntity<>(aktivnostService.dohvatiAktivnostiZaDatum(idPutovanje, date), HttpStatus.OK);
	}
	
	@PutMapping("/uredi/{idAktivnost}")
	public ResponseEntity<AktivnostDTO> urediAktivnost(@PathVariable Long idAktivnost, @RequestBody AktivnostDTO aktivnost){
		try {
		return new ResponseEntity<>(aktivnostService.urediAktivnost(idAktivnost, aktivnost), HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	
	@PostMapping("/novaAktivnost/{idPutovanje}")
	public ResponseEntity<AktivnostDTO> novaAktivnost(@PathVariable Long idPutovanje,
			@RequestBody AktivnostDTO aktivnost) {
		try {
			return new ResponseEntity<>(aktivnostService.novaAktivnost(idPutovanje, aktivnost), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@DeleteMapping("/izbrisi/{idAktivnost}")
	public ResponseEntity<Boolean> izbrisiAktivnost(@PathVariable Long idAktivnost){
		return new ResponseEntity<>( aktivnostService.izbrisiAktivnost(idAktivnost), HttpStatus.OK);
	}

}
