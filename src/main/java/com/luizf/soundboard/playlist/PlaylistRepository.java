package com.luizf.soundboard.playlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    @Modifying
    @Query(value = "INSERT INTO user_playlist (user_id, playlist_id) VALUES (:user_id, :playlist_id)",
            nativeQuery = true
    )
    void saveUserPlaylist(@Param("playlist_id") Long playlist_id, @Param("user_id") Long user_id);

    @Query(value = "SELECT playlist.playlist_id, name_, description FROM user_playlist as up\n" +
            "JOIN playlist\n" +
            "ON playlist.playlist_id = up.playlist_id where user_id = :user_id", nativeQuery=true)
    List<Playlist> getUserPlaylists(@Param("user_id") Long user_id);
}
