package jmaster.io.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jmaster.io.entity.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

	@Query("Select c FROM Coupon c WHERE couponCode = :x AND c.expiredDate >= :y ")
	Coupon findByCoupon(@Param("x") String couponCode, @Param("y") Date now);
}
