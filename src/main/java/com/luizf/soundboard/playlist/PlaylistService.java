package com.luizf.soundboard.playlist;

import com.luizf.soundboard.exception.plan_exceptions.PlanNotFound;
import com.luizf.soundboard.exception.playlist_exceptions.MaxPlaylists;
import com.luizf.soundboard.exception.playlist_exceptions.PlaylistNotFound;
import com.luizf.soundboard.exception.user_exceptions.UserNotFound;
import com.luizf.soundboard.plan.Plan;
import com.luizf.soundboard.plan.PlanRepository;
import com.luizf.soundboard.user.User;
import com.luizf.soundboard.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    public PlaylistService(PlaylistRepository playlistRepository, PlanRepository planRepository, UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Playlist save(Playlist playlist, Long user_id) {
      User user = userRepository.findById(user_id).orElseThrow(() -> new UserNotFound("User not found"));
      Plan userPlan = planRepository.findById(user.getPlanId()).orElseThrow(() -> new PlanNotFound("Plan not found"));
      if(findUserPlaylists(user.getId()).size() < userPlan.getMax_playlists()) {
          Playlist p = playlistRepository.save(playlist);
          playlistRepository.saveUserPlaylist(playlist.getId(), user.getId());
          return p;
      }
      throw new MaxPlaylists("Can't save playlist because maximum number of " + userPlan.getMax_playlists() + " playlists was reached");
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

    public Playlist[] getPlaylistsWhereSoundNotExists(Long soundId) {
       return playlistRepository.getPlaylistsWhereSoundNotExist(soundId);
    }

}
