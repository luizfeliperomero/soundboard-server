package com.luizf.soundboard.sound;

import lombok.Data;
import org.springframework.content.commons.annotations.ContentId;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Sound {

    @Id
    @Column(name = "sound_id")
    @SequenceGenerator(name = "sound_seq", sequenceName = "sound_sound_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sound_seq")
    private long id;
    @ContentId
    private Long contendId;
    @ContentId
    private Long contentLength;
    @Column(name = "name_")
    private String name;
    private String url;
}
