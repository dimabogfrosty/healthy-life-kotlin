CREATE TABLE users
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    birth_date DATE,
    email VARCHAR(255) UNIQUE NOT NULL,
    gender VARCHAR(255),
    weight DECIMAL,
    height DECIMAL
);

CREATE TABLE roles
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE users_roles
(
    user_id INTEGER,
    role_id INTEGER,
    CONSTRAINT user_id_role_id_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT users_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT users_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE achievements
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    image_src VARCHAR(255)
);

CREATE TABLE users_achievements
(
    user_id INTEGER,
    achievement_id INTEGER,
    CONSTRAINT user_id_achievement_id_pkey PRIMARY KEY (user_id, achievement_id),
    CONSTRAINT users_achievements_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT users_achievements_achievement_id_fkey FOREIGN KEY (achievement_id) REFERENCES achievements (id)
);

CREATE TABLE records
(
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    run_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    CONSTRAINT records_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id)
);

--CREATE TABLE trainings
--(
--    id SERIAL PRIMARY KEY,
--    name VARCHAR(255),
--    day_count INTEGER
--);
--
--CREATE TABLE exercises
--(
--    id SERIAL PRIMARY KEY,
--    day_count INTEGER,
--    training_id INTEGER NOT NULL,
--    CONSTRAINT exercises_training_id_fkey FOREIGN KEY (training_id) REFERENCES trainings (id)
--);