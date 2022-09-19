package com.example.Banking.application1.repository;

import com.example.Banking.application1.entity.MyBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBankRepository extends JpaRepository<MyBank,Long> {

@Query(" from MyBank where accountNumber=:accountNumber")
  MyBank findByaccountNumber(@Param("accountNumber") String accountNumber);
}
