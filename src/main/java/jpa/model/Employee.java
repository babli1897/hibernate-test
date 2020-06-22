package jpa.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity(name = "employee")
@Data
public class Employee {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;


    /*jpa.model.EmployeeIdentity must not have @Id properties when used as an @EmbeddedId:*/
    @EmbeddedId
    private EmployeeIdentity employeeIdentity;

    /*We use JPA’s @Embedded annotation to embed a type in the model class*/
    @Embedded
    private Name name;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "zipcode", column = @Column(name = "pincode"))}) //overrides the name zipcode in embeddable type to pincode in employee table
    private Address address;

    @NotNull
    @Email
    private String email;

    /*CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1*/ // when embedded id was commented and @Id was not.
}

