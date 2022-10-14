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

    @Modifying
    @Query(value = "DELETE FROM playlist_sound WHERE sound_id = :soundId", nativeQuery = true)
    void deletePlaylistSoundBySoundId(@Param("soundId") Long soundId );
}
