package com.fashion.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fashion.Exception.UserNotFoundException;
import com.fashion.models.dto.UserDto;
import com.fashion.models.entity.User;
import com.fashion.models.mapper.UserMapper;
import com.fashion.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService service;

	@GetMapping("/users")
	public List<UserDto> list() {
		List<User> list = service.findAll();
		if (list.isEmpty()) {
			return new ArrayList<>();
		}
		return UserMapper.INSTANCE.toDtoList(list);

	}
	
	@PostMapping("/user")
	public UserDto insert(@RequestBody UserDto dto) {
		User user = service.save(dto);
		return UserMapper.INSTANCE.toDto(user);
	}
	
	@PutMapping("/user/{id}/upd")
	public ResponseEntity<String> update(@PathVariable("id") @Min(1) Integer id, @RequestBody UserDto dto){
		User user = service.findById(id).orElseThrow(()-> new UserNotFoundException());
		User nUser = UserMapper.INSTANCE.toEntity(dto);
		nUser.setId(dto.getId());
		service.save(UserMapper.INSTANCE.toDto(nUser));
		return ResponseEntity.ok().body("User with "+ id+" updated!!");
	}
	
	@GetMapping("/user/{id}/del")
	public ResponseEntity<String> delete(@PathVariable("id") @Min(1) Integer id){
		User user = service.findById(id).orElseThrow(()-> new UserNotFoundException());
		service.deleteById(user.getId());
		return ResponseEntity.ok().body("User with "+ id + " deleted!!!");
	}
}
