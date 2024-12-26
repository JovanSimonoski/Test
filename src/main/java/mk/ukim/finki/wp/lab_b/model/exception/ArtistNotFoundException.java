package mk.ukim.finki.wp.lab_b.model.exception;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(Long id) {
        System.out.println("Song not found: " + id);
    }
}
