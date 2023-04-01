package com.lumedeirosn.imersaospring.Repositorys;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lumedeirosn.imersaospring.Entities.LanguageAttributes;

public interface LanguagesAttributesRepository extends MongoRepository<LanguageAttributes, String> {
    List<LanguageAttributes> findByOrderByRanking();
}
