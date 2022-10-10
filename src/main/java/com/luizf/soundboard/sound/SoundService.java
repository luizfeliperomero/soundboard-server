package com.luizf.soundboard.sound;

import com.luizf.soundboard.exception.playlist_exceptions.PlaylistNotFound;
import com.luizf.soundboard.exception.sound_exceptions.SoundNotFound;
import com.luizf.soundboard.playlist.Playlist;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestParam;
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

    public List<Sound> getPlaylistSounds(Long id) {
       return soundRepository.getPlaylistSounds(id);
    }


    @Transactional
    public Sound uploadFile(MultipartFile file, Long playlist_id) {
        //String soundsPath = "src" + File.separator + "main" + File.separator +  "resources" + File.separator + "sounds";
        Sound sound = new Sound();
        String soundsPath = "E:\\Projects\\soundboard-server\\src\\main\\resources\\sounds";
        try {
            String hashName = String.valueOf(file.getBytes().toString().hashCode());
            sound.setName(file.getOriginalFilename().split("\\.")[0]);
            sound.setUrl("http://192.168.1.101:8080/api/v1/sound/getAudio/" +hashName);
            file.transferTo(new File(soundsPath + File.separator + hashName));
            Sound s = soundRepository.save(sound);
            soundRepository.savePlaylistSound(playlist_id, sound.getId());
            return s;
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
