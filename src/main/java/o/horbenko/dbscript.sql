--     POSTGRES IN DB 'DB'

    CREATE TABLE public.test_table
            (
                    id bigint NOT NULL,
    value character varying,
    CONSTRAINT test_table_pk PRIMARY KEY (id)
    )
    WITH (
            OIDS=FALSE
    );
    ALTER TABLE public.test_table
    OWNER TO postgres;