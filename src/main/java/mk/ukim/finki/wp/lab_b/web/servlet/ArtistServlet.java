package mk.ukim.finki.wp.lab_b.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab_b.model.Artist;
import mk.ukim.finki.wp.lab_b.model.Song;
import mk.ukim.finki.wp.lab_b.model.exception.ArtistNotFoundException;
import mk.ukim.finki.wp.lab_b.model.exception.SongNotFoundException;
import mk.ukim.finki.wp.lab_b.service.ArtistService;
import mk.ukim.finki.wp.lab_b.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "ArtistServlet", urlPatterns = {"/servlet/artist"})
public class ArtistServlet extends HttpServlet {
    private final ArtistService artistService;
    private final SongService songService;
    private final SpringTemplateEngine springTemplateEngine;


    public ArtistServlet(ArtistService artistService, SongService songService, SpringTemplateEngine springTemplateEngine) {
        this.artistService = artistService;
        this.songService = songService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String trackId = (String) req.getSession().getAttribute("trackId");
        context.setVariable("artists", artistService.listArtists());
        context.setVariable("trackId", trackId);

        springTemplateEngine.process("artistsList.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        Long id = Long.parseLong(req.getParameter("artistId"));
        String trackId = req.getSession().getAttribute("trackId").toString();

        if (songService.findByTrackId(trackId).isEmpty()) {
            throw new SongNotFoundException(trackId);
        }
        if (artistService.findById(id).isEmpty()) {
            throw new ArtistNotFoundException(id);
        }

        Song song = songService.findByTrackId(trackId).get();
        Artist artist = artistService.findById(id).get();

        songService.addArtistToSong(id, trackId);

        resp.sendRedirect("/songDetails?trackId=" + trackId);

    }
}
