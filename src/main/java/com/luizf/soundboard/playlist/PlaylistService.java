package com.luizf.soundboard.playlist;

import com.luizf.soundboard.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Transactional
    public Playlist save(Playlist playlist, Long user_id) {
      Playlist p = playlistRepository.save(playlist);
      playlistRepository.saveUserPlaylist(playlist.getId(), user_id);

      return p;
    }

    public List<Playlist> findUserPlaylists(Long user_id) {
       return playlistRepository.getUserPlaylists(user_id);
    }
}
