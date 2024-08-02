package sn.cisse410.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sn.cisse410.model.Product;
import sn.cisse410.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * @param product
     * @return
     */
    @PostMapping(path = "/product")
    public Product saveSingleProduct(@RequestBody Product product) {
        return productService.saveSingleProduct(product);
    }

    /**
     * @param products
     * @return
     */
    @PostMapping(path = "/products")
    public List<Product> saveManyProducts(@RequestBody List<Product> products) {
        return productService.saveManyProduct(products);
    }

    /**
     * @return
     */
    @GetMapping(path = "/products")
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    /**
     * @param id
     * @return
     */
    @GetMapping(path = "/product/{id}")
    public Optional<Product> getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    /**
     * @param product
     * @return
     */
    @PutMapping(path = "/product")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping(path = "/product/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}
