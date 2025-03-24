package com.ajthack.netgeardemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.ZonedDateTime;

@Data
@Builder
public class BookResponse {
    private Long id;
    private String title;
    private String author;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime publishedDate;

    private String genre;
    private ZonedDateTime createdAt;
}
