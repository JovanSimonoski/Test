package mk.ukim.finki.wp.lab_b.service;

import mk.ukim.finki.wp.lab_b.model.Album;
import mk.ukim.finki.wp.lab_b.model.Artist;
import mk.ukim.finki.wp.lab_b.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();

    Optional<Song> findByTrackId(String trackId);

    Optional<Song> findById(Long id);

    Optional<Song> addArtistToSong(Long artistId, String trackId);

    Optional<Song> update(Long id, String trackId, String title, String genre, int releaseYear, Long albumId);

    Optional<Song> save(String trackId, String title, String genre, int releaseYear, Long albumId);

    //Optional<Song> update(Long id, String trackId, String title, String genre, int releaseYear, List<Artist> performers, Long albumId);

    void deleteById(Long id);
}
