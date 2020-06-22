package jpa.repository;

import jpa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    /*When you use an embeddable type and you need to query the Entity using a field of the embeddable type,
     then you can specify query methods like this -*/

    List<Employee> findByNameFirstName(String firstName);

    List<Employee> findByNameLastName(String lastName);

}


/*  The JpaRepository interface contains methods for all the CRUD operations on the entity.
    Spring Boot automatically injects an implementation of this interface called SimpleJpaRepository at runtime.
    This helps us perform all the CRUD operations on the entity without implementing anything ourselves.*/
