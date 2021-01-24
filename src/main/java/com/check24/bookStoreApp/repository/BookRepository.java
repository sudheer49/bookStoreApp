package com.check24.bookStoreApp.repository;

import org.springframework.stereotype.Repository;

import com.check24.bookStoreApp.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * 
 * @author Satya Kolipaka 
 * Jpa Reposritory for Book entiry
 *
 */
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

}
