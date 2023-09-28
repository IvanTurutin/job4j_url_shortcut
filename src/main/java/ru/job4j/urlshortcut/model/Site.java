package ru.job4j.urlshortcut.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
//import ru.job4j.auth.validation.Operation;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "sites")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    /*@Min(value = 1, message = "Id must be not null", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class})
     */
    private int id;

    /*@NotBlank(message = "Name must be not empty")*/
    private String name;

    /*@NotBlank(message = "Login must be not empty")*/
    private String login;

    /*@Length(min = 6, message = "Password must be more than 5 symbols")*/
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "site", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.SUBSELECT)
    private List<Url> urls = new ArrayList<>();


}
