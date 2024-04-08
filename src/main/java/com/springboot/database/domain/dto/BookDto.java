package com.springboot.database.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.database.domain.AuthorEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

    private String title;
    private AuthorDto author;
    private String isbn;
    @JsonProperty("year")
    private String yearPublished;
}
