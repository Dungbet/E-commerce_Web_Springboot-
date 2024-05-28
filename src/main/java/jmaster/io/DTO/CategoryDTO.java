package jmaster.io.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDTO {
	private int id;
	
	@NotBlank
	@Size(min = 6, max = 20)
	private String name;
}
