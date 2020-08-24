BEGIN;
-- CLAEN UP
TRUNCATE TABLE public.hunters;
TRUNCATE TABLE public.monsters;

-- INSERT SEED
INSERT INTO public.monsters VALUES
    (1, 'らんぽす', 100, 10, 5, 'ランポスの皮'),
    (2, 'ふるふる', 1500, 150, 180, 'アルビノ');

INSERT INTO public.hunters VALUES
    (1, '新米ハンター', 1000, 100, 100);

COMMIT;