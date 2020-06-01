CREATE TABLE trainings
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE users_trainings
(
    user_id INTEGER,
    training_id INTEGER,
    CONSTRAINT user_id_training_id_pkey PRIMARY KEY (user_id, training_id),
    CONSTRAINT users_trainings_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT users_trainings_training_id_fkey FOREIGN KEY (training_id) REFERENCES trainings (id)
);

CREATE TABLE training_days
(
    id SERIAL PRIMARY KEY,
    day_number INTEGER,
    training_id INTEGER NOT NULL,
    CONSTRAINT training_days_training_id_fkey FOREIGN KEY (training_id) REFERENCES trainings (id)
);

CREATE TABLE exercises
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    image_src VARCHAR(255),
    repetitions_number INTEGER
);

CREATE TABLE training_days_exercises
(
    exercise_id INTEGER,
    training_day_id INTEGER,
    CONSTRAINT exercise_id_training_day_id_pkey PRIMARY KEY (exercise_id, training_day_id),
    CONSTRAINT exercises_training_days_exercise_id_fkey FOREIGN KEY (exercise_id) REFERENCES exercises (id),
    CONSTRAINT exercises_training_days_training_day_id_fkey FOREIGN KEY (training_day_id) REFERENCES training_days (id)
);