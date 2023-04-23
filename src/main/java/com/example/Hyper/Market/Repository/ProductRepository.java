package com.example.Hyper.Market.Repository;

import com.example.Hyper.Market.Enum.ProductCategory;
import com.example.Hyper.Market.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByProductCategory(ProductCategory productCategory);
}
