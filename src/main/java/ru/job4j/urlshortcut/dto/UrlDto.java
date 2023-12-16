package ru.job4j.urlshortcut.dto;

import org.hibernate.validator.constraints.URL;
import ru.job4j.urlshortcut.validation.Operation;

import javax.validation.constraints.Pattern;

public record UrlDto(
        @URL(message = "Invalid Url, Please provide a valid URL", groups = {
                Operation.OnCreate.class
        })
        String url,
        //@Size(max = 10, min = 10, message = "Invalid urlKey, Size should be 10")
        @Pattern(regexp = "^[A-Z0-9],{10}$", message = "Invalid urlKey, invalid characters used", groups = {
                Operation.OnConvert.class
        })
        String urlKey
) {
}
