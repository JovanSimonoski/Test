package mk.ukim.finki.wp.lab_b.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab_b.model.Album;
import mk.ukim.finki.wp.lab_b.model.Artist;
import mk.ukim.finki.wp.lab_b.model.Song;
import mk.ukim.finki.wp.lab_b.model.exception.SongNotFoundException;
import mk.ukim.finki.wp.lab_b.service.AlbumService;
import mk.ukim.finki.wp.lab_b.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("songs", songService.listSongs());
        return "listSongs";
    }

    @PostMapping
    public String chooseSong(@RequestParam String trackId, HttpSession session) {
        session.setAttribute("trackId", trackId);
        return "redirect:/artist";
    }

    @GetMapping("/details/{trackId}")
    public String getSongDetailsPage(@PathVariable String trackId, Model model) {
        if (songService.findByTrackId(trackId).isEmpty()) {
            throw new SongNotFoundException(trackId);
        }

        Song song = songService.findByTrackId(trackId).get();
        List<Artist> performers = song.getPerformers();

        model.addAttribute("song", song);
        model.addAttribute("performers", performers);
        return "songDetails";
    }


    @GetMapping("/add-form")
    public String addSongPage(Model model) {
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("albums", albums);
        return "addSong";
    }

    @PostMapping("/add")
    public String saveSong(@RequestParam(required = false) Long id,
                           @RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam int releaseYear,
                           @RequestParam Long albumId

    ) {
        if (id != null) {
            this.songService.update(id, trackId, title, genre, releaseYear, albumId);
        } else {
            this.songService.save(trackId, title, genre, releaseYear, albumId);
        }
        return "redirect:/songs";
    }

    @GetMapping("/edit/{id}")
    public String editSong(@PathVariable Long id, Model model) {
        if (this.songService.findById(id).isPresent()) {
            Song song = this.songService.findById(id).get();
            List<Album> albums = this.albumService.findAll();
            model.addAttribute("song", song);
            model.addAttribute("albums", albums);
            return "addSong";
        }
        return "redirect:/songs?error=SongNotFound";
    }


    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        this.songService.deleteById(id);
        return "redirect:/songs";
    }
}
