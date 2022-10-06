package com.luizf.soundboard.sound;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/sound")
public class SoundController {
    private final SoundService soundService;

    public SoundController(SoundService soundService) {
        this.soundService = soundService;
    }

    @PostMapping("/save")
   public ResponseEntity save(@RequestBody Sound sound, @RequestParam Long playlist_id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(soundService.save(sound, playlist_id));
   }

   @GetMapping("/getSounds/{playlist_id}")
   public List<Sound> getPlaylistSounds(@PathVariable Long playlist_id) {
       return soundService.getPlaylistSounds(playlist_id);
   }
}
