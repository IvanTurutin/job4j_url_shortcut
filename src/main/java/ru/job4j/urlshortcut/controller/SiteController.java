package ru.job4j.urlshortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.dto.SiteDto;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.service.SiteService;
import ru.job4j.urlshortcut.validation.Operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/sites")
@Slf4j
public class SiteController {

    private final SiteService sites;
    private final ObjectMapper objectMapper;


    @GetMapping("/all")
    public ResponseEntity<List<Site>> findAll() {
        List<Site> siteList = sites.findAll();
        return new ResponseEntity<>(
                siteList,
                siteList.isEmpty() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<Site> findById(@PathVariable int id) {
        return ResponseEntity.of(sites.findById(id));
    }

    @PostMapping(value = "/registration", consumes = {"application/json"})
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<String> create(@Valid @RequestBody SiteDto site) {
        Optional<Site> siteOptional = sites.save(site);
        String body;
        if (siteOptional.isPresent()) {
            body = new HashMap<>() {{
                put("registration", "true");
                put("login", siteOptional.get().getLogin());
                put("password", siteOptional.get().getPassword());
            }}.toString();

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(body);
        } else {
            body = new HashMap<>() {{
                put("registration", "false");
                put("login", "N/A");
                put("password", "N/A");
            }}.toString();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(body);
        }
    }

    @PutMapping("/")
    /*@Validated(Operation.OnUpdate.class)

     */
    public ResponseEntity<Void> update(@Valid @RequestBody Site site) {
        return this.sites.update(site)
                ? ResponseEntity.ok().build()
                : ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Site site = new Site();
        site.setId(id);
        return this.sites.delete(site) ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        log.error(e.getLocalizedMessage());
    }
}
