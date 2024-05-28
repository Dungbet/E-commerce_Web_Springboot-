package jmaster.io.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jmaster.io.DTO.BestUser;
import jmaster.io.DTO.BestsellingProduct;
import jmaster.io.DTO.BillStatisticDTO;
import jmaster.io.entity.Bill;

public interface BillRepo extends JpaRepository<Bill, Integer> {

	@Query("SELECT b FROM Bill b WHERE b.id = :id")
	Bill search(@Param("id") int id);
	
	//Thống kê Doanh số
//	@Query("SELECT count(b.id), month(b.createdAt), year(b.createdAt) "
//			+ "FROM Bill b GROUP BY month(b.createdAt), year(b.createdAt)")
//	List<Object[]> thongKeBill2();
//	
	@Query("SELECT new jmaster.io.DTO.BillStatisticDTO(count(b.id), MONTH(b.createdAt), YEAR(b.createdAt))"
			+ "FROM Bill b GROUP BY MONTH(b.createdAt), YEAR(b.createdAt)")
	List<BillStatisticDTO> thongKeBill2();
	
	//Sản phẩm Bán chạy
	@Query("SELECT new jmaster.io.DTO.BestsellingProduct(p.id, p.name, SUM(bi.quantity)) "
			+ "FROM Bill b JOIN b.billItems bi JOIN bi.product p GROUP BY p.id, p.name")
	List<BestsellingProduct> bestsellingProduct();
	
	//Khách hàng chi tiêu
	@Query("SELECT new jmaster.io.DTO.BestUser(b.user.id, b.user.name, SUM(bi.buyPrice)) "
			+ "FROM Bill b JOIN b.billItems bi GROUP BY b.user.id, b.user.name")
	List<BestUser> bestUser();
//	@Query("SELECT new jmaster.io.DTO.BestUser(u.id, u.name, SUM(b.billItems.buyPrice)) "
//	        + "FROM Bill b JOIN b.billItems bi JOIN bi.user u GROUP BY u.id, u.name")
//	List<BestUser> bestUser();

}
