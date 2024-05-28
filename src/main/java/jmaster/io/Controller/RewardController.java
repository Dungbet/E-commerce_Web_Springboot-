package jmaster.io.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jmaster.io.DTO.ResponseDTO;
import jmaster.io.DTO.RewardDTO;
import jmaster.io.Service.RewardService;

@RestController
@RequestMapping("/reward")
public class RewardController {

	@Autowired
	RewardService rewardService;
	
	@PostMapping("/")
	public ResponseDTO<Void> dial(@ModelAttribute @Valid RewardDTO rewardDTO){
		rewardService.create(rewardDTO);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
}
