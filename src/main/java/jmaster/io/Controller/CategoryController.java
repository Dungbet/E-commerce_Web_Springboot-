package jmaster.io.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jmaster.io.DTO.CategoryDTO;
import jmaster.io.DTO.ResponseDTO;
import jmaster.io.Service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseDTO<Void> create(@ModelAttribute @Valid CategoryDTO categoryDTO){
		categoryService.create(categoryDTO);
		// khởi tạo một đối tượng builder từ lớp ResponseDTO, sử dụng Void làm kiểu dữ liệu của đối tượng. 
		//Void được sử dụng ở đây để chỉ rằng đối tượng không chứa dữ liệu cụ thể.
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
	
	@PutMapping("/")
	public ResponseDTO<Void> edit( @ModelAttribute @Valid CategoryDTO categoryDTO){
		categoryService.edit(categoryDTO);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
	@DeleteMapping("/")
	public ResponseDTO<Void> delete(@RequestParam("id") int id){
		categoryService.delete(id);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
}
