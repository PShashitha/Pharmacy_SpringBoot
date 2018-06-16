package SpringBootWithMongo.SpringBootWithMongo.service;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import SpringBootWithMongo.SpringBootWithMongo.model.User;
import SpringBootWithMongo.SpringBootWithMongo.model.UserRepository;

@RestController
@RequestMapping("/login/")
public class UserService {
		@Autowired
		private UserRepository repository;
		
//		@RequestMapping(method = RequestMethod.POST)
//		public void AddUser(@RequestBody User instance) {
//			try {
//				repository.save(instance);
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		@SuppressWarnings("finally")
		@RequestMapping(method = RequestMethod.GET)
		public List<User> GetAllUsers() {
			List<User> userList = null;
			try {
				userList = repository.findAll();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				return userList;
			}
		}
		
		@SuppressWarnings("finally")
		@RequestMapping(value = "/{Id}", method = RequestMethod.GET)
		public User getSingle(@PathVariable String Id) {
			User instance = null;
			try {
				instance = this.findOne(Id);
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				return instance;
			}
		}
		@SuppressWarnings("finally")
		@RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
		public User getUserByUserName(@PathVariable String username) {
			User instance = null;
			try {
				instance = this.findOne(username);
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				return instance;
			}
		}
		@SuppressWarnings("finally")
		@RequestMapping(value = "/password/{password}", method = RequestMethod.GET)
		public User getAuthentication(@PathVariable String password) {
			User instance = null;
			try {
				String encoded = Base64.getEncoder().encodeToString(password.getBytes());
				instance = this.findOne(encoded);
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				return instance;
			}
		}
		
		
		@RequestMapping(value = "/{Id}", method = RequestMethod.PUT)
		public void updateUser(@RequestBody User instance, @PathVariable String Id) {
			try {
				User updateInstance = this.findOne(Id);
				updateInstance.setName(instance.getName());
				repository.save(updateInstance);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		@RequestMapping(value = "/{Id}", method = RequestMethod.DELETE)
		public void deleteUser(@PathVariable String Id) {
			try {
				repository.delete(this.findOne(Id));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings("finally")
		private User findOne(String Id) {
			User instance = null;
			try {
				List<User> userList = repository.findAll();
				for(User user: userList) {
					if(user.getId().equals((Id))) {
						instance =  user;
						break;
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				return instance;
			}
		}
}
