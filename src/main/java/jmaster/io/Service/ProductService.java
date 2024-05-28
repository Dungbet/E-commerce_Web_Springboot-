package jmaster.io.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmaster.io.DTO.ProductDTO;
import jmaster.io.Repository.ProductRepo;
import jmaster.io.entity.Product;

public interface ProductService {
	void create(ProductDTO productDTO);
	
	void edit( ProductDTO productDTO );
	
	void detele(int id);
	
	List<ProductDTO> searchByName(String name);
}

@Service
class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepo productRepo;
	
	@Override
	public void create(ProductDTO productDTO) {
		Product product = new ModelMapper().map(productDTO, Product.class);
		productRepo.save(product);
		
	}

	@Override
	public void edit(ProductDTO productDTO) {
		// Check xem có đối tượng đó không
		Product product = productRepo.findById(productDTO.getId()).orElse(null);
				
			if(product != null) {
				product.setDescription(productDTO.getDescription());
				product.setName(productDTO.getName());
				product.setPrice(productDTO.getPrice());
				
				productRepo.save(product);
			}
		
	}

	@Override
	public void detele(int id) {
		productRepo.deleteById(id);
		
	}
	private ProductDTO convert(Product product) {
		return new ModelMapper().map(product, ProductDTO.class);
	}
	
	@Override
	public List<ProductDTO> searchByName(String name) {
	    List<Product> products = productRepo.searchByName(name);
	    List<ProductDTO> productDTOs = products.stream()
	            .map(product -> convert(product))
	            .collect(Collectors.toList());
	    return productDTOs;
	}

	
}