package cvs.translator.withpersistence.csvtranslator.parser;

import cvs.translator.withpersistence.csvtranslator.entities.IncomingText;
import cvs.translator.withpersistence.csvtranslator.entities.IncomingTextRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CSVParser {

    @Autowired
    private IncomingTextRepository repository;

    public List<List<String>> parseCSV(String csv) {
        saveIncomingCSV(csv);
        return generateParsedResponse(csv);
    }

    private List<List<String>> generateParsedResponse(String csv) {
        List<List<String>> response = new ArrayList<>();
        List<String> responseList;
        String carriageFree = csv.replace("\r", "");
        responseList = new ArrayList<>(Arrays.asList(carriageFree.split("\n")));
        responseList = responseList.stream().filter(item -> !item.isEmpty()).collect(Collectors.toList());
        responseList.forEach(text -> response.add(prepareOutput(new ArrayList<>(Arrays.asList(replaceInternalQuotes(text).split(","))))));
        return response;
    }

    private String replaceInternalQuotes(String text) {
        boolean inQuotes = false;
        char[] input = text.toCharArray();
        StringBuilder output = new StringBuilder();

        for (char c : input) {
            if (inQuotes && c == ',') {
                output.append("@@@");
            } else {
                output.append(c);
            }
            if (c == '"') {
                inQuotes = !inQuotes;
            }
        }

        return output.toString();
    }

    private List<String> prepareOutput(List<String> strings) {
        return strings
                .stream()
                .map(item -> item.replace('"', ' ').trim())
                .map(item -> item.replace("@@@", ","))
                .collect(Collectors.toList());
    }

    private void saveIncomingCSV(String csv) {
        IncomingText savedText = repository.findByText(csv);
        if (savedText == null) {
            savedText = new IncomingText();
            savedText.setText(csv);
            repository.saveAndFlush(savedText);
        }
        repository.findAll().forEach(item->log.info(item.toString()));
        log.info("-----------------------------------------\n");
    }

}
