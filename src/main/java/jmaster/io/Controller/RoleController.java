package jmaster.io.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jmaster.io.DTO.PageDTO;
import jmaster.io.DTO.ResponseDTO;
import jmaster.io.DTO.RoleDTO;
import jmaster.io.DTO.SearchDTO;
import jmaster.io.Service.RoleService;


@RestController 
@RequestMapping("/admin/role")
public class RoleController {
		@Autowired 
		RoleService roleService;

		@PostMapping("/")
		public ResponseDTO<Void> create(@RequestBody @Valid RoleDTO roleDTO){
			roleService.create(roleDTO);
			return ResponseDTO.<Void>builder().status(200).msg("ok").build();
		}
		
		
		@DeleteMapping("/{id}") // @pathparam thay vì hỏi chấm ta sẽ truyền kiểu : role/12
		public ResponseDTO<Void> detele(@PathVariable("id") int id) {
			roleService.delete(id);
			return ResponseDTO.<Void>builder().status(200).msg("ok").build();
		}
		
		@PutMapping("/")
		public ResponseDTO<Void> edit(@RequestBody @Valid RoleDTO roleDTO) {
			roleService.update(roleDTO);
			return ResponseDTO.<Void>builder().status(200).msg("ok").build() ;
		}
		
		@PostMapping("/search") // so su dung thu vien jackson de convert ve dang json
		public ResponseDTO<PageDTO<RoleDTO> > search( @RequestBody @Valid SearchDTO searchDTO) {
			return ResponseDTO.<PageDTO<RoleDTO>>builder().status(200).data(roleService.search(searchDTO)).build();
		}
}
