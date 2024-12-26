package mk.ukim.finki.wp.lab_b.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.lab_b.model.Album;
import mk.ukim.finki.wp.lab_b.model.Artist;
import mk.ukim.finki.wp.lab_b.model.Song;
import mk.ukim.finki.wp.lab_b.model.exception.AlbumNotFoundException;
import mk.ukim.finki.wp.lab_b.model.exception.SongNotFoundException;
import mk.ukim.finki.wp.lab_b.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab_b.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab_b.repository.jpa.SongRepository;
import mk.ukim.finki.wp.lab_b.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Optional<Song> findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public Optional<Song> addArtistToSong(Long artistId, String trackId) {
        if (this.songRepository.findByTrackId(trackId).isEmpty()
                || this.artistRepository.findById(artistId).isEmpty()) {
            throw new RuntimeException();
        }
        Song songUpdated = this.songRepository.findByTrackId(trackId).get();
        Artist artistUpdated = this.artistRepository.findById(artistId).get();

        songUpdated.getPerformers().add(artistUpdated);

        return Optional.of(this.songRepository.save(songUpdated));
    }

    @Override
    @Transactional
    public Optional<Song> update(Long id, String trackId, String title, String genre, int releaseYear, Long albumId) {
        Song songUpdated = this.songRepository.findById(id).orElseThrow(RuntimeException::new);
        Album album = this.albumRepository.findById(albumId).orElseThrow(RuntimeException::new);
        songUpdated.setTitle(title);
        songUpdated.setGenre(genre);
        songUpdated.setReleaseYear(releaseYear);
        songUpdated.setTrackId(trackId);
        songUpdated.setAlbum(album);
        return Optional.of(this.songRepository.save(songUpdated));
    }

    @Override
    @Transactional
    public Optional<Song> save(String trackId, String title, String genre, int releaseYear, Long albumId) {
        Album album = this.albumRepository.findById(albumId).orElseThrow(() -> new AlbumNotFoundException(albumId));
        Song s = new Song(trackId, title, genre, releaseYear, album);
        return Optional.of(this.songRepository.save(s));
    }

    @Override
    public void deleteById(Long id) {
        this.songRepository.deleteById(id);
    }
}
