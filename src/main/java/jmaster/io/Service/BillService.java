package jmaster.io.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmaster.io.DTO.BestUser;
import jmaster.io.DTO.BestsellingProduct;
import jmaster.io.DTO.BillDTO;
import jmaster.io.DTO.BillStatisticDTO;
import jmaster.io.Repository.BillRepo;
import jmaster.io.Repository.CouponRepo;
import jmaster.io.entity.Bill;
import jmaster.io.entity.Coupon;
import jmaster.io.jobscheduler.Jobscheduler;

public interface BillService {
	
	void create(BillDTO billDTO,String coupon, Date now);
	
	BillDTO search(int id);
	
	void update(BillDTO billDTO);
	
	void delete(int id);
	
	List<BillStatisticDTO> thongKeBill2();
	
	List<BestsellingProduct> bestsellingProduct();
	
	List<BestUser> bestUser();
}

@Service
class BillServiceImpl implements BillService{
	
	@Autowired
	BillRepo billRepo;
	
	@Autowired
	Jobscheduler jobscheduler;
	
	@Autowired
	CouponRepo couponRepo;

	private BillDTO convert(Bill bill) {
		return new ModelMapper().map(bill, BillDTO.class);
	}
	@Override
	public BillDTO search(int id) {
		Bill bill = billRepo.search(id);
		return new ModelMapper().map(bill, BillDTO.class);
	}

	@Override
	public void update(BillDTO billDTO) {
		Bill bill = billRepo.findById(billDTO.getId()).orElse(null);
		
		if(bill != null) {
			bill.setStatus(billDTO.getStatus());
			bill.setBillItems(bill.getBillItems());
		}
		
	}

	@Override
	public void delete(int id) {
		billRepo.deleteById(id);
		
	}
	@Override
	public List<BestsellingProduct> bestsellingProduct() {
		return billRepo.bestsellingProduct();
	}
	@Override
	public List<BestUser> bestUser() {
		return billRepo.bestUser();
	}
	@Override
	public void create(BillDTO billDTO, String coupon, Date now) {
		Coupon coupon2 = couponRepo.findByCoupon(coupon,now);
		Bill bill = new ModelMapper().map(billDTO, Bill.class);
			if(coupon2 != null) {
				bill.setCouponCode(coupon2.getCouponCode());
				bill.setDiscount(coupon2.getDiscountAmount());
			}
			else {
				bill.setCouponCode(null);
				bill.setDiscount(0);
			}
			billRepo.save(bill);
		
		}
		
	@Override
	public List<BillStatisticDTO> thongKeBill2() {
		try {
			jobscheduler.exportExcel();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return billRepo.thongKeBill2();
	}
	
}
