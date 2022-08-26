package be.aewyn.springdata.repositories;

import be.aewyn.springdata.domain.Filiaal;
import be.aewyn.springdata.domain.Werknemer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Sql({"/insertFilialen.sql","/insertWerknemers.sql"})
class WerknemerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String WERKNEMERS = "werknemers";
    private final WerknemerRepository repository;

    public WerknemerRepositoryTest(WerknemerRepository repository){
        this.repository = repository;
    }

    @Test
    void findByFiliaalGemeente(){
        var antwerpen = "Antwerpen";
        assertThat(repository.findByFiliaalGemeente(antwerpen))
                .hasSize(countRowsInTableWhere(WERKNEMERS, "filiaalId = (select id from filialen where gemeente = 'Antwerpen')"))
                .first()
                .extracting(Werknemer::getFiliaal)
                .extracting(Filiaal::getGemeente)
                .isEqualTo(antwerpen);
    }

    @Test
    void findByVoornaamStringStartingWith(){
        assertThat(repository.findByVoornaamStartingWith("J"))
                .hasSize(countRowsInTableWhere(WERKNEMERS, "voornaam like 'J%'"))
                .allSatisfy(werknemer -> assertThat(werknemer.getVoornaam().toUpperCase()).startsWith("J"))
                .extracting(Werknemer::getFiliaal)
                .extracting(Filiaal::getNaam);
    }

    @Test
    void eerstePagina(){
        var page = repository.findAll(PageRequest.of(0,2));
        assertThat(page.getContent()).hasSize(2);
        assertThat(page.hasPrevious()).isFalse();
        assertThat(page.hasNext()).isTrue();
    }

    @Test
    void tweedePagina(){
        var page = repository.findAll(PageRequest.of(1,2));
        assertThat(page.getContent()).hasSize(1);
        assertThat(page.hasPrevious()).isTrue();
        assertThat(page.hasNext()).isFalse();
    }
}