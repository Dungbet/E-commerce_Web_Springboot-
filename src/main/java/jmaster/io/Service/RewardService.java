package jmaster.io.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmaster.io.DTO.RewardDTO;
import jmaster.io.Repository.RewardRepo;
import jmaster.io.entity.Reward;

public interface RewardService {
	void create(RewardDTO rewardDTO);
	void update(RewardDTO rewardDTO);
	void delete(int id);
}
@Service
class RewardServiceImpl implements RewardService{

	@Autowired
	RewardRepo rewardRepo;
	
	@Override
	public void create(RewardDTO rewardDTO) {
		Reward reward = new ModelMapper().map(rewardDTO, Reward.class);
		rewardRepo.save(reward);
	}

	@Override
	public void update(RewardDTO rewardDTO) {
		Reward reward = rewardRepo.findById(rewardDTO.getId()).orElse(null);
		
		if(reward != null) {
			reward.setTenPhanThuong(rewardDTO.getTenPhanThuong());
			reward.setTiLeTrung(rewardDTO.getTiLeTrung());
		}
		
	}

	@Override
	public void delete(int id) {
		rewardRepo.deleteById(id);
	}
	
}
