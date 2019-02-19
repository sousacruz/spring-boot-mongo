package com.synox.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synox.workshopmongo.domain.User;
import com.synox.workshopmongo.dto.UserDTO;
import com.synox.workshopmongo.repositories.UserRepository;
import com.synox.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("User not found!"));
	}
	
	public User insert(User user) {
		return repo.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User upd) {
		Optional<User> old = repo.findById(upd.getId());
		User user = old.get();
		updateData(user, upd);
		return repo.save(user);
	}
	
	private void updateData(User user, User upd) {
		user.setName(upd.getName());
		user.setEmail(upd.getEmail());
	}

	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());	
	}
}
