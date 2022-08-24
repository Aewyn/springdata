package be.aewyn.springdata.domain;

import javax.persistence.*;

@Entity
@Table(name = "werknemers")
public class Werknemer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long voornaam;
    private long familienaam;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "filiaalId")
    private Filiaal filiaal;

    public long getId() {
        return id;
    }

    public long getVoornaam() {
        return voornaam;
    }

    public long getFamilienaam() {
        return familienaam;
    }

    public Filiaal getFiliaal() {
        return filiaal;
    }
}
