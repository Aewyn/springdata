package be.aewyn.springdata.repositories;

import be.aewyn.springdata.domain.Werknemer;
import be.aewyn.springdata.projections.AantalWerknemersPerFamilienaam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long> {
    List<Werknemer> findByFiliaalGemeente(String gemeente);

    @EntityGraph(value = "Werknemer.metFiliaal")
    List<Werknemer> findByVoornaamStartingWith(String woord);

    List<AantalWerknemersPerFamilienaam> findAantalWerknemersPerFamilienaam();

    Page<Werknemer> findAll(Pageable pageable);
}
