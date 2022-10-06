package com.luizf.soundboard.sound;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
