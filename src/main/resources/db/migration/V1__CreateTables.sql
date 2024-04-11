CREATE TABLE combat_schema.users (
                                            id INT PRIMARY KEY,
                                            role VARCHAR(255)
);

CREATE TABLE combat_schema.character (
                                            id INT IDENTITY(1,1) PRIMARY KEY,
                                            name VARCHAR(255),
                                            health INT,
                                            mana INT,
                                            base_strength INT,
                                            base_agility INT,
                                            base_intelligence INT,
                                            base_faith INT,
                                            created_by INT
);

CREATE TABLE combat_schema.duel (
                                            id INT IDENTITY(1,1) PRIMARY KEY,
                                            challenger_id INT,
                                            challengee_id INT,
                                            started DATETIME,
                                            completed DATETIME,
                                            status VARCHAR(255),
                                            outcome varchar(255),
                                            challenger_state VARCHAR(255),
                                            challengee_state VARCHAR(255)
);