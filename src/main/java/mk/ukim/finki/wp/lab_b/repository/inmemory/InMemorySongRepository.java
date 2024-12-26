package mk.ukim.finki.wp.lab_b.repository.inmemory;

import mk.ukim.finki.wp.lab_b.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab_b.model.Album;
import mk.ukim.finki.wp.lab_b.model.Artist;
import mk.ukim.finki.wp.lab_b.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemorySongRepository {

    public List<Song> findAll() {
        return DataHolder.songs;
    }

    public Optional<Song> findByTrackId(String trackId) {
        return DataHolder.songs.stream().filter(s -> s.getTrackId().equals(trackId)).findFirst();
    }

    public Optional<Song> findById(Long id) {
        return DataHolder.songs.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    public Optional<Artist> addArtistToSong(Artist artist, Song song) {
        List<Artist> artists = song.getPerformers();
        artists.add(artist);
        song.setPerformers(artists);
        return Optional.of(artist);
    }

    public void deleteById(Long id) {
        DataHolder.songs.removeIf(s -> s.getId().equals(id));
    }

    public Optional<Song> save(String trackId, String title, String genre, int releaseYear, Album album) {
        if (album == null) {
            throw new IllegalArgumentException("Album cannot be null");
        }

        Song song = new Song(trackId, title, genre, releaseYear, album);
        DataHolder.songs.removeIf(s -> s.getTrackId().equals(trackId));
        DataHolder.songs.add(song);
        return Optional.of(song);
    }
}
