package ma.alten.testAlten.business.controllers;


import ma.alten.testAlten.business.aop.annotation.AdminPermission;
import ma.alten.testAlten.business.domain.Product;
import ma.alten.testAlten.business.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @AdminPermission
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @AdminPermission
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    @AdminPermission
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
