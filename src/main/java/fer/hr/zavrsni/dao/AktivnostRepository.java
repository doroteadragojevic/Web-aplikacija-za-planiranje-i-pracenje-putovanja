package fer.hr.zavrsni.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fer.hr.zavrsni.domain.Aktivnost;

public interface AktivnostRepository extends JpaRepository<Aktivnost, Long>{

}
