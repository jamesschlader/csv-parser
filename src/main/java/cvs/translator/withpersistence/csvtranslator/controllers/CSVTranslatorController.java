package cvs.translator.withpersistence.csvtranslator.controllers;

import cvs.translator.withpersistence.csvtranslator.services.CSVTranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CSVTranslatorController {

    @Autowired
    private CSVTranslatorService csvTranslatorService;

    @GetMapping("/translateCSV")
    public String translateCSV(@RequestParam("csv") String csv, @RequestParam(required = false, value ="type") String type){
        return  csvTranslatorService.translateCSV(csv, type);
    }

    @DeleteMapping("/translateCSV/delete")
    public String deleteCSV(@RequestParam("csv") String csv){
        return csvTranslatorService.deleteCSV(csv);
    }

    @GetMapping("translateCSV/all")
    public String getAll(){
        return csvTranslatorService.getAll();
    }
}
