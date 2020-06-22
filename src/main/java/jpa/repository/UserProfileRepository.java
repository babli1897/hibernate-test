package jpa.repository;

import jpa.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*JpaRepository<class_name, id_class> id_class cannot have primitive value*/
/*@Repository is optional, can remove this*/
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
}
