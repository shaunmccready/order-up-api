package com.shaunmccready.orderupapi.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Data
@Accessors(chain = true)
@Slf4j
@Table(name = "users", schema = "public")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    /**
     * AB@AB.CD is the minimum size of an email address
     */
    @NotBlank
    @Email
    private String email;

    @JsonIgnore
    private Boolean emailVerified;

    @NotBlank
    @Size(min = 3, message = "must be at least 3 characters long")
    private String name;

    private String givenName;

    private String familyName;

    private String picture;

    /**
     * Status of the account
     */
    private Status status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy = "organizer")
    private Set<Events> events;

    private LocalDateTime created;

    private LocalDateTime modified;

    public Users setDefaults() {
        this.status = Status.ACTIVE;
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
        return this;
    }
}
