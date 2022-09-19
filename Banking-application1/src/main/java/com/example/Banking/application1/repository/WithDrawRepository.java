package com.example.Banking.application1.repository;

import com.example.Banking.application1.entity.WithDraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithDrawRepository  extends JpaRepository<WithDraw,Long> {

}
