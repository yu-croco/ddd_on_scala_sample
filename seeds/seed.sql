-- CLAEN UP
TRUNCATE hunters_monster_materials, monster_materials, monsters, hunters RESTART IDENTITY;

-- INSERT SEED
INSERT INTO public.monsters VALUES
    ('32de8cb2-76ac-4fc1-a3b4-372e446bf5bf', 'らんぽす', 100, 10, 5),
    ('33de8cb2-76ac-4fc1-a3b4-372e446bf5bf', 'ふるふる', 1500, 150, 180);

INSERT INTO public.monster_materials VALUES
    ('32de8cb2-76ac-4fc1-a3b4-372e446bf0bf', 'ランポスの皮', 1, '32de8cb2-76ac-4fc1-a3b4-372e446bf5bf'),
    ('32de8cb2-76ac-4fc1-a3b4-372e446bf1bf', 'ランポスの爪', 1, '32de8cb2-76ac-4fc1-a3b4-372e446bf5bf'),
    ('32de8cb2-76ac-4fc1-a3b4-372e446bf2bf', 'アルビノ', 2, '33de8cb2-76ac-4fc1-a3b4-372e446bf5bf'),
    ('32de8cb2-76ac-4fc1-a3b4-372e446bf3bf', '電気袋', 2, '33de8cb2-76ac-4fc1-a3b4-372e446bf5bf');

INSERT INTO public.hunters VALUES
    ('01de8cb2-76ac-4fc1-a3b4-372e446bf0bf', '新米ハンター', 1000, 100, 100),
    ('02de8cb2-76ac-4fc1-a3b4-372e446bf0bf', '中級ハンター', 3500, 480, 390);

INSERT INTO public.hunters_monster_materials VALUES
('01de8cb2-76ac-4fc1-a30s-372e446bf0bf', '02de8cb2-76ac-4fc1-a3b4-372e446bf0bf', '32de8cb2-76ac-4fc1-a3b4-372e446bf3bf');
