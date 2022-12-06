ALTER TABLE items ADD COLUMN cost int;

INSERT INTO items (title, cost) VALUES ('Snickers', 40), ('Albini', 100);

UPDATE items SET cost=id*20;