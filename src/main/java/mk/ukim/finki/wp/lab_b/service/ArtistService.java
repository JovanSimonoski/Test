package mk.ukim.finki.wp.lab_b.service;

import mk.ukim.finki.wp.lab_b.model.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    List<Artist> listArtists();

    Optional<Artist> findById(Long id);
}
