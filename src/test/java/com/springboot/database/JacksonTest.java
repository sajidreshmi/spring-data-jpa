package com.springboot.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.database.domain.BookEntity;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class JacksonTest {
    @Test
    public void testThatObjectMapperCanCreateJsonFromJavaObject() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        BookEntity book = BookEntity.builder()
                .title("The Great Gatsby")
                .isbn("978-3-16-148410-0")
                .yearPublished("1925")
                .build();

        String result = objectMapper.writeValueAsString(book);
        assertThat(result).isEqualTo("{\"title\":\"The Great Gatsby\",\"author\":null,\"isbn\":\"978-3-16-148410-0\",\"year\":\"1925\"}");
    }

    @Test
    public void testThatObjectMapperCanCreateJavaObjectFromJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"foo\":\"bar\", \"title\":\"The Great Gatsby\",\"author\":null,\"isbn\":\"978-3-16-148410-0\",\"year\":\"1925\"}";
        BookEntity result = objectMapper.readValue(json, BookEntity.class);
        assertThat(result).isEqualTo(BookEntity.builder()
                .title("The Great Gatsby")
                .isbn("978-3-16-148410-0")
                .yearPublished("1925")
                .build());
    }
}
