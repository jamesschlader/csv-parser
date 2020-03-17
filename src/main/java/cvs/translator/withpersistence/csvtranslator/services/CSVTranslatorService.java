package cvs.translator.withpersistence.csvtranslator.services;

import cvs.translator.withpersistence.csvtranslator.TranslatorScanner;
import cvs.translator.withpersistence.csvtranslator.entities.IncomingText;
import cvs.translator.withpersistence.csvtranslator.entities.IncomingTextRepository;
import cvs.translator.withpersistence.csvtranslator.parser.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CSVTranslatorService {

    private CSVParser csvParser;
    private TranslatorScanner translatorScanner;
    private final IncomingTextRepository repository;

    @Autowired
    public CSVTranslatorService(CSVParser csvParser, TranslatorScanner translatorScanner, IncomingTextRepository repository) {
        this.csvParser = csvParser;
        this.translatorScanner = translatorScanner;
        this.repository = repository;
    }

    public String translateCSV(String csv, String type) {
        List<List<String>> result = csvParser.parseCSV (csv);
        Set<String> keys = translatorScanner.getTranslatorMap().keySet();
        if(!keys.contains(type)) type = "bracket";
        String translation = translatorScanner.getTranslatorMap().get(type).Translate(result);
        IncomingText saved = repository.findByText(translation);
        if(saved == null){
            saved = new IncomingText();
            saved.setText(translation);
            repository.save(saved);
        }
        return saved.getText();
    }

    public String deleteCSV(String csv) {
        IncomingText target = repository.findByText(csv);
        if (target == null){
            return "No matching record";
        }
        repository.delete(target);
        return "Deleted " + target.getText() + " from the database";
    }

    public String getAll() {
        return repository.findAll().toString();
    }
}
