package cvs.translator.withpersistence.csvtranslator;

import cvs.translator.withpersistence.csvtranslator.interfaces.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class TranslatorScanner {

    private Map<String, Translator> translatorMap = new HashMap<>();

    @Autowired
    public void setTranslators(List<Translator> trans){
        trans.forEach(translator -> {
            String annotation = translator.getClass().getAnnotation(Component.class).value().compareTo("") == 0 ?
                    translator.getClass().getSimpleName().toLowerCase().replace("translator", "") :
                    translator.getClass().getAnnotation(Component.class).value();

            translatorMap.putIfAbsent(annotation, translator);
        });
    }

    public Map<String, Translator> getTranslatorMap(){
        return this.translatorMap;
    }
}
