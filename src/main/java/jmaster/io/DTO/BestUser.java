package jmaster.io.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestUser {
	private int idUserName;
	private String nameUser;
	private double amountSpent;
}
