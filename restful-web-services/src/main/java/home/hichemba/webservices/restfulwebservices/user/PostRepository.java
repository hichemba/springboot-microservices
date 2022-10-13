package home.hichemba.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUserId(Integer id);
}
