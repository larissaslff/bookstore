package com.book.store.controllers;

import com.book.store.dto.AuthorDTO;
import com.book.store.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    private AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> registerANewAuthor(@RequestBody AuthorDTO authorDTO){
        AuthorDTO savedAuthor = authorService.saveAuthor(authorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);
    }

}
