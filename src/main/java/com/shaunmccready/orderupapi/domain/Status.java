package com.shaunmccready.orderupapi.domain;

import com.shaunmccready.orderupapi.exception.NoSuchElementException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Declaring the different Statuses that a record could have in the database
 */
public enum Status implements Serializable {

    ACTIVE("ACTIVE"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED");

    @Getter
    private String name;

    Status(String name) {
        this.name = name;
    }

    public static List<Status> getAllStatus() {
        return Arrays.asList(Status.values());
    }


    public static Status getFromName(final String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }

        for (Status status : Status.values()) {
            if (status.getName().equalsIgnoreCase(name)) {
                return status;
            }
        }

        throw new NoSuchElementException(String.format("No status with the name [%s] was found", name));
    }

}
