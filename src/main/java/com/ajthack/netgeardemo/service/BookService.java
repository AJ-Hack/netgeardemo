package com.ajthack.netgeardemo.service;

import com.ajthack.netgeardemo.dto.BookRequest;
import com.ajthack.netgeardemo.dto.BookResponse;
import com.ajthack.netgeardemo.exception.BookNotFoundException;
import com.ajthack.netgeardemo.mapper.BookMapper;
import com.ajthack.netgeardemo.model.Book;
import com.ajthack.netgeardemo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookResponse createBook(BookRequest request) {
        Book book = bookMapper.toEntity(request);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toResponse(savedBook);
    }

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    public BookResponse getBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toResponse)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
}
