package fer.hr.zavrsni.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fer.hr.zavrsni.dto.KorisnikDTO;
import fer.hr.zavrsni.dto.LoginDTO;
import fer.hr.zavrsni.service.KorisnikService;

@RestController
@RequestMapping("/users")
public class KorisnikController {

	@Autowired
	KorisnikService korisnikService;

	@GetMapping("/{idKorisnik}")
	public ResponseEntity<KorisnikDTO> dohvatiKorisnika(@PathVariable Long idKorisnik) {
		return new ResponseEntity<>(korisnikService.dohvatiKorisnika(idKorisnik), HttpStatus.OK);
	}

	@PutMapping("/uredi/{idKorisnik}")
	public ResponseEntity<KorisnikDTO> urediKorisnika(@PathVariable Long idKorisnik,
			@RequestBody KorisnikDTO korisnik) {
		return new ResponseEntity<>(korisnikService.urediKorisnika(idKorisnik, korisnik), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginDTO> createParticipantUserAccount(@RequestBody LoginDTO account) {
		Long loginId = korisnikService.login(account.getUsername(), account.getPassword());
		if (loginId != null) {
			account.setId(loginId);
			return new ResponseEntity<>(account, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}

	@PostMapping("/kreirajKorisnika")
	public ResponseEntity<Long> kreirajKorisnika(@RequestBody KorisnikDTO korisnik) {
		KorisnikDTO k = korisnikService.kreirajKorisnika(korisnik.getUsername(), korisnik.getLozinka(),
				korisnik.getIme(), korisnik.getPrezime(), korisnik.getDatumRodenja(), korisnik.getEmail());
		if (k != null)
			try {
				return new ResponseEntity<>(korisnik.getId(), HttpStatus.OK);
			} catch (IllegalArgumentException e) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

}
