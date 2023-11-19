package ru.job4j.urlshortcut.dto;

import org.hibernate.validator.constraints.Length;

public record SiteDto(
        @Length(min = 3, message = "Site name must be more than 2 symbols")
        String site
) {
}
