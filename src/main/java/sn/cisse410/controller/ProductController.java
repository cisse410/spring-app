package sn.cisse410.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sn.cisse410.exception.ProductNotFoundException;
import sn.cisse410.model.Product;
import sn.cisse410.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * @param product
     * @return
     */
    @PostMapping(path = "/product")
    public ResponseEntity<Product> saveSingleProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveSingleProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    /**
     * @param products
     * @return
     */
    @PostMapping(path = "/products")
    public ResponseEntity<List<Product>> saveManyProducts(@RequestBody List<Product> products) {
        List<Product> savedProducts = productService.saveManyProduct(products);
        return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
    }

    /**
     * @return
     */
    @GetMapping(path = "/products")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping(path = "/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Aucun produit trouvé avec l'id " + id));
        // Ceci est aussi est une possibilite une ResponseEntity (C'est la maniere
        // statique)
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    /**
     * @param product
     * @return
     */
    @PutMapping(path = "/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        if (id != product.getId()) {
            throw new ProductNotFoundException("Aucune correspondance");
        }
        Product updatedProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping(path = "/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
