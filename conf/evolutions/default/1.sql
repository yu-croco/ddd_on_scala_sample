-- !Ups

CREATE TABLE public.users
(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE public.tasks
(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    detail TEXT  NOT NULL,
    user_id BIGINT NOT NULL REFERENCES users(id)
);

CREATE TABLE public.monsters
(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    life BIGSERIAL NOT NULL,
    defense_power BIGSERIAL NOT NULL,
    attack_power  BIGSERIAL NOT NULL
);

CREATE TABLE public.hunters
(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    life BIGSERIAL NOT NULL,
    defense_power BIGSERIAL NOT NULL,
    attack_power  BIGSERIAL NOT NULL
);

-- !Downs
DROP TABLE public.tasks CASCADE;
DROP TABLE public.users CASCADE;