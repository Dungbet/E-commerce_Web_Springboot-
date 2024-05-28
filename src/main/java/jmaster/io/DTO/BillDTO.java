package jmaster.io.DTO;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jmaster.io.entity.User;
import lombok.Data;

@Data
public class BillDTO {
	private int id;
	private String status;
	
//	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "Asia/Ho_Chi_Minh")
//	private Date createdAt;
	private User user;
	
	
	//@JsonManagedReference được sử dụng để xác định mối quan hệ quản lý giữa đối tượng BillDTO 
	//và danh sách billItems khi chuyển đổi giữa JSON và đối tượng Java.
	@JsonManagedReference // Nếu khoong dùng ko gen được json, Có jsonreference phải có jsonback, bên class nào chính thì cho referent
	private List<BillItemsDTO> billItems;
	
	String couponCode;
	double discount;
}
