package cvs.translator.withpersistence.csvtranslator.csvtranslators;

import cvs.translator.withpersistence.csvtranslator.interfaces.Translator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("raw")
public class RawTranslator implements Translator {

    @Override
    public String Translate(List<List<String>> source) {
        StringBuilder returnString = new StringBuilder();
        source.forEach(list -> list.forEach(text->returnString.append(text + ",")));
        returnString.deleteCharAt(returnString.length() - 1);
        return returnString.toString().trim();
    }
}
