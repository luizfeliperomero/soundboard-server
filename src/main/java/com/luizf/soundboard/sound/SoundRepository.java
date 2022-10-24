package com.luizf.soundboard.sound;

import com.luizf.soundboard.playlist.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoundRepository extends JpaRepository<Sound, Long> {
    @Modifying
    @Query(value = "INSERT INTO playlist_sound(playlist_id, sound_id) VALUES (:playlistId, :soundId)",
            nativeQuery = true
    )
    void savePlaylistSound(@Param("playlistId") Long playlistId, @Param("soundId") Long soundId);

    @Query(value = "SELECT sound.sound_id, name_, url, code FROM playlist_sound as ps\n" +
            "JOIN sound\n" +
            "ON sound.sound_id = ps.sound_id where playlist_id = :playlistId", nativeQuery=true)
    List<Sound> getPlaylistSounds(@Param("playlistId") Long playlistId);

    @Query(value = "SELECT sound_id from playlist_sound where sound_id = :soundId", nativeQuery = true)
    List<Long> getPlaylistSoundIds(@Param("soundId") Long soundId);


    @Modifying
    @Query(value = "DELETE FROM playlist_sound WHERE sound_id = :soundId and playlist_id = :playlistId", nativeQuery = true)
    void deletePlaylistSoundBySoundId(@Param("soundId") Long soundId, @Param("playlistId") Long playlistId );

    @Query(value = "select count(*) from\n" +
            "(select * from user_playlist where user_id = :userId) as up\n" +
            "join playlist_sound\n" +
            "on playlist_sound.playlist_id = up.playlist_id", nativeQuery = true)
    Long userSoundsNumber(@Param("userId") Long userId);
}
