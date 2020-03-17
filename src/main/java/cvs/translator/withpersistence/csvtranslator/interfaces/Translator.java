package cvs.translator.withpersistence.csvtranslator.interfaces;

import java.util.List;

public interface Translator {
    public String Translate(List<List<String>> source);
}

