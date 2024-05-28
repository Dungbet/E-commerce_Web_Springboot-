package jmaster.io.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmaster.io.DTO.DialHistoryDTO;
import jmaster.io.Repository.DialHistoryRepo;
import jmaster.io.Repository.RewardRepo;
import jmaster.io.Repository.UserRepo;
import jmaster.io.entity.DialHistory;
import jmaster.io.entity.Reward;
import jmaster.io.entity.User;

public interface DialService {

	void quaySo(DialHistoryDTO dialHistoryDTO);
}
@Service
class DialServiceImpl implements DialService{
	
	@Autowired
	RewardRepo rewardRepo;
	
	@Autowired
	DialHistoryRepo dialHistoryRepo;
	
	@Autowired
	UserRepo userRepo;
	
	
	@Override
	public void quaySo(DialHistoryDTO dialHistoryDTO) {
		List<String> phanThuong = new ArrayList<>() ;
		for( int i = 0; i <= rewardRepo.count() ;i++) {
			Reward reward = rewardRepo.findById(i).orElse(null);
			if(reward != null) {
				phanThuong.add(reward.getTenPhanThuong());
			}
			
		}
		List<Integer> tiLePhanThuong= new ArrayList<Integer>();
		for( int i = 0; i <= rewardRepo.count() ;i++) {
			Reward reward = rewardRepo.findById(i).orElse(null);
			if(reward != null) {
				tiLePhanThuong.add(reward.getTiLeTrung());
			}
		}
		
		// Khởi tạo một đối tượng Random
	    Random random = new Random();

	    // Lấy số ngẫu nhiên từ 1 đến 100
	    int soNgauNhien = random.nextInt(100) + 1;

	    // Kiểm tra xem số ngẫu nhiên rơi vào tỉ lệ nào và trả về phần thưởng tương ứng
	    int idPhanThuongNhanDuoc;
	    
	    //Xác xuất tích lũy
	    int cumulativeProbability = 0;
	    for (int i = 0 ; i < tiLePhanThuong.size() ; i++) {
	        cumulativeProbability += tiLePhanThuong.get(i);
	        if (soNgauNhien <= cumulativeProbability) {
	        	idPhanThuongNhanDuoc = rewardRepo.findByName(phanThuong.get(i));
	        	
	        	//System.out.println(rewardRepo.findByName(phanThuong.get(i)));
	        	DialHistory dialHistory= new DialHistory();
	        	dialHistory.setReward(rewardRepo.findById(idPhanThuongNhanDuoc).orElse(null));
	        	
	        	User user = userRepo.findById(dialHistoryDTO.getUser().getId()).orElse(null);
	        	dialHistory.setUser(user);
	        	
	        	dialHistoryRepo.save(dialHistory);
	        }
	    }
		
	}

}