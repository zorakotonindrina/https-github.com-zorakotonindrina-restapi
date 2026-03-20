package mg.votretp.restapi.controller;

import mg.votretp.restapi.dto.ProductDTO;
import mg.votretp.restapi.model.Product;
import mg.votretp.restapi.service.JwtService;
import mg.votretp.restapi.service.ProductService;
import mg.votretp.restapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public List<Product> getAll(){
        return productService.findAll();
    }
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id){
        return productService.findById(id);
    }
//    @PostMapping
//    public Object create(
//            @RequestHeader(value="Authorization",required=false) String header,
//            @RequestBody ProductDTO dto){
//        if(header == null){
//            return Map.of("message","token missing");
//        }
//        String token = header.replace("Bearer ","");
//        if(!jwtService.isValid(token)){
//            return Map.of("message","token invalid");
//        }
//        return productService.save(dto);
//    }

    @PostMapping
    public Product create(@RequestBody ProductDTO dto){
        return productService.save(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteById(id); // méthode dans ProductService
        if (deleted) {
            return ResponseEntity.ok("Produit supprimé avec succès !");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produit introuvable !");
        }
    }

}