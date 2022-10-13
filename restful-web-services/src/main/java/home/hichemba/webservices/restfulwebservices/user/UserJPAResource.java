package home.hichemba.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserJPAResource {

	@Autowired
	private UserRepository userDaoRepository;

	@Autowired
	private PostRepository postDaoRepository;

	@GetMapping(path = "/jpa/users")
	public List<User> getAllUsers() {

		return userDaoRepository.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> getUser(@PathVariable Integer id) {

		Optional<User> findOne = userDaoRepository.findById(id);

		if (findOne.isEmpty()) {
			throw new UserNotFoundException(String.format("id: %s", id));
		}

		EntityModel<User> entityModel = EntityModel.of(findOne.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
		entityModel.add(link.withRel("all-users"));

		return entityModel;
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User newUser) {

		User savedUser = userDaoRepository.save(newUser);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable Integer id) {

		userDaoRepository.deleteById(id);
	}

	@GetMapping("/jpa/users/{id}/posts")
	List<Post> getPostsByUser(@PathVariable Integer id) {

		// return postDaoRepository.findByUserId(id);

		Optional<User> findOne = userDaoRepository.findById(id);

		if (findOne.isEmpty()) {
			throw new UserNotFoundException(String.format("id: %s", id));
		}

		return findOne.get().getPosts();
	}

	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<User> createPostForUser(@PathVariable Integer id, @Valid @RequestBody Post newPost) {

		Optional<User> findOne = userDaoRepository.findById(id);

		if (findOne.isEmpty()) {
			throw new UserNotFoundException(String.format("id: %s", id));
		}

		newPost.setUser(findOne.get());

		Post savedPost = postDaoRepository.save(newPost);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
