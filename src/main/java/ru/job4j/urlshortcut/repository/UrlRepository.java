package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Url;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Integer> {
    List<Url> findAll();
    Optional<Url> findByUrl(String url);
    Optional<Url> findByUrlKey(String urlKey);
}
