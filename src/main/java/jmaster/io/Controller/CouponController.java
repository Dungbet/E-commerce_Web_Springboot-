package jmaster.io.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jmaster.io.DTO.CouponDTO;
import jmaster.io.DTO.ResponseDTO;
import jmaster.io.Service.CouponService;

@RestController
@RequestMapping("/coupon")
public class CouponController {

	@Autowired
	CouponService couponService;
	
	@PostMapping("/")
	public ResponseDTO<Void> create(@ModelAttribute @Valid CouponDTO couponDTO){
		couponService.create(couponDTO);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
}
