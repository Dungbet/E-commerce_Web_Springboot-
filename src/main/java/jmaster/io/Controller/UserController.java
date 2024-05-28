package jmaster.io.Controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jmaster.io.DTO.ResponseDTO;
import jmaster.io.DTO.UserDTO;
import jmaster.io.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public ResponseDTO<UserDTO> search(@RequestParam("id") int id){
		return ResponseDTO.<UserDTO>builder().status(200).data(userService.search(id)).build();
	}
	@PostMapping("/")
	public ResponseDTO<Void> create(@ModelAttribute @Valid UserDTO userDTO) throws IllegalStateException, IOException{
		if(!userDTO.getFile().isEmpty()) {
			String filename = userDTO.getFile().getOriginalFilename();
			File saveFile = new File("D:/" + filename);
			
			userDTO.getFile().transferTo(saveFile);
			userDTO.setAvatar(filename);
		}
		userService.create(userDTO);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
	
	@PutMapping("/")
	public ResponseDTO<Void> edit(@ModelAttribute @Valid UserDTO userDTO){
		userService.update(userDTO);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
	
	@DeleteMapping("/")
	public ResponseDTO<Void> delete(@RequestParam("id") int id){
		userService.delete(id);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
		
	}
}
