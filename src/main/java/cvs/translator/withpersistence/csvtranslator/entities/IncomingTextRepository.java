package cvs.translator.withpersistence.csvtranslator.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface IncomingTextRepository extends JpaRepository<IncomingText, Long> {

    List<IncomingText> findAllByCreateDateAfter(Timestamp createDate);
    IncomingText findByText(String text);
    IncomingText deleteByText (String text);
}
