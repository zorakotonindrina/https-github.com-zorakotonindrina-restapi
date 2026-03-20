package mg.votretp.restapi.service;

import mg.votretp.restapi.dto.ProductDTO;
import mg.votretp.restapi.model.Product;
import mg.votretp.restapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    public List<Product> findAll(){
        return repository.findAll();
    }
    public Product findById(Long id){
        return repository.findById(id).orElseThrow();
    }


    public Product save(Product product){
        return repository.save(product);
    }


    public Product save(ProductDTO dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return repository.save(product);
    }

    public boolean deleteById(Long id){
        Optional<Product> product = repository.findById(id);
        if(product.isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
