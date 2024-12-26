package mk.ukim.finki.wp.lab_b.repository.inmemory;

import mk.ukim.finki.wp.lab_b.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab_b.model.Album;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAlbumRepository {
    public List<Album> findAll() {
        return DataHolder.albums;
    }

    public Optional<Album> findById(Long id) {
        return DataHolder.albums.stream().filter(album -> album.getId().equals(id)).findFirst();
    }
}