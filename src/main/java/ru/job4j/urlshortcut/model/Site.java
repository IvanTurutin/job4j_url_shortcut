package ru.job4j.urlshortcut.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Модель данных Сайт
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "sites")
public class Site {

    /**
     * Идентификатор сайта
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    /*@Min(value = 1, message = "Id must be not null", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class})
     */
    private int id;

    /**
     * Название сайта
     */
    /*@NotBlank(message = "Name must be not empty")*/
    private String name;

    /**
     * Логин сайта
     */
    /*@NotBlank(message = "Login must be not empty")*/
    private String login;

    /**
     * Пароль сайта
     */
    /*@Length(min = 6, message = "Password must be more than 5 symbols")*/
    private String password;

    /**
     * Список ссылок, принадлежащих сайту
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "site_id")
    private List<Url> urls = new ArrayList<>();
}
