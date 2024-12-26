package mk.ukim.finki.wp.lab_b.web.controller;

import mk.ukim.finki.wp.lab_b.model.Artist;
import mk.ukim.finki.wp.lab_b.model.Song;
import mk.ukim.finki.wp.lab_b.model.exception.ArtistNotFoundException;
import mk.ukim.finki.wp.lab_b.model.exception.SongNotFoundException;
import mk.ukim.finki.wp.lab_b.service.ArtistService;
import mk.ukim.finki.wp.lab_b.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/artist")
public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping
    public String getArtistPage(@SessionAttribute(value = "trackId") String trackId, Model model) {
        model.addAttribute("artists", artistService.listArtists());
        model.addAttribute("trackId", trackId);
        return "artistsList";
    }

    @PostMapping
    public String addArtistToSong(@RequestParam Long artistId,
                                  @SessionAttribute(value = "trackId") String trackId) {
        songService.addArtistToSong(artistId, trackId);

        return "redirect:/songs/details/" + trackId;

    }

}
