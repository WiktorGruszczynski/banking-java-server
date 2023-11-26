package com.bank.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bank.server.models.RegisterVerification;

import jakarta.transaction.Transactional;




public interface RegisterVerificationDao extends JpaRepository<RegisterVerification, Long>{
    RegisterVerification findByEmail(String email);
    
    
    @Modifying
    @Transactional
    @Query("DELETE FROM RegisterVerification e WHERE e.email = ?1")
    Integer deleteByEmail(String email);
}
