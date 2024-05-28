package jmaster.io.Service;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jmaster.io.DTO.PageDTO;
import jmaster.io.DTO.RoleDTO;
import jmaster.io.DTO.SearchDTO;
import jmaster.io.Repository.RoleRepo;
import jmaster.io.entity.Role;

public interface RoleService {
	
	void create(RoleDTO roleDTO);
	void update (RoleDTO roleDTO);
	void delete(int id);
	PageDTO<RoleDTO> search (SearchDTO searchDTO);
	
}

@Service
class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleRepo roleRepo; 
	
	@Transactional 
	public void create(RoleDTO roleDTO) {
		Role role = new ModelMapper().map(roleDTO, Role.class);
		roleRepo.save(role);
	}

	@Transactional
	public void delete(int id) {
		roleRepo.deleteById(id);
	}

	@Transactional
	public void update(RoleDTO roleDTO) {
		// check xem tồn tại hay không
		Role role = roleRepo.findById(roleDTO.getId()).orElse(null);

		if (role != null) {
			role.setName(roleDTO.getName());
			roleRepo.save(role);
		}
	}
	
	private RoleDTO convert(Role role) {
		return new ModelMapper().map(role, RoleDTO.class);
	}

	public PageDTO<RoleDTO> search(SearchDTO searchDTO) {
		PageRequest pageRequest = PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getSize());
		Page<Role> page = roleRepo.searchByName("%" + searchDTO.getKeyword() + "%", pageRequest); 
				
		return PageDTO.<RoleDTO>builder()
				.totalPages(page.getTotalPages())
				.totalElements(page.getTotalElements())
				.data(page.get().map(u -> convert(u)).collect(Collectors.toList()))
				.build();
		
	}
}
