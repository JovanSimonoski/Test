package mk.ukim.finki.wp.lab_b.model.exception;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(String trackId) {
        System.out.println("Song not found: " + trackId);
    }
}
