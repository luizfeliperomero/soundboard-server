package com.luizf.soundboard.sound;

import com.luizf.soundboard.exception.playlist_exceptions.PlaylistNotFound;
import com.luizf.soundboard.exception.sound_exceptions.SoundNotFound;
import com.luizf.soundboard.playlist.Playlist;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
        //String soundsPath = "src" + File.separator + "main" + File.separator +  "resources" + File.separator + "sounds";
        String soundsPath = "C:\\Users\\Pichau\\Desktop\\luizf-rpg-soundboard\\soundboard\\src\\main\\resources\\sounds\\";
        try {
        file.transferTo(new File(soundsPath + File.separator + file.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Sound update(Sound sound) {
        Optional<Sound> soundOpt = soundRepository.findById(sound.getId());
        return soundOpt.map(s -> {
            s.setName(sound.getName());
            return soundRepository.save(s);
        }).orElseThrow( ()  -> new SoundNotFound("Playlist with id " +sound.getId()+ " not found"));
    }

    public void delete(Sound sound) {
       soundRepository.delete(sound);
    }
}
