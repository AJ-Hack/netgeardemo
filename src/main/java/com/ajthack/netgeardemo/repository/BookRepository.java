package com.ajthack.netgeardemo.repository;

import com.ajthack.netgeardemo.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.deleted = true WHERE b.id = :id")
    void softDelete(@Param("id") Long id);

    Optional<Book> findByIdAndDeletedFalse(Long id);

    Page<Book> findAllByDeletedFalse(Pageable pageable);
}
