package com.luizf.soundboard.playlist;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
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

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Playlist playlist) {
       return ResponseEntity.ok(playlistService.update(playlist));
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Playlist playlist) {
        playlistService.delete(playlist);
    }

    @GetMapping("/getPlaylistsWhereSoundNotExists/{soundId}")
    public ResponseEntity<Playlist[]> getPlaylistsWhereSoundNotExists(@PathVariable Long soundId) {
       return ResponseEntity.ok(playlistService.getPlaylistsWhereSoundNotExists(soundId));
    }

}
