package com.example.Banking.application1.repository;

import com.example.Banking.application1.entity.LoginDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository  extends JpaRepository<LoginDetail,Long> {


  LoginDetail findByuserNameAndPassword(String userName, String password);
}
