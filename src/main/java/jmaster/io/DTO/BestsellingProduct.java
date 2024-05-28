package jmaster.io.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestsellingProduct {
	private int idProduct;
	private String nameProduct;
	private double soLuongBan;
}
