package be.aewyn.springdata.repositories;

import be.aewyn.springdata.domain.Werknemer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long> {
    List<Werknemer> findByFiliaalGemeente(String gemeente);

    @EntityGraph(value = "Werknemer.metFiliaal")
    List<Werknemer> findByVoornaamStartingWith(String woord);
}
