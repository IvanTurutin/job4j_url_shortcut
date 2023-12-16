package ru.job4j.urlshortcut.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String url;

    @Column(name = "urlkey")
    private String urlKey;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "site_id")
    /**
     * There is a circular reference Url refer to Site and Site refer to Url. It is failing while
     * serializing the Site object to JSon. Try adding com.fasterxml.jackson.annotation.JsonIgnore to Site field
     * in Site entity.
     */
    @JsonIgnore
    private Site site;

    @Override
    public String toString() {

        return "Url{"
                + "id=" + id
                + ", url='" + url + '\''
                + ", urlKey='" + urlKey + '\''
                + ", site='" + site.getName() + '\''
                + '}';
    }
}
