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
    @Query(value = "INSERT INTO playlist_sound(playlist_id, sound_id) VALUES (:playlist_id, :sound_id)",
            nativeQuery = true
    )
    void savePlaylistSound(@Param("playlist_id") Long playlist_id, @Param("sound_id") Long sound_id);

    @Query(value = "SELECT sound.sound_id, name_, url FROM playlist_sound as ps\n" +
            "JOIN sound\n" +
            "ON sound.sound_id = ps.sound_id where playlist_id = :playlist_id", nativeQuery=true)
    List<Sound> getPlaylistSounds(@Param("playlist_id") Long playlist_id);
}
