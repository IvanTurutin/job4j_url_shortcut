package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.dto.UrlDto;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.repository.SiteRepository;
import ru.job4j.urlshortcut.repository.UrlRepository;

import java.util.List;
import java.util.Optional;

@ThreadSafe
@Service
@AllArgsConstructor
@Slf4j
public class UrlService {
    private final UrlRepository urlRepository;
    private final SiteRepository siteRepository;

    public static final int CODE_LENGTH = 10;
    public final  static String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public List<Url> findAll() {
        return urlRepository.findAll();
    }

    public Optional<Url> findById(int id) {
        return urlRepository.findById(id);
    }

    public Optional<String> convert(UrlDto urlDto) {
        log.debug("convert(), urlDto = " + urlDto);
        Optional<Url> dbUrl = urlRepository.findByUrl(urlDto.url());
        log.debug("convert(), dbUrl.isPresent() = " + dbUrl.isPresent());
        if (dbUrl.isPresent()) {
            log.debug("convert(), dbUrl = " + dbUrl.get());

            return Optional.of(dbUrl.get().getUrlKey());
        } else {
            Optional<Url> savedUrl = save(urlDto);
            if (savedUrl.isPresent()) {
                log.debug("convert(), savedUrl = " + savedUrl.get());
                return Optional.of(savedUrl.get().getUrlKey());
            }
        }
        return Optional.empty();
    }

    public Optional<Url> save(UrlDto urlDto) {
        Optional<Site> site = checkSite(urlDto.url());
        if (site.isEmpty()) {
            log.debug("save(), site not found");
            return Optional.empty();
        }

        log.debug("save(), site = " + site.get());

        Url url = new Url();
        url.setSite(site.get());
        url.setUrl(urlDto.url());
        url.setUrlKey(codeGenerator(CODE_LENGTH));

        log.debug("save(), url = " + url);

        try {
            return Optional.of(urlRepository.save(url));
        } catch (Exception e) {
            log.error("Exception at UrlService.save()", e);
        }
        return Optional.empty();
    }


    private Optional<Site> checkSite(String url) {
        String siteName = url.split("/")[2];
        log.debug("checkSite(), siteName = " + siteName);
        return siteRepository.findByName(siteName);
    }

    /**
     * Генерирует код для URL из указанного количества символов
     * @param length длина пароля
     * @return пароль
     */
    private String codeGenerator(int length) {
        log.debug("codeGenerator(), enter in the method");


        int charSetLength = CHAR_SET.length();
        StringBuilder urlKey = new StringBuilder();

        char symb;
        for (int i = 0; i < length; i++) {
            symb = CHAR_SET.charAt((int) (charSetLength * Math.random()));
            urlKey.append(symb);
        }

        if (urlRepository.findByUrlKey(urlKey.toString()).isPresent()) {
            urlKey = new StringBuilder(codeGenerator(length));
        }

        log.debug("codeGenerator(), urlKey = " + urlKey);

        return urlKey.toString();
    }


/*
        url.setUrlKey(loginGenerator(CODE_LENGTH));
        String password = passwordGenerator(CODE_LENGTH);
        url.setPassword(encoder.encode(password));
*/

/*
        try {
            Site savedSite = urlRepository.save(url);
            savedSite.setPassword(password);
            return Optional.of(savedSite);
        } catch (Exception e) {
            log.error("Exception at SiteService.save()", e);
        }
*/




/*
    private String loginGenerator(int length) {
        StringBuilder login = new StringBuilder();
        char symb;
        for (int i = 0; i < length; i++) {
            symb = (char) (65 + (int) (26 * Math.random()));
            login.append(symb);
        }
        if (urlRepository.findByLogin(login.toString()).isPresent()) {
            login = new StringBuilder(loginGenerator(length));
        }
        return login.toString();
    }
*/

/*    public boolean update(Site site) {
        if (findById(site.getId()).isPresent()) {
            siteRepository.save(site);
            return true;
        }
        return false;
    }*/

    // дописать метод
/*
    private Site siteDtoToSite(SiteDto siteDto, Site site) {
        return new Site();
    }
*/

/*
    public boolean update(Site site) {
        Optional<Site> personDb = findById(site.getId());
        if (personDb.isEmpty()) {
            return false;
        }
        personDb.get().setPassword(site.getPassword());
        urlRepository.save(personDb.get());
        return true;
    }

    public boolean delete(Site site) {
        return urlRepository.deleteById(site.getId());
    }
*/
}
