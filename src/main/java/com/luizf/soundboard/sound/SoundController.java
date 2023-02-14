package com.luizf.soundboard.sound;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiListUI;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sound")
@CrossOrigin("*")
public class SoundController {
    private final SoundService soundService;

    public SoundController(SoundService soundService) {
        this.soundService = soundService;
    }


   @GetMapping("/getAudio/{code}")
   public byte[] getAudio(@PathVariable String code) throws IOException {
        String soundsPath = "src" + File.separator + "main" + File.separator +  "resources" + File.separator + "sounds";
        String path = soundsPath + File.separator + code;
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        return bytes;
   }

    @PostMapping("/uploadFile")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String playlist_id, @RequestParam String user_id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(soundService.uploadFile(file, Long.parseLong(playlist_id), Long.parseLong(user_id)));
    }

   @GetMapping("/getSounds/{playlist_id}")
   public List<Sound> getPlaylistSounds(@PathVariable Long playlist_id) {
       return soundService.getPlaylistSounds(playlist_id);
   }

   @DeleteMapping("/delete/{playlistId}")
   public void delete(@RequestBody Sound sound, @PathVariable Long playlistId) {
       soundService.delete(sound, playlistId);
   }

   @PutMapping("/update")
   public ResponseEntity update(@RequestBody Sound sound) {
        return ResponseEntity.ok(soundService.update(sound));
   }

   @GetMapping("/savePlaylistSound/{playlistId}/{soundId}")
   public void savePlaylistSound(@PathVariable Long playlistId, @PathVariable Long soundId) {
        soundService.savePlaylistSound(playlistId, soundId);
   }

}
