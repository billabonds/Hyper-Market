package com.example.Hyper.Market.Repository;

import com.example.Hyper.Market.Model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered,Integer> {

}
