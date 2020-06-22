package jpa.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@Data
/*Composite-id class must implement Serializable, mandatory when used as @EmbeddedId*/
public class EmployeeIdentity implements Serializable {

    @NotNull
    private String employeeId;

    @NotNull
    private String companyId;
}

/* CREATE TABLE `employee` (
  `company_id` varchar(255) NOT NULL,
  `employee_id` varchar(255) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`company_id`,`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1*/
