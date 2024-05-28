package jmaster.io.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jmaster.io.DTO.BestUser;
import jmaster.io.DTO.BestsellingProduct;
import jmaster.io.DTO.BillDTO;
import jmaster.io.DTO.BillStatisticDTO;
import jmaster.io.DTO.ResponseDTO;
import jmaster.io.DTO.UserDTO;
import jmaster.io.Service.BillService;

@RestController
@RequestMapping("/bill")
public class BillController {

	@Autowired
	BillService billService;
	
	@PostMapping("/")
	public ResponseDTO<Void> create(@ModelAttribute @Valid BillDTO billDTO,@RequestParam("coupon") String coupon, @RequestParam("datenow") Date now){
		billService.create(billDTO,coupon, now);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
	
	@GetMapping("/")
	public ResponseDTO<BillDTO> search(@RequestParam("id") int id){
		
		return ResponseDTO.<BillDTO>builder().status(200).data(billService.search(id)).build();
	}
	
	@PutMapping("/")
	public ResponseDTO<Void> update(@ModelAttribute @Valid BillDTO billDTO){
		billService.update(billDTO);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
	
	@DeleteMapping("/")
	public ResponseDTO<Void> delete(@RequestParam("id") int id){
		billService.delete(id);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
	
	@GetMapping("/bestselling-product")
	public ResponseDTO<List<BestsellingProduct>> bestsellingProduct(){
		return ResponseDTO.<List<BestsellingProduct>>builder().status(200).data(billService.bestsellingProduct()).build();
	}
	@GetMapping("/best-user")
	public ResponseDTO<List<BestUser>> bestUser(){
		return ResponseDTO.<List<BestUser>>builder().status(200).data(billService.bestUser()).build();
	}
	
	@GetMapping("/thong-ke")
	public ResponseDTO<List<BillStatisticDTO>> thongKeBill(){
		
		return ResponseDTO.<List<BillStatisticDTO>>builder().status(200).data(billService.thongKeBill2()).build();
	}
}
