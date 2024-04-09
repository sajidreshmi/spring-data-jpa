package com.springboot.database.services;

import com.springboot.database.domain.AuthorEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findById(Long id);

    void deleteAuthor(AuthorEntity authorEntity);
}
