package mk.ukim.finki.wp.lab_b.service.impl;

import mk.ukim.finki.wp.lab_b.model.Album;
import mk.ukim.finki.wp.lab_b.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab_b.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public Optional<Album> update(Long id, String name, String genre, String releaseYear) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));
        album.setName(name);
        album.setGenre(genre);
        album.setReleaseYear(releaseYear);
        return Optional.of(albumRepository.save(album));
    }

    @Override
    public Optional<Album> save(String name, String genre, String releaseYear) {
        Album album = new Album(name, genre, releaseYear);
        return Optional.of(albumRepository.save(album));
    }

    @Override
    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }
}
