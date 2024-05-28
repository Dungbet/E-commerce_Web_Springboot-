package jmaster.io.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jmaster.io.DTO.ProductDTO;
import jmaster.io.DTO.ResponseDTO;
import jmaster.io.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/")
	public ResponseDTO<List<ProductDTO>> searchByName(@RequestParam("name") String name){
		return ResponseDTO.<List<ProductDTO>>builder().status(200).data(productService.searchByName(name)).build();
	}
	
	@PostMapping("/")
	public ResponseDTO<Void> create(@ModelAttribute @Valid ProductDTO productDTO ) throws IllegalStateException, IOException{
		if(!productDTO.getFile().isEmpty()) {
			// Lấy tên tệp tin gốc của hình ảnh từ đối tượng ProductDTO.
			String filename = productDTO.getFile().getOriginalFilename();
			
			// Luu file vao o cung may chu
			File saveFile = new File("D:/" + filename);
			//Di chuyển tệp tin hình ảnh được tải lên vào vị trí đã được chỉ định bởi đối tượng saveFile.
			productDTO.getFile().transferTo(saveFile);
			
			// Lay ten file luu xuong database
			productDTO.setImage(filename);//set o day
		}
		productService.create(productDTO);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
	
	@PutMapping("/")
	public ResponseDTO<Void> edit(@ModelAttribute @Valid ProductDTO productDTO) throws IllegalStateException, IOException{
		if(!productDTO.getFile().isEmpty()) {
			// Lấy tệp tin gốc của hình ảnh từ DTO
			String filename = productDTO.getFile().getOriginalFilename();
			// Tạo ra địa chỉ lưu file
			File saveFile = new File("D:/" + filename);
			// Di chuyển tệp tải lên vào vị trí lưu
			productDTO.getFile().transferTo(saveFile);
			// Lấy tên xuống dtb
			productDTO.setImage(filename);
		}
		productService.edit(productDTO);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
	
	@DeleteMapping("/")
	public ResponseDTO<Void> delete(@RequestParam("id") int id){
		productService.detele(id);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
}
