package jmaster.io.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmaster.io.DTO.BillItemsDTO;
import jmaster.io.Repository.BillItemsRepo;
import jmaster.io.entity.BillItems;

public interface BillItemsService {

	void create(BillItemsDTO billItemsDTO);
	
	void update(BillItemsDTO billItemsDTO);
	
	void delete(int id);
}
@Service
class BillItemsServiceImpl implements BillItemsService{
	
	@Autowired
	BillItemsRepo billitemrepo;

	@Override
	public void create(BillItemsDTO billItemsDTO) {
		BillItems billItems = new ModelMapper().map(billItemsDTO, BillItems.class);
		billitemrepo.save(billItems);
		
	}

	@Override
	public void update(BillItemsDTO billItemsDTO) {
		BillItems billItems = billitemrepo.findById(billItemsDTO.getId()).orElse(null);
		if(billItems != null) {
			billItems.setBuyPrice(billItemsDTO.getBuyPrice());
			billItems.setQuantity(billItemsDTO.getQuantity());
			billitemrepo.save(billItems);
		}
		
	}

	@Override
	public void delete(int id) {
		billitemrepo.deleteById(id);
		
	}
	
}
