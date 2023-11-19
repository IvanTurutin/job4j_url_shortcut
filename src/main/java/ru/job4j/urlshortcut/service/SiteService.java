package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.dto.SiteDto;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;

import java.util.List;
import java.util.Optional;

@ThreadSafe
@Service
@AllArgsConstructor
@Slf4j
public class SiteService {
    private final SiteRepository siteRepository;
    private final BCryptPasswordEncoder encoder;
    public static final int PASSWORD_LENGTH = 5;

    public List<Site> findAll() {
        return siteRepository.findAll();
    }

    public Optional<Site> findById(int id) {
        return siteRepository.findById(id);
    }

    public Optional<Site> save(SiteDto siteDto) {
        Site site = new Site();
        site.setName(siteDto.site());
        site.setLogin(loginGenerator(PASSWORD_LENGTH));
        String password = passwordGenerator(PASSWORD_LENGTH);
        site.setPassword(encoder.encode(password));

        try {
            Site savedSite = siteRepository.save(site);
            savedSite.setPassword(password);
            return Optional.of(savedSite);
        } catch (Exception e) {
            log.error("Exception at SiteService.save()", e);
        }
        return Optional.empty();
    }

    /**
     * Генерирует пароль из указанного количества цифр
     * @param length длина пароля
     * @return пароль
     */
    private String passwordGenerator(int length) {
        StringBuilder password = new StringBuilder();
        char symb;
        for (int i = 0; i < length; i++) {
            symb = (char) (48 + (int) (10 * Math.random()));
            password.append(symb);
        }
        return password.toString();
    }

    private String loginGenerator(int length) {
        StringBuilder login = new StringBuilder();
        char symb;
        for (int i = 0; i < length; i++) {
            symb = (char) (65 + (int) (26 * Math.random()));
            login.append(symb);
        }
        if (siteRepository.findByLogin(login.toString()).isPresent()) {
            login = new StringBuilder(loginGenerator(length));
        }
        return login.toString();
    }

/*    public boolean update(Site site) {
        if (findById(site.getId()).isPresent()) {
            siteRepository.save(site);
            return true;
        }
        return false;
    }*/

    // дописать метод
    private Site siteDtoToSite(SiteDto siteDto, Site site) {
        return new Site();
    }

    public boolean update(Site site) {
        Optional<Site> personDb = findById(site.getId());
        if (personDb.isEmpty()) {
            return false;
        }
        personDb.get().setPassword(site.getPassword());
        siteRepository.save(personDb.get());
        return true;
    }

    public boolean delete(Site site) {
        return siteRepository.deleteById(site.getId());
    }
}
