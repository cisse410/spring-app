package sn.cisse410.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.cisse410.model.Product;
import sn.cisse410.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * @param product
     * @return
     */
    public Product saveSingleProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * @param products
     * @return
     */
    public List<Product> saveManyProduct(List<Product> products) {
        return productRepository.saveAll(products);
    }

    /**
     * @return
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    /**
     * @param id
     * @return
     */
    public String deleteProduct(int id) {
        productRepository.deleteById(id);
        return "Product deleted successfully";
    }

    public Product updateProduct(Product product) {
        Product prod = productRepository.findById(product.getId()).get();
        prod.setId(product.getId());
        prod.setName(product.getName());
        prod.setQuantity(product.getQuantity());
        prod.setPrice(product.getPrice());

        return productRepository.save(prod);
    }
}
