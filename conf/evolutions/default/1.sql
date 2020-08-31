-- !Ups
CREATE TABLE public.monsters
(
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    life BIGSERIAL NOT NULL,
    defense_power BIGSERIAL NOT NULL,
    offense_power  BIGSERIAL NOT NULL
);

CREATE TABLE public.monster_materials
(
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    rarity BIGSERIAL NOT NULL,
    monster_id TEXT NOT NULL REFERENCES monsters(id)
);

CREATE TABLE public.hunters
(
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    life BIGSERIAL NOT NULL,
    defense_power BIGSERIAL NOT NULL,
    offense_power  BIGSERIAL NOT NULL
);

CREATE TABLE public.hunters_monster_materials
(
    id TEXT PRIMARY KEY,
    hunter_id TEXT NOT NULL REFERENCES hunters(id),
    monster_materials_id TEXT NOT NULL REFERENCES monster_materials(id)
);


-- !Downs
DROP TABLE public.hunters_monster_materials CASCADE;
DROP TABLE public.hunters CASCADE;
DROP TABLE public.monster_materials CASCADE;
DROP TABLE public.monsters CASCADE;