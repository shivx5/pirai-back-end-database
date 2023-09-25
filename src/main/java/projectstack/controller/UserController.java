package projectstack.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projectstack.implimentation.UserServiceImpl;
import projectstack.model.User;
import projectstack.model.UserRequest;
import projectstack.model.UserResponse;
import projectstack.repository.UserRepository;
import projectstack.util.jwtUtil;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping
public class UserController {

	@Autowired
	private UserServiceImpl useservice;
	
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private jwtUtil util;
	
	//1.save user data in database
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user)
	{
		Set<String> role=new HashSet<>();
		role.add("user");
		user.setRoles(role);
		Integer id=useservice.saveUser(user);
		String body="User "+id+" saved";
		return ResponseEntity.ok(body);
		
	}
	
	@GetMapping("/userPresent")
	public  ResponseEntity<Boolean> userPresent()
	{
		return ResponseEntity.ok(true);
	}

	
	@PostMapping("/login")
	public ResponseEntity<UserResponse>loginUser(@RequestBody UserRequest user)
	{
//		System.out.println(user.getEmail());
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
		      String token= util.generateToken(user.getEmail());
		      return ResponseEntity.ok(
		    		  new UserResponse(token,"success")
		    		  );
	}
	
	@GetMapping("/welcome")
	public ResponseEntity<String>accessdata(Principal p)
	{
		return ResponseEntity.ok("helo user"+p.getName());
	}
	
	@PostMapping("/updateUser")
	public ResponseEntity<String> SetUpdateDetails(@RequestBody User updated ,Principal p)
	{
//		System.out.println(p.getName());
		System.out.println(updated.getCountry());
		System.out.println(updated.getCity());
		System.out.println(updated.getFirstName());
		System.out.println(updated.getLastName());
		System.out.println(updated.getState());
		System.out.println(updated.getAddress());
		System.out.println();

		System.out.println(updated.getEmail());
		System.out.println(updated.getAddress());
		
		
		Optional<User> user= userrepo.findByEmail(p.getName());
		if(user.isPresent())
		{
			User existinguser=user.get();
			existinguser.setAddress(updated.getAddress());
			existinguser.setCity(updated.getCity());
			existinguser.setCountry(updated.getCountry());
			existinguser.setFirstName(updated.getFirstName());
			existinguser.setLastName(updated.getLastName());
			existinguser.setState(updated.getState());
			System.out.println(existinguser.getAddress());
			userrepo.save(existinguser);
			return ResponseEntity.ok("updated");
		}
		return ResponseEntity.ok("no such user present");
		
		
		
		
		
	}
	
	@GetMapping("/getupdatedUser")
	public Optional<User> updateduser(Principal p)
	{
		    Optional<User> user=userrepo.findByEmail(p.getName());
		    if(user.isPresent())
		    { 
		    	return user;
		    	
		    }
		  
		    else {
		    	
		    	 return null;
		    }
		    
	}
	
}
