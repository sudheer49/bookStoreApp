package com.check24.bookStoreApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.check24.bookStoreApp.entity.User;

/**
 * 
 * @author Satya Kolipaka 
 * Jpa Reposritory for User entiry
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
