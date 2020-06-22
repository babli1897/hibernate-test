package jpa.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "user_profile")
@Data
public class UserProfile {

    /*primary key declaration , auto_increment*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /* when table is created via code, sets type of column phone_number as varchar(12)* and check of not null is also added/
    @NotNull
    @Size(max=12)
    private String phoneNumber;

    /* variables can have datatype as enum.  EnumType.ORDINAL for integers. EnumType.STRING for varchar*/
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /*TemporalType.DATE stores only date part and not the time*/
    @Temporal(TemporalType.TIMESTAMP)
    private Date dob;

    /*adds not null check in column and defines it as unique*/
    /*ConstraintViolationImpl{interpolatedMessage='must be a well-formed email address', propertyPath=email, rootBeanClass=class jpa.model.UserProfile, messageTemplate='{javax.validation.constraints.Email.message}*/
    @NotNull
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    private String country;

    @NotNull
    private String zipCode;

    /*owner of the relationship contains a @JoinColumn annotation to specify the foreign key column,
     and the inverse-side of the relationship contains a mappedBy attribute to indicate that the relationship is mapped by the other entity.
    column name "user_id" will map to table mapped by User class and this value
    cannot be null(refers to id value of user table since it is primary key */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /* table created via code

    show create table user_profile;
    CREATE TABLE `user_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `country` varchar(255) NOT NULL,
  `dob` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ebc21hy5j7scdvcjt0jy6xxrv` (`user_id`),
  UNIQUE KEY `UK_tcks72p02h4dp13cbhxne17ad` (`email`),
  CONSTRAINT `FKuganfwvnbll4kn2a3jeyxtyi` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1
    * */
}
