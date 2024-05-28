package jmaster.io.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CouponDTO {

	int id;
	String couponCode;
	double discountAmount;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "Asia/Ho_Chi_Minh") 
	Date expiredDate;
}
