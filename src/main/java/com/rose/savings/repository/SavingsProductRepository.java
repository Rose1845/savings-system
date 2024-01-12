package com.rose.savings.repository;

import com.rose.savings.model.entity.SavingsProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsProductRepository extends JpaRepository<SavingsProduct,Long> {
    boolean existsByName(String name);
}
