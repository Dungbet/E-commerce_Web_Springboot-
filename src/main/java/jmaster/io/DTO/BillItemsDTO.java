package jmaster.io.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class BillItemsDTO {

	private int id;
	
	// C1:
	@JsonBackReference // không cần lấy billdto
	//C2:
	//@JsonIgnoreProperties("billItems")
	private BillDTO billDTO;
	
	private ProductDTO product;
	@Min(0)
	private double quantity;
	@Min(0)
	private double buyPrice;
}
