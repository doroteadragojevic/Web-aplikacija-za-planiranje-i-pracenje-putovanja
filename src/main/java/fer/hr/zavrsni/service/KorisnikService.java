package fer.hr.zavrsni.service;

import java.util.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fer.hr.zavrsni.dao.KorisnikRepository;
import fer.hr.zavrsni.domain.Korisnik;
import fer.hr.zavrsni.domain.Putovanje;
import fer.hr.zavrsni.dto.KorisnikDTO;

@Service
public class KorisnikService {

	@Autowired
	KorisnikRepository korisnikRepository;

	public Long login(String username, String password) {
		Korisnik k = korisnikRepository.findByUsername(username);
		if (BCrypt.checkpw(password, k.getLozinka()))
			return k.getIdKorisnik();
		return null;
	}

	public KorisnikDTO kreirajKorisnika(String username, String lozinka, String ime, String prezime, Date datumRodenja,
			String email) {
		checkIfUsernameExists(username);
		String salt = BCrypt.gensalt();
	    String hashedPassword = BCrypt.hashpw(lozinka, salt);
		return KorisnikDTO
				.toDto(korisnikRepository.save(new Korisnik(username, hashedPassword, ime, prezime, datumRodenja, email)));
	}

	private void checkIfUsernameExists(String username) {
		List<Korisnik> korisnici = korisnikRepository.findAll();
		for(Korisnik k : korisnici) {
			if(k.getUsername() == username) throw new IllegalArgumentException();
		}
		
	}

	public void dodajPutovanjeKorisniku(Long idKorisnik, Putovanje p) {
		Korisnik k = korisnikRepository.findById(idKorisnik).get();
		k.dodajPutovanjeKorisniku(p);
		korisnikRepository.save(k);
	}

	public KorisnikDTO dohvatiKorisnika(Long idKorisnik) {
		return KorisnikDTO.toDto(korisnikRepository.findById(idKorisnik).get());
	}

	public KorisnikDTO urediKorisnika(Long idKorisnik, KorisnikDTO korisnik) {
		Korisnik k = korisnikRepository.findById(idKorisnik).get();
		if (korisnik.getUsername() != null)
			k.setUsername(korisnik.getUsername());
		if (korisnik.getIme() != null)
			k.setIme(korisnik.getIme());
		if (korisnik.getPrezime() != null)
			k.setPrezime(korisnik.getPrezime());
		if (korisnik.getDatumRodenja() != null)
			k.setDatumRodenja(korisnik.getDatumRodenja());
		if (korisnik.getEmail() != null)
			k.setEmail(korisnik.getEmail());
		korisnikRepository.save(k);
		return KorisnikDTO.toDto(k);

	}

	public Korisnik dohvatiKorisnikaPoPutovanju(Long idPutovanje) {
		List<Korisnik> k = korisnikRepository.findAll();
		for(Korisnik k1 : k) {
			if(
			!k1.getPutovanja().stream().filter(putovanje -> putovanje.getIdPutovanja() == idPutovanje).toList().isEmpty()) {
				return k1;
			}
		}
		return null;
		
	}
}
