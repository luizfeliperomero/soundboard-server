package com.luizf.soundboard.sound;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class SoundService {
    private final SoundRepository soundRepository;

    public SoundService(SoundRepository soundRepository) {
        this.soundRepository = soundRepository;
    }

    @Transactional
    public Sound save(Sound sound, Long playlist_id) {
        Sound s = soundRepository.save(sound);
        soundRepository.savePlaylistSound(playlist_id, sound.getId());
        return s;
    }

    public List<Sound> getPlaylistSounds(Long id) {
       return soundRepository.getPlaylistSounds(id);
    }

    public void uploadFile(MultipartFile file) {
        String soundsPath = "src" + File.separator + "main" + File.separator +  "resources" + File.separator + "sounds";
        try {
        file.transferTo(new File(soundsPath + File.separator + file.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
