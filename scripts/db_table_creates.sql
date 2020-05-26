CREATE USER orderup_rw WITH
    LOGIN
    NOSUPERUSER
    INHERIT
    NOCREATEDB
    NOCREATEROLE
    NOREPLICATION;


CREATE TABLE public.users
(
    id             text NOT NULL,
    email          text NOT NULL,
    email_verified boolean DEFAULT false,
    name           text NOT NULL,
    given_name     text,
    family_name    text,
    picture        text,
    status         TEXT DEFAULT 'ACTIVE'::text,
    created        timestamp without time zone DEFAULT now(),
    modified       timestamp without time zone DEFAULT now(),
    CONSTRAINT users_id_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

GRANT ALL ON TABLE public.users TO "orderup-admin";
GRANT SELECT, INSERT, UPDATE, DELETE ON public.users TO orderup_rw;



CREATE TABLE public.events
(
    id           BIGSERIAL NOT NULL,
    name         text      NOT NULL,
    organizer_id TEXT      NOT NULL,
    created      timestamp without time zone DEFAULT now(),
    modified     timestamp without time zone DEFAULT now(),
    status       TEXT      NOT NULL,
    CONSTRAINT events_id_pkey PRIMARY KEY (id),
    CONSTRAINT fk_organizer_id FOREIGN KEY (organizer_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE CASCADE ON DELETE CASCADE
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

GRANT ALL ON TABLE public.events TO "orderup-admin";
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.events TO orderup_rw;



CREATE TABLE public.items
(
    id       BIGSERIAL,
    event_id bigint NOT NULL,
    name     TEXT   NOT NULL,
    status   TEXT   NOT NULL,
    created  timestamp without time zone DEFAULT now(),
    modified timestamp without time zone DEFAULT now(),
    CONSTRAINT items_id_pkey PRIMARY KEY (id),
    CONSTRAINT fk_event_id FOREIGN KEY (event_id)
        REFERENCES public.events (id) MATCH SIMPLE
        ON UPDATE CASCADE ON DELETE CASCADE
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;


GRANT ALL ON TABLE public.items TO "orderup-admin";
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.items TO orderup_rw;



CREATE TABLE public.events_items
(
    id       BIGSERIAL,
    event_id bigint NOT NULL,
    item_id bigint NOT NULL,
    CONSTRAINT events_items_id_pkey PRIMARY KEY (id),
    CONSTRAINT fk_event_id FOREIGN KEY (event_id)
        REFERENCES public.events (id) MATCH SIMPLE
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_items_id FOREIGN KEY (item_id)
        REFERENCES public.items (id) MATCH SIMPLE
        ON UPDATE CASCADE ON DELETE CASCADE
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;


GRANT ALL ON TABLE public.events_items TO "orderup-admin";
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.events_items TO orderup_rw;



CREATE TABLE public.orders
(
    id            BIGSERIAL,
    guest_id      TEXT    NOT NULL,
    event_item_id bigint NOT NULL,
    quantity      integer not null,
    comments      TEXT,
    status        TEXT    NOT NULL,
    created       timestamp without time zone DEFAULT now(),
    modified      timestamp without time zone DEFAULT now(),
    CONSTRAINT orders_id_pkey PRIMARY KEY (id),
    CONSTRAINT fk_guest_id FOREIGN KEY (guest_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_event_item_id FOREIGN KEY (event_item_id)
        REFERENCES public.events_items (id) MATCH SIMPLE
        ON UPDATE CASCADE ON DELETE CASCADE
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;


GRANT ALL ON TABLE public.orders TO "orderup-admin";
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.orders TO orderup_rw;