package com.book.store.controllers;

import com.book.store.dto.PublisherDTO;
import com.book.store.services.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    private PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    public ResponseEntity<Object> registerANewPublisher(@RequestBody PublisherDTO publisher){
        try {
            PublisherDTO savedPublisher = publisherService.savePublisher(publisher);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPublisher);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
