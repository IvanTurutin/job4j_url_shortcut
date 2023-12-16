package ru.job4j.urlshortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.dto.UrlDto;
import ru.job4j.urlshortcut.service.UrlService;
import ru.job4j.urlshortcut.validation.Operation;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/urls")
@Slf4j
public class UrlController {

    private final UrlService urls;
    private final ObjectMapper objectMapper;

    @PostMapping(value = "/convert", consumes = {"application/json"})
    @Validated(Operation.OnConvert.class)
    public ResponseEntity<String> convert(@Valid @RequestBody UrlDto urlDto) {
        Optional<String> urlKeyOptional = urls.convert(urlDto);

        String body;
        if (urlKeyOptional.isPresent()) {
            body = new HashMap<>() {{
                put("code", urlKeyOptional.get());
            }}.toString();

            return ResponseEntity.status(HttpStatus.OK)
                    .body(body);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
}
