package com.springboot.database.services;

import com.springboot.database.domain.AuthorEntity;
import org.springframework.stereotype.Service;


public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity authorEntity);
}
