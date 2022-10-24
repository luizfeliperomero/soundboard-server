package com.luizf.soundboard.plan;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Plan {
    @Id
    @Column(name = "plan_id")
    @SequenceGenerator(name = "plan_seq", sequenceName = "plan_plan_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_seq")
    Long id;
    @Column(name = "name_")
    String name;
    Integer max_playlists;
    Integer max_uploads;
    Integer price_month;
}
