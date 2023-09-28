package ru.job4j.urlshortcut.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    /*@Min(value = 1, message = "Id must be not null", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class})
     */
    private int id;

    /*@NotBlank(message = "Name must be not empty")*/
    private String url;

    /*@NotBlank(message = "Login must be not empty")*/
    private String urlKey;

    /*@Length(min = 6, message = "Password must be more than 5 symbols")*/
    private int count;

    /*@Min(value = 1, message = "Count must be not null", groups = {
            Operation.OnUpdate.class})
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id")
    private Site site;


}
