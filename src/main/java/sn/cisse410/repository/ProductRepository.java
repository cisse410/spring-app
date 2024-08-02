package sn.cisse410.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.cisse410.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
