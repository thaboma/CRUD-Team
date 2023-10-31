CREATE SCHEMA IF NOT EXISTS test;

CREATE TABLE IF NOT EXISTS test.team
(
    id bigint NOT NULL,
    created_date timestamp without time zone,
    manager_id character varying(50) ,
    team_name character varying(50) ,
    CONSTRAINT team_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS test.employee
(
    id bigint NOT NULL ,
    created_date timestamp without time zone,
    date_of_birth timestamp without time zone,
    employee_id character varying(50) NOT NULL,
    name character varying(50),
    last_name character varying(50),
    team_id bigint NOT NULL,
    CONSTRAINT employee_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS test.vehicle
(
    id bigint NOT NULL ,
    acquired_date timestamp without time zone,
    model character varying(255),
    colour character varying(50),
    name character varying(50),
    vin_number character varying(50),
    team_id bigint NOT NULL,
    CONSTRAINT vehicle_pkey PRIMARY KEY (id)
    );

SET SCHEMA test;