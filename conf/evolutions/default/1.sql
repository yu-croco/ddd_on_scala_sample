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
    detail TEXT,
    user_id BIGINT NOT NULL REFERENCES users(id)
);

-- !Downs
DROP TABLE public.tasks CASCADE;
DROP TABLE public.users CASCADE;