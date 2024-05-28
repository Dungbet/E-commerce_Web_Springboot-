package jmaster.io.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import jmaster.io.DTO.UserDTO;
import jmaster.io.Repository.UserRepo;
import jmaster.io.entity.Role;
import jmaster.io.entity.User;


public interface UserService {
	UserDTO findByUsername(String username);
	void create(UserDTO userDTO);
	void update(UserDTO userDTO);
	void delete(int id);
	UserDetails loadUserByUsername(String username);
	
	UserDTO search(int id);
}

@Service
class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	UserRepo userRepo;
	
	@Override
	public void create(UserDTO userDTO) {
		User user = new ModelMapper().map(userDTO, User.class);
		user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
		userRepo.save(user);
		
	}

	@Override
	public void update(UserDTO userDTO) {
		User user = userRepo.findById(userDTO.getId()).orElse(null);
		
		if(user != null) {
			user.setEmail(userDTO.getEmail());
			user.setName(userDTO.getName());
			user.setAvatar(userDTO.getAvatar());
			user.setBirthdate(userDTO.getBirthdate());
			user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
			
			userRepo.save(user);
		}
		
	}

	@Override
	public void delete(int id) {
		userRepo.deleteById(id);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User userEntity = userRepo.findByUsername(username);
		if(userEntity == null) {
			throw new UsernameNotFoundException("Not Foud");
		}
		
		// convert userEntity _> user details
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		// Chuyển vai trò về quyền trong security
		for( Role role : userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new org.springframework.security.core.userdetails.User(username, userEntity.getPassword(), authorities);
	}

	@Override
	public UserDTO findByUsername(String username) {
		User user = userRepo.findByUsername(username);
		if(user == null) throw new NoResultException();
		return new ModelMapper().map(user, UserDTO.class);
		
	}

	@Override
	public UserDTO search(int id) {
		User user = userRepo.getById(id);
		return new ModelMapper().map(user, UserDTO.class);
	}
	
}

