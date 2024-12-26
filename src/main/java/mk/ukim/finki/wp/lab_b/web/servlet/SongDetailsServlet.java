package mk.ukim.finki.wp.lab_b.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab_b.model.Artist;
import mk.ukim.finki.wp.lab_b.model.Song;
import mk.ukim.finki.wp.lab_b.model.exception.SongNotFoundException;
import mk.ukim.finki.wp.lab_b.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SongDetailsServlet", urlPatterns = {"/servlet/songDetails"})
public class SongDetailsServlet extends HttpServlet {
    private final SongService songService;
    private final SpringTemplateEngine springTemplateEngine;


    public SongDetailsServlet(SongService songService, SpringTemplateEngine springTemplateEngine) {
        this.songService = songService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String trackId = req.getParameter("trackId");

        if (songService.findByTrackId(trackId).isEmpty()) {
            throw new SongNotFoundException(trackId);
        }

        Song song = songService.findByTrackId(trackId).get();
        List<Artist> performers = song.getPerformers();

        context.setVariable("song", song);
        context.setVariable("performers", performers);

        springTemplateEngine.process("songDetails.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
