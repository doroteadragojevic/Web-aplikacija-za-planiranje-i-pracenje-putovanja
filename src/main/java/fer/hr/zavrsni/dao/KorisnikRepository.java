package fer.hr.zavrsni.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fer.hr.zavrsni.domain.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{

	Korisnik findByUsername(String username);
	
}
