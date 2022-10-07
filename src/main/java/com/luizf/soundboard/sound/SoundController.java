package com.luizf.soundboard.sound;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

   @PostMapping("/uploadFile")
   public void uploadFile(@RequestParam("file") MultipartFile file) {
        soundService.uploadFile(file);
   }

   @GetMapping("/getAudio/{name}")
   public byte[] getAudio(@PathVariable String name) throws IOException {
        String soundsPath = "src" + File.separator + "main" + File.separator +  "resources" + File.separator + "sounds";
        String path = soundsPath + File.separator + name;
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        return bytes;
   }

   @GetMapping("/getSounds/{playlist_id}")
   public List<Sound> getPlaylistSounds(@PathVariable Long playlist_id) {
       return soundService.getPlaylistSounds(playlist_id);
   }
}
