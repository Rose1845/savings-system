package com.rose.savings.repository;

import com.rose.savings.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByIdNumber(String idNumber);
    boolean existsByMemberNumber(String memberNumber);
}
