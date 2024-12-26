package mk.ukim.finki.wp.lab_b.model.exception;

public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(Long id) {
        System.out.println("Album not found: " + id);
    }
}
