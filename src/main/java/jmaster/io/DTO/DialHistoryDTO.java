package jmaster.io.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jmaster.io.entity.Reward;
import jmaster.io.entity.User;
import lombok.Data;

@Data
public class DialHistoryDTO {

	private int id;
	private Reward reward;
	private User user;
	
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date thoiGianChoi;
}
