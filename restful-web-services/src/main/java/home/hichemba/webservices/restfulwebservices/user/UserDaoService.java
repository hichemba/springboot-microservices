package home.hichemba.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<User>();
	private static int usersCount = 0;
	
	static {
		users.add(new User(++usersCount, "Hichem", LocalDate.now().minusYears(38)));
		users.add(new User(++usersCount, "Naoures", LocalDate.now().minusYears(37)));
		users.add(new User(++usersCount, "Ahmed", LocalDate.now().minusYears(10)));
	}

	public List<User> findAll() {
		
		return users;
	}
	
	public User save(User user) {
		
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}
}
