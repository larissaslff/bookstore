package com.book.store.models;

import com.book.store.dto.PublisherDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "publishers")
public class Publisher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;

    public static Publisher toEntity(PublisherDTO publisherDTO) {
        return Publisher.builder().name(publisherDTO.name()).build();
    }
}
