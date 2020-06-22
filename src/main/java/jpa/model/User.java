package jpa.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/*
@Entity only -> creates or find table on tha basis of class name(lower case)
@Entity(name="entity_1") only -> creates or finds table named entity_1 in the database
@Entity(name="entity")  @Table(name="table_name") both -> creates or finds table with name table_name in the database
* */

// creates or finds a table name "users" in the database //
@Entity(name = "user_detail")
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    /*CascadeType.ALL -> creates entry in user_profile table if save operation is performed on users table with userProfile variable populated*/
   /*
    fetch = FetchType.LAZY - Fetch the related entity lazily from the database.
    cascade = CascadeType.ALL - Apply all cascading effects to the related entity.
    That is, whenever we update/delete a User entity, update/delete the corresponding UserProfile as well.
    mappedBy = “user” - We use mappedBy attribute in the User entity to tell hibernate that the User entity is not responsible for this relationship and
     It should look for a field named user in the UserProfile entity to find the configuration for the JoinColumn/ForeignKey column.
   * */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private UserProfile userProfile;


    /* table created via code
    show create table users;
    *  CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1
    * */

}
