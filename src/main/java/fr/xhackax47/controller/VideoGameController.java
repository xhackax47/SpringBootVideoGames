package fr.xhackax47.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.xhackax47.dao.VideoGameRepository;
import fr.xhackax47.exception.ResourceNotFoundException;
import fr.xhackax47.model.VideoGame;

@RestController
@RequestMapping("/api/games")
public class VideoGameController {

    @Autowired
    VideoGameRepository vg;
    
    @GetMapping("/")
    public List<VideoGame> getAllGames() {
        return vg.findAll();
    }
    
    @PostMapping("/")
    public VideoGame createVideoGame(@Valid @RequestBody VideoGame game) {
        return vg.save(game);
    }
    
    @GetMapping("/{id}")
    public VideoGame getVideoGameById(@PathVariable(value = "id") Long gameId) {
        return vg.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Jeu vidéo", "id", gameId));
    }
    
    @PutMapping("/{id}")
    public VideoGame updateVideoGame(@PathVariable(value = "id") Long gameId,
                                            @Valid @RequestBody VideoGame videoGame) {

    	VideoGame game = vg.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Jeu vidéo", "id", gameId));

    	game.setName(videoGame.getName());
    	game.setGenre(videoGame.getGenre());
    	game.setPrice(videoGame.getPrice());
    	game.setPlatform(videoGame.getPlatform());

        VideoGame gameUp = vg.save(game);
        return gameUp;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVideoGame(@PathVariable(value = "id") Long gameId) {
        VideoGame game = vg.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Jeu vidéo", "id", gameId));

        vg.delete(game);

        return ResponseEntity.ok().build();
    }
}
