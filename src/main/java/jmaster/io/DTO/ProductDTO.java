package jmaster.io.DTO;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jmaster.io.entity.Category;
import lombok.Data;

@Data
public class ProductDTO {

	private int id;
	
	@NotBlank
	private String name;
	@Min(0)
	private double price;
	private String description;
	private Category category;
	private String image;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	
	//khi đối tượng User được chuyển đổi thành JSON, thuộc tính "file" sẽ không xuất hiện trong kết quả JSON.
	@JsonIgnore
	private MultipartFile file;
	
}
