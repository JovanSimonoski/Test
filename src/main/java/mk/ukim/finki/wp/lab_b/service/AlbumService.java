package mk.ukim.finki.wp.lab_b.service;

import mk.ukim.finki.wp.lab_b.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<Album> findAll();

    Optional<Album> findById(Long id);

    Optional<Album> update(Long id, String name, String genre, String releaseYear);

    Optional<Album> save(String name, String genre, String releaseYear);

    void deleteById(Long id);
}
