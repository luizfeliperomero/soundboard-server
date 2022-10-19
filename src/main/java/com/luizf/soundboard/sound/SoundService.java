package com.luizf.soundboard.sound;

import com.luizf.soundboard.exception.playlist_exceptions.PlaylistNotFound;
import com.luizf.soundboard.exception.sound_exceptions.SoundNotFound;
import com.luizf.soundboard.playlist.Playlist;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class SoundService {
    private String soundsPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator +  "resources" + File.separator + "sounds";
    private final SoundRepository soundRepository;

    public SoundService(SoundRepository soundRepository) {
        this.soundRepository = soundRepository;
    }

    public List<Sound> getPlaylistSounds(Long id) {
       return soundRepository.getPlaylistSounds(id);
    }


    @Transactional
    public Sound uploadFile(MultipartFile file, Long playlistId, Long userId) {
        Sound sound = new Sound();
        try {
            sound.setName(file.getOriginalFilename().split("\\.")[0]);
            String hashName = bytesToSha1(file.getBytes());
            sound.setCode(hashName + userId);
            sound.setUrl("http://192.168.1.101:8080/api/v1/sound/getAudio/" + sound.getCode());
            if(!new File(soundsPath + File.separator + hashName).isFile()) {
                file.transferTo(new File(soundsPath + File.separator + sound.getCode()));
            }
            Sound s = soundRepository.save(sound);
            soundRepository.savePlaylistSound(playlistId, sound.getId());
            return s;
        } catch (IOException e ) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
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

    @Transactional
    public void savePlaylistSound(Long playlistId, Long soundId) {
        soundRepository.savePlaylistSound(playlistId, soundId);
    }

    public boolean multiplePlaylists(Long soundId) {
       if(soundRepository.getPlaylistSoundIds(soundId).size() > 1) {
          return true;
       }
       return false;
    }

    @Transactional
    public void delete(Sound sound, Long playlistId) {
        try {
            if(!multiplePlaylists(sound.getId())) {
                Files.deleteIfExists(Paths.get(soundsPath + sound.getCode()));
                soundRepository.delete(sound);
            }
            soundRepository.deletePlaylistSoundBySoundId(sound.getId(), playlistId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String bytesToSha1(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        BigInteger no = new BigInteger(sha1.digest(bytes));
        String hash = no.toString(16);
        while (hash.length() < 32) {
            hash = "0" + hash;
        }
        return hash;
    }

}
