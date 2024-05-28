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
import jmaster.io.DTO.BillItemsDTO;
import jmaster.io.DTO.ResponseDTO;
import jmaster.io.Service.BillItemsService;

@RestController
@RequestMapping("/bill-items")
public class BillItemsController {

	@Autowired
	BillItemsService billItemsService;
	
	@PostMapping("/")
	public ResponseDTO<Void> create(@ModelAttribute @Valid BillItemsDTO billItemsDTO){
		billItemsService.create(billItemsDTO);
		// khởi tạo một đối tượng builder từ lớp ResponseDTO, sử dụng Void làm kiểu dữ liệu của đối tượng. 
		//Void được sử dụng ở đây để chỉ rằng đối tượng không chứa dữ liệu cụ thể.
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
	
	@PutMapping("/")
	public ResponseDTO<Void> edit( @ModelAttribute @Valid BillItemsDTO billItemsDTO){
		billItemsService.update(billItemsDTO);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
	@DeleteMapping("/")
	public ResponseDTO<Void> delete(@RequestParam("id") int id){
		billItemsService.delete(id);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
}
