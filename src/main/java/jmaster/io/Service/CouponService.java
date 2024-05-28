package jmaster.io.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmaster.io.DTO.CouponDTO;
import jmaster.io.Repository.CouponRepo;
import jmaster.io.entity.Coupon;


public interface CouponService {
	void create(CouponDTO coupon);
//	Coupon findByCoupon(String coupon);
}
@Service
class CouponImpl implements CouponService{

	@Autowired
	CouponRepo couponRepo;
	
//	@Override
//	public Coupon findByCoupon(String coupon) {
//		
//	}

	@Override
	public void create(CouponDTO couponDTO) {
		Coupon coupon = new ModelMapper().map(couponDTO, Coupon.class);
		couponRepo.save(coupon);
	}
	
}
