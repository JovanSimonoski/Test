package mk.ukim.finki.wp.lab_b.repository.jpa;

import mk.ukim.finki.wp.lab_b.model.Artist;
import mk.ukim.finki.wp.lab_b.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    //List<Song> findAllByAlbum_Id(Long albumId);
    Optional<Song> findByTrackId(String trackId);
}
