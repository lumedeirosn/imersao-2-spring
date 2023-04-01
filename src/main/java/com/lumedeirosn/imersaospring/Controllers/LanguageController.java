package com.lumedeirosn.imersaospring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.lumedeirosn.imersaospring.Entities.LanguageAttributes;
import com.lumedeirosn.imersaospring.Repositorys.LanguagesAttributesRepository;

@RestController
public class LanguageController {
    
    @Autowired
    private LanguagesAttributesRepository repository;

    @PostMapping(value = "/languages")
    public ResponseEntity<LanguageAttributes> postLanguage(@RequestBody LanguageAttributes language) {
        return new ResponseEntity<>(repository.save(language), HttpStatus.CREATED);
    }

    @GetMapping(value = "/languages")
    public List<LanguageAttributes> getLanguage() {
        return repository.findByOrderByRanking();
    }


    @GetMapping(value = "/languages/{id}")
    public LanguageAttributes getLanguageByID(@PathVariable String id) {
        return repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }

    @PutMapping(value = "/languages/{id}")
    public LanguageAttributes updateLanguageAttributes(@PathVariable String id, @RequestBody LanguageAttributes language) {
        LanguageAttributes languageToUpdate = repository
            .findById(id)
            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
        
        languageToUpdate.setId(id);
        languageToUpdate.setTitle(language.getTitle());
        languageToUpdate.setImage(language.getImage());
        languageToUpdate.setRanking(language.getRanking());
        return repository.save(languageToUpdate);
    }
    
    @DeleteMapping(value = "/languages/{id}")
    public void deleteLanguageAtributes(@PathVariable String id) {
        repository.deleteById(id);
    }
}
