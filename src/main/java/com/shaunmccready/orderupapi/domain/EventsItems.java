package com.shaunmccready.orderupapi.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Accessors(chain = true)
@Slf4j
@Table(name = "events_items", schema = "public")
public class EventsItems implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Events event;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Items item;
}
