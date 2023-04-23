package com.example.Hyper.Market.Repository;

import com.example.Hyper.Market.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

    Seller findByEmailId(String email);
}
