package com.example.Hyper.Market.Repository;

import com.example.Hyper.Market.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
