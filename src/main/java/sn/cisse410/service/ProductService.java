package sn.cisse410.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.cisse410.exception.ProductNotFoundException;
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
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Aucun produits trouvé");
        }
        return products;
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
    public void deleteProduct(int id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Aucun produit trouvé avec l'id: " + id);
        }
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product product) {
        if (!productRepository.existsById(product.getId())) {
            throw new ProductNotFoundException("Aucun produit trouvé avec l'id: " + product);
        }

        return productRepository.save(product);
    }
}
