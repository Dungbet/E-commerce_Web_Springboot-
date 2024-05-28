package jmaster.io.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Bill extends TimeAuditable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String status; // New, pending, active
	
	@ManyToOne
	private User user;
	
	// orphanRemoval được đặt thành true, khi một đối tượng "BillItems" bị gỡ bỏ khỏi danh sách "billItems" 
	// của "Bill", nó sẽ tự động bị xóa khỏi cơ sở dữ liệu.
	// Billitem ứng với 1 sản phẩm trong giỏ hàng
	// bill lấy list bilitem rồi xóa 1 phần tử thì trong bảng Billitem
	@OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true )
	private List<BillItems> billItems;
	String couponCode;
	double discount;
}
