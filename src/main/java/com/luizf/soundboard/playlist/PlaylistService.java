package com.luizf.soundboard.playlist;

import com.luizf.soundboard.exception.playlist_exceptions.PlaylistNotFound;
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

    public Playlist update(Playlist playlist) {
        Optional<Playlist> playlistOpt = playlistRepository.findById(playlist.getId());
        return playlistOpt.map(p -> {
            p.setName(playlist.getName());
            p.setDescription(playlist.getDescription());
            return playlistRepository.save(p);
        }).orElseThrow( ()  -> new PlaylistNotFound("Playlist with id " +playlist.getId()+ " not found"));
    }

    @Transactional
    public void delete(Playlist playlist) {
        playlistRepository.deleteUserPlaylistByPlaylistId(playlist.getId());
        playlistRepository.deletePlaylistSoundbyPlaylistId(playlist.getId());
        playlistRepository.delete(playlist);
    }
}
