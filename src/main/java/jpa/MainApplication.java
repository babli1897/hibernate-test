package jpa;

import jpa.model.*;
import jpa.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*Link Reference: https://www.callicoder.com/categories/spring-boot/  */
/*mvn springboot:run runs the application , run the command from hibernatemapping folder*/
@SpringBootApplication
@EnableJpaAuditing /*to enable AuditingEntityListener*/
@Slf4j
public class MainApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository; // one to one mapping test

    @Autowired
    UserProfileRepository userProfileRepository;  // one to one mapping test

    @Autowired
    PostMTMRepository postMTMRepository;  // many to many mapping test

    @Autowired
    TagRepository tagRepository;  // many to many mapping test

    @Autowired
    EmployeeRepository employeeRepository; //Embeddable and Embedded test

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {

        userProfileRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
        SimpleDateFormat sdf =new SimpleDateFormat("yyMMdd");
        User user = new User();
        user.setEmail("cartoon.ram@gmail.com");
        user.setFirstName("Sakshi");
        user.setLastName("Kumari");
        user.setPassword("rambharose");

        UserProfile userProfile = new UserProfile();
        userProfile.setCountry("INDIA");
        userProfile.setDob(sdf.parse("970718"));
        userProfile.setEmail("cartoon@gmail.com");
        userProfile.setGender(Gender.FEMALE);
        userProfile.setZipCode("201301");
        userProfile.setUser(user);

        user.setUserProfile(userProfile);
        userRepository.save(user);


//        many to many relationship code testing

        postMTMRepository.deleteAllInBatch();
        tagRepository.deleteAllInBatch();

        // Create a Post
        PostMTM post = new PostMTM();
        post.setTitle("Hibernate Many to Many Example with Spring Boot");
        post.setContent("Learn how to map a many to many relationship using hibernate");
        post.setDescription("Entire Post content with Sample code");

        // Create two tags
        Tag tag1 = new Tag();
        tag1.setName("Spring Boot1");
        Tag tag2 = new Tag();
        tag2.setName("Hibernate1");

        Tag tag3 = new Tag();
        tag3.setName("tag3");


        // Add tag references in the post
        post.getTags().add(tag1);
        post.getTags().add(tag2);
        post.getTags().add(tag3);

        // Add post reference in the tags
        tag1.getPosts().add(post);
        tag2.getPosts().add(post);
        tag3.getPosts().add(post);

        postMTMRepository.save(post);

        /*Embeddable test*/
        Name name = new Name();
        name.setFirstName("Atharv");
        name.setMiddleName("Singh");
        name.setLastName("Rathore");

        Address address = new Address();
        address.setCity("NOIDA");
        address.setCountry("INDIA");
        address.setState("UP");
        address.setZipcode("201301");

        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setEmail("kps@gmail.com");
        employee.setAddress(address);
        employee.setName(name);

        EmployeeIdentity eid = new EmployeeIdentity();
        eid.setCompanyId("Test");
        eid.setEmployeeId("20678791");

        employee.setEmployeeIdentity(eid);


        Name riteshKumar = new Name();
        riteshKumar.setFirstName("Ritesh");
        riteshKumar.setLastName("Kumar");
        Employee ritesh = new Employee();
        ritesh.setEmail("ritesh@gmail.com");
        ritesh.setAddress(address);
        ritesh.setName(riteshKumar);

        EmployeeIdentity guruji = new EmployeeIdentity();
        guruji.setCompanyId("XYZ");
        guruji.setEmployeeId("abc123");
        dipanshu.setEmployeeIdentity(guruji);
        employeeList.add(employee);
        employeeList.add(ritesh);
        employeeRepository.saveAll(employeeList);

       
        log.info("employee details:{}",employeeRepository.findByNameFirstName("Ritesh"));
    

      }
    
}
