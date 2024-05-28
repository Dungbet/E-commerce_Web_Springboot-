package jmaster.io.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jmaster.io.DTO.DialHistoryDTO;
import jmaster.io.DTO.ResponseDTO;
import jmaster.io.Service.DialService;

@RestController
@RequestMapping("/quay-so")
public class DialController {

	@Autowired
	DialService dialService;
	@PostMapping("/")
	public ResponseDTO<Void> dial(@ModelAttribute @Valid DialHistoryDTO dialHistoryDTO){
		dialService.quaySo(dialHistoryDTO);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
}
