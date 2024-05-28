package jmaster.io.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import jmaster.io.DTO.CategoryDTO;
import jmaster.io.Repository.CategoryRepo;
import jmaster.io.entity.Category;

public interface CategoryService {
	Page<List<CategoryDTO>> searchByName(String s);
	
	void create(CategoryDTO categoryDTO);
	
	void edit (CategoryDTO categoryDTO);
	
	void delete( int id);
}

@Service
class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public void create(CategoryDTO categoryDTO) {
		Category category = new ModelMapper().map(categoryDTO, Category.class);
		categoryRepo.save(category);
		
		
	}

	@Override
	public void edit(CategoryDTO categoryDTO) {
		Category category = categoryRepo.findById(categoryDTO.getId()).orElse(null);
		
		if(category != null) {
			category.setId(categoryDTO.getId());
			category.setName(categoryDTO.getName());
		}
		
	}

	@Override
	public void delete(int id) {
		categoryRepo.deleteById(id);
		
	}

	private CategoryDTO convert(Category category) {
		return new ModelMapper().map(category, CategoryDTO.class);
	}
	@Override
	public Page<List<CategoryDTO>> searchByName(String s) {
//		Sort sortName = Sort.by("name").ascending();
//		
//		PageRequest pageRequest = PageRequest.of(0, 5,sortName);
//		 List<CategoryDTO> categoryDTOs  = categoryDTOs.get().map(u -> convert(u)).collect(Collectors.toList());
		return null;
	}
	
}
