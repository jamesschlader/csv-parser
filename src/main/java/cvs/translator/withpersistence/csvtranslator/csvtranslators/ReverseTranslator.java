package cvs.translator.withpersistence.csvtranslator.csvtranslators;

import cvs.translator.withpersistence.csvtranslator.interfaces.Translator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("reverse")
public class ReverseTranslator implements Translator {

    @Override
    public String Translate(List<List<String>> source){
        StringBuilder response = new StringBuilder();
        source.forEach(list -> list.forEach(string->response.append(reverse(string))));
        return response.toString();
    }

    private StringBuilder reverse (String s){
        char[] start = s.toCharArray();
        StringBuilder reversed = new StringBuilder();
        for (int i = start.length - 1; i > -1; i--) reversed.append(start[i]);
        return reversed;
    }

}
