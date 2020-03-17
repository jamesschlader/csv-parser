package cvs.translator.withpersistence.csvtranslator.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class IncomingText {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length=1000, unique=true)
    private String text;
    private Timestamp createDate = new Timestamp(System.currentTimeMillis());
    private Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
}
