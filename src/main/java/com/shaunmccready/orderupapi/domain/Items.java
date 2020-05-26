package com.shaunmccready.orderupapi.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Accessors(chain = true)
@Slf4j
@Table(name = "items", schema = "public")
public class Items implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "item")
    private Set<EventsItems> eventsItems;

    private Status status;

    private LocalDateTime created;

    private LocalDateTime modified;

    public Items setDefaults() {
        this.status = Status.ACTIVE;
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
        return this;
    }
}
