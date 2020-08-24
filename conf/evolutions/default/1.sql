-- !Ups
CREATE TABLE public.monsters
(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    life BIGSERIAL NOT NULL,
    defense_power BIGSERIAL NOT NULL,
    offense_power  BIGSERIAL NOT NULL
);

CREATE TABLE public.monster_materials
(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    rarity BIGSERIAL NOT NULL,
    monster_id BIGINT NOT NULL REFERENCES monsters(id)
);

CREATE TABLE public.hunters
(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    life BIGSERIAL NOT NULL,
    defense_power BIGSERIAL NOT NULL,
    offense_power  BIGSERIAL NOT NULL
);


-- !Downs
DROP TABLE public.tasks CASCADE;
DROP TABLE public.users CASCADE;