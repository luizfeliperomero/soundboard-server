package com.luizf.soundboard.sound;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Sound {

    @Id
    @Column(name = "sound_id")
    @SequenceGenerator(name = "sound_seq", sequenceName = "sound_sound_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sound_seq")
    private long id;
    @Column(name = "name_")
    private String name;
    private String fullName;
    private String url;
}
