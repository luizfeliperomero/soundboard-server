package com.luizf.soundboard.playlist;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/playlist")
public class PlaylistController {
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping("/save")
    public Playlist save(@RequestBody Playlist playlist, @RequestParam Long user_id) {
       return playlistService.save(playlist, user_id);
    }

    @GetMapping("/findPlaylists/{user_id}")
    public ResponseEntity findUserPlaylists(@PathVariable Long user_id) {
        return ResponseEntity.ok(playlistService.findUserPlaylists(user_id));
    }
}
