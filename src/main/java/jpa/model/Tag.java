package jpa.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "tag")
@Getter
@Setter
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NaturalId // store in db in sorted order of name, in ascending order
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<PostMTM> posts = new HashSet<>();

    /*
    * We use @ManyToMany annotation to create a many-to-many relationship between two entities.
    *  In a bi-directional association, the @ManyToMany annotation is used on both the entities but only one entity can be the owner of the relationship.
    The entity that specifies the @JoinTable is the owning side of the relationship and the entity that specifies the mappedBy attribute is the inverse side.*/

    /*
    *  CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qp93jyuw586kcgdajsb3tfbjy` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1*/

}
