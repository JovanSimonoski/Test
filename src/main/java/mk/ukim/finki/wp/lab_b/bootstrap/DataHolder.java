package mk.ukim.finki.wp.lab_b.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab_b.model.Album;
import mk.ukim.finki.wp.lab_b.model.Artist;
import mk.ukim.finki.wp.lab_b.model.Song;
import mk.ukim.finki.wp.lab_b.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab_b.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab_b.repository.jpa.SongRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists = null;
    public static List<Song> songs = null;
    public static List<Album> albums = null;

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;

    public DataHolder(AlbumRepository albumRepository, ArtistRepository artistRepository, SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
    }


    @PostConstruct
    public void init() {
        artists = new ArrayList<>();
        if (this.artistRepository.count() == 0) {
            artists.add(new Artist("Freddie", "Mercury", "Lead vocalist of Queen and iconic rock legend."));
            artists.add(new Artist("Beyonce", "Knowles", "American singer, songwriter, and performer."));
            artists.add(new Artist("Elvis", "Presley", "The King of Rock and Roll."));
            artists.add(new Artist("Adele", "Adkins", "British singer known for her powerful voice."));
            artists.add(new Artist("Michael", "Jackson", "The King of Pop."));
            artists.add(new Artist("Taylor", "Swift", "American singer-songwriter and pop icon."));
            artists.add(new Artist("Bob", "Dylan", "Influential American singer-songwriter and Nobel laureate."));
            artists.add(new Artist("Whitney", "Houston", "American singer and one of the best-selling music artists."));
            artists.add(new Artist("John", "Lennon", "Co-founder of The Beatles and solo artist."));
            artists.add(new Artist("Lady", "Gaga", "Pop icon known for her unique style and artistry."));
            this.artistRepository.saveAll(artists);
        }

        albums = new ArrayList<>();
        if (this.albumRepository.count() == 0) {
            albums.add(new Album("Thriller", "Pop", "1982"));
            albums.add(new Album("Back in Black", "Hard Rock", "1980"));
            albums.add(new Album("The Dark Side of the Moon", "Progressive Rock", "1973"));
            albums.add(new Album("The Bodyguard", "Soundtrack", "1992"));
            albums.add(new Album("Rumours", "Soft Rock", "1977"));
            albums.add(new Album("Hotel California", "Rock", "1976"));
            albums.add(new Album("Abbey Road", "Rock", "1969"));
            albums.add(new Album("Purple Rain", "Pop/Rock", "1984"));
            albums.add(new Album("Born in the U.S.A.", "Rock", "1984"));
            albums.add(new Album("Bad", "Pop", "1987"));
            this.albumRepository.saveAll(albums);
        }


        songs = new ArrayList<>();

        if (this.songRepository.count() == 0) {
            songs.add(new Song("T001", "Bohemian Rhapsody", "Rock", 1975, albums.get(0)));
            songs.add(new Song("T002", "Halo", "Pop", 2008, albums.get(1)));
            songs.add(new Song("T003", "Jailhouse Rock", "Rock and Roll", 1957, albums.get(2)));
            songs.add(new Song("T004", "Rolling in the Deep", "Soul", 2010, albums.get(3)));
            songs.add(new Song("T005", "Billie Jean", "Pop", 1982, albums.get(4)));
            songs.add(new Song("T006", "Shake It Off", "Pop", 2014, albums.get(5)));
            songs.add(new Song("T007", "Blowin' in the Wind", "Folk", 1963, albums.get(6)));
            songs.add(new Song("T008", "I Will Always Love You", "R&B", 1992, albums.get(7)));
            songs.add(new Song("T009", "Imagine", "Pop Rock", 1971, albums.get(8)));
            songs.add(new Song("T010", "Bad Romance", "Pop", 2009, albums.get(9)));
            this.songRepository.saveAll(songs);
        }
    }

}
