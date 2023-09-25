package projectstack.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import projectstack.model.User;


public interface IUserService {
	
	Integer saveUser(User user);
//	Optional<User>findByUsername(String username);
	Optional<User>findByEmail(String email);

}
