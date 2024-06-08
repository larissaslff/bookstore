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
    public ResponseEntity<PublisherDTO> registerANewPublisher(@RequestBody PublisherDTO publisher){
        PublisherDTO savedPublisher = publisherService.savePublisher(publisher);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPublisher);
    }
}
