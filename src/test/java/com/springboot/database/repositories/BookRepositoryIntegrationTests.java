package com.springboot.database.repositories;


import com.springboot.database.TestDataUtil;
import com.springboot.database.domain.AuthorEntity;
import com.springboot.database.domain.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {

    private BookRepository underTest;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        BookEntity book = TestDataUtil.createTestBookA(authorEntity);
        underTest.save(book);
        Optional<BookEntity> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();

        BookEntity bookA = TestDataUtil.createTestBookA(authorEntity);
        underTest.save(bookA);

        BookEntity bookB = TestDataUtil.createTestBookB(authorEntity);
        underTest.save(bookB);

        BookEntity bookC = TestDataUtil.createTestBookC(authorEntity);
        underTest.save(bookC);

        Iterable<BookEntity> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();

        BookEntity bookA = TestDataUtil.createTestBookA(authorEntity);
        underTest.save(bookA);

        bookA.setTitle("UPDATED");
        underTest.save(bookA);

        Optional<BookEntity> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();

        BookEntity bookA = TestDataUtil.createTestBookA(authorEntity);
        underTest.save(bookA);

        underTest.deleteById(bookA.getIsbn());

        Optional<BookEntity> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isEmpty();
    }
}