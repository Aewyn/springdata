package be.aewyn.springdata.repositories;

import be.aewyn.springdata.domain.Filiaal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface FiliaalRepository extends JpaRepository<Filiaal, Long>{
    //Je erft je repository van JpaRepository. Je type tussen <> je object type en het type van de PK

    List<Filiaal> findByGemeenteOrderByNaam(String gemeente);
    List<Filiaal> findByOmzetGreaterThanEqual(BigDecimal vanaf);

    int countByGemeente(String gemeente);

    @Query("select avg(f.omzet) from Filiaal f")
    BigDecimal findGemiddeldeOmzet();
}
