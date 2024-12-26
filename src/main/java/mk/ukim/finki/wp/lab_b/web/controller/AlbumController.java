package mk.ukim.finki.wp.lab_b.web.controller;

import mk.ukim.finki.wp.lab_b.model.Album;
import mk.ukim.finki.wp.lab_b.model.Artist;
import mk.ukim.finki.wp.lab_b.model.Song;
import mk.ukim.finki.wp.lab_b.model.exception.SongNotFoundException;
import mk.ukim.finki.wp.lab_b.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public String getAlbums(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("albums", albumService.findAll());
        return "listAlbums";
    }

    @GetMapping("/details/{id}")
    public String getAlbumDetailsPage(@PathVariable Long id, Model model) {
        if (albumService.findById(id).isEmpty()) {
            throw new RuntimeException("Album with id " + id + " not found");
        }

        Album album = albumService.findById(id).get();
        List<Song> songs = album.getSongs();

        model.addAttribute("album", album);
        model.addAttribute("songs", songs);
        return "albumDetails";
    }

    @GetMapping("/add-form")
    public String addAlbumPage(Model model) {
        return "addAlbum";
    }

    @PostMapping("/add")
    public String saveAlbum(@RequestParam(required = false) Long id,
                            @RequestParam String name,
                            @RequestParam String genre,
                            @RequestParam String releaseYear
    ) {
        if (id != null) {
            this.albumService.update(id, name, genre, releaseYear);
        } else {
            this.albumService.save(name, genre, releaseYear);
        }
        return "redirect:/albums";
    }

    @GetMapping("/edit/{id}")
    public String editAlbum(@PathVariable Long id, Model model) {
        if (this.albumService.findById(id).isPresent()) {
            Album album = this.albumService.findById(id).get();
            model.addAttribute("album", album);
            return "addAlbum";
        }
        return "redirect:/songs?error=SongNotFound";
    }

    @GetMapping("/delete/{id}")
    public String deleteAlbum(@PathVariable Long id) {
        this.albumService.deleteById(id);
        return "redirect:/albums";
    }
}
