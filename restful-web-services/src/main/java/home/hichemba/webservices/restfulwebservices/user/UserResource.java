package home.hichemba.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userDaoService;

	@GetMapping(path = "/users")
	public List<User> getAllUsers() {

		return userDaoService.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public User getUser(@PathVariable Integer id) {

		User findOne = userDaoService.findOne(id);

		if (findOne == null) {
			throw new UserNotFoundException(String.format("id: %s", id));
		}

		return findOne;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(@RequestBody User newUser) {

		User savedUser = userDaoService.save(newUser);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable Integer id) {

		userDaoService.deleteById(id);
	}
}
