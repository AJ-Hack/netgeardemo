package com.ajthack.netgeardemo.mapper;

import com.ajthack.netgeardemo.dto.BookRequest;
import com.ajthack.netgeardemo.dto.BookResponse;
import com.ajthack.netgeardemo.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book toEntity(BookRequest request) {
        return Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publishedDate(request.getPublishedDate())
                .genre(request.getGenre())
                .build();
    }

    public BookResponse toResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publishedDate(book.getPublishedDate())
                .genre(book.getGenre())
                .createdAt(book.getCreatedAt())
                .build();
    }
}
