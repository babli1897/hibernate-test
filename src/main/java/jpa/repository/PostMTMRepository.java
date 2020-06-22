package jpa.repository;

import jpa.model.PostMTM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMTMRepository extends JpaRepository<PostMTM,Long> {
}
