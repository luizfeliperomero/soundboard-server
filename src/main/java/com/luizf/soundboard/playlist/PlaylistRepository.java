package com.luizf.soundboard.playlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
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

    @Modifying
    @Query(value = "DELETE FROM user_playlist WHERE playlist_id = :playlistId", nativeQuery = true)
    void deleteUserPlaylistByPlaylistId(@Param("playlistId") Long playlistId);

    @Modifying
    @Query(value = "DELETE FROM playlist_sound WHERE playlist_id = :playlistId", nativeQuery = true)
    void deletePlaylistSoundbyPlaylistId(@Param("playlistId") Long playlistId);

    @Query(value = "select playlist.playlist_id, name_, description from\n" +
            "(select * from playlist_sound where sound_id = :soundId ) as ps\n" +
            "join playlist\n" +
            "on playlist.playlist_id != ps.playlist_id", nativeQuery = true)
    Playlist[] getPlaylistsWhereSoundNotExist(@Param("soundId") Long soundId);
}
