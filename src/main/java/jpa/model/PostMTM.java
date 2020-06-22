package jpa.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "posts_many_to_many")
@Getter
@Setter
public class PostMTM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    @Lob
    private String content;

    private Date postedAt = new Date();

    private Date createdAt = new Date();

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "post_tags",joinColumns = {@JoinColumn(name = "post_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private Set<Tag> tags = new HashSet<>();
/* post_tags is the table the stores mapping between post_many_to_many and tag table using their primary key columns*/

    /*
    * CREATE TABLE `post_tags` (
  `post_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`post_id`,`tag_id`),
  KEY `FKp7cfgjsujc2vl3p88qfqkpws6` (`tag_id`),
  CONSTRAINT `FK22g0fe59hbdm33ueaybovcdqc` FOREIGN KEY (`post_id`) REFERENCES `posts_many_to_many` (`id`),
  CONSTRAINT `FKp7cfgjsujc2vl3p88qfqkpws6` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1*/

    /*
    * We use @ManyToMany annotation to create a many-to-many relationship between two entities.
    *  In a bi-directional association, the @ManyToMany annotation is used on both the entities but only one entity can be the owner of the relationship.
    The entity that specifies the @JoinTable is the owning side of the relationship and the entity that specifies the mappedBy attribute is the inverse side.*/



    /*CREATE TABLE `post_tags` (
  `post_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`post_id`,`tag_id`),
  KEY `FKp7cfgjsujc2vl3p88qfqkpws6` (`tag_id`),
  CONSTRAINT `FK22g0fe59hbdm33ueaybovcdqc` FOREIGN KEY (`post_id`) REFERENCES `posts_many_to_many` (`id`),
  CONSTRAINT `FKp7cfgjsujc2vl3p88qfqkpws6` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1*/
}
