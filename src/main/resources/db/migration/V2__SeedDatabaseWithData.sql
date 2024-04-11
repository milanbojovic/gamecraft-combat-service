-- Insert data into users table
INSERT INTO combat_schema.users (id, role)
VALUES (1, 'ROLE_GameMaster'),
       (2, 'User');
-- Insert data into character table
INSERT INTO combat_schema.character (name, health, mana, base_strength, base_agility, base_intelligence, base_faith, created_by)
VALUES ('Aethelred', 100, 50, 10, 5, 5, 5, 1),
       ('Balgar', 8000, 70, 5, 5, 10, 5, 2),
       ('Caelum', 9000, 60, 5, 10, 5, 5, 2),
       ('Kentaur', 80, 60, 5, 10, 5, 5, 3);
