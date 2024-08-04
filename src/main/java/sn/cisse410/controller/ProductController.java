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
import sn.cisse410.response.ResponseHandler;
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
    public ResponseEntity<Object> saveSingleProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveSingleProduct(product);
        return ResponseHandler.customResponse("Produit cree avec succes", HttpStatus.CREATED, savedProduct);
    }

    /**
     * @param products
     * @return
     */
    @PostMapping(path = "/products")
    public ResponseEntity<Object> saveManyProducts(@RequestBody List<Product> products) {
        List<Product> savedProducts = productService.saveManyProduct(products);
        return ResponseHandler.customResponse("Produits crees avec succes", HttpStatus.CREATED, savedProducts);
    }

    /**
     * @return
     */
    @GetMapping(path = "/products")
    public ResponseEntity<Object> getAllProduct() {
        List<Product> products = productService.getAllProducts();
        return ResponseHandler.customResponse("Voici la liste des produits", HttpStatus.OK, products);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping(path = "/product/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Aucun produit trouvé avec l'id " + id));

        return ResponseHandler.customResponse("Voici les informations de: " + product.getName(), HttpStatus.OK,
                product);
    }

    /**
     * @param product
     * @return
     */
    @PutMapping(path = "/product/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable int id, @RequestBody Product product) {
        if (id != product.getId()) {
            throw new ProductNotFoundException("Aucune correspondance");
        }
        Product updatedProduct = productService.updateProduct(product);
        return ResponseHandler.customResponse("Produit modifie avec succes", HttpStatus.OK, updatedProduct);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping(path = "/product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseHandler.customResponse("Produit supprime avec succes", HttpStatus.NO_CONTENT, id);
    }
}
