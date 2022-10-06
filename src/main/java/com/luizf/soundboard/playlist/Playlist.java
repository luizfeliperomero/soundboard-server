package com.luizf.soundboard.playlist;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Playlist {
    @Id
    @Column(name = "playlist_id")
    @SequenceGenerator(name = "playlist_seq", sequenceName = "playlist_playlist_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playlist_seq")
    private long id;
    @Column(name = "name_")
    private String name;
    @Column(name = "description")
    private String description;
}
