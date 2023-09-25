package projectstack.implimentation;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import projectstack.model.User;
import projectstack.repository.UserRepository;
import projectstack.service.IUserService;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder pwdencode;
	
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public Integer saveUser(User user) {
	
		user.setPassword(pwdencode.encode(user.getPassword()));
		return repository.save(user).getId();
	}

	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<User> opt=findByEmail(email);
		//read user from database
		if(opt.isEmpty())
			throw new UsernameNotFoundException("User not Exist");
		User user=opt.get();

		return new org.springframework.security.core.userdetails.User
				(email, user.getPassword(), user.getRoles().stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
	}

//	@Override
//	public Optional<User> findByEmail(String email) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}

	

	

}
