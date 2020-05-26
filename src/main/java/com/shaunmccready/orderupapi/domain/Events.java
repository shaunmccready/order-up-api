package com.shaunmccready.orderupapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Accessors(chain = true)
@Slf4j
@Table(name = "events", schema = "public")
public class Events implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    @JsonIgnoreProperties("events")
    private Users organizer;

    @OneToMany(mappedBy = "event")
    private Set<EventsItems> eventsItems;

    private Status status;

    private LocalDateTime created;

    private LocalDateTime modified;


}
