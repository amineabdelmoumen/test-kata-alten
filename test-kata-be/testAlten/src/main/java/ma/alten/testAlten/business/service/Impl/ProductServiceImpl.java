package ma.alten.testAlten.business.service.Impl;

import ma.alten.testAlten.business.domain.Product;
import ma.alten.testAlten.business.repository.ProductRepository;
import ma.alten.testAlten.business.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



/**
 * Service implementation for managing products.
 * Provides methods to retrieve, save, and delete products.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * Constructs a new ProductServiceImpl with the given repository.
     *
     * @param productRepository the repository used for product data access
     */
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves all products from the repository.
     *
     * @return a list of all products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return an Optional containing the found product, or empty if not found
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Saves a product in the repository.
     * If the product already exists, it will be updated.
     *
     * @param product the product to save
     * @return the saved product
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
