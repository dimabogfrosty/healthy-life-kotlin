DROP TABLE training_days_exercises;
DROP TABLE training_days;


ALTER TABLE exercises
DROP COLUMN repetitions_number,
ADD COLUMN number INTEGER,
ADD COLUMN reiteration INTEGER,
ADD COLUMN day_of_training INTEGER,
ADD COLUMN training_id INTEGER NOT NULL,
ADD CONSTRAINT exercises_training_id_fkey FOREIGN KEY (training_id) REFERENCES trainings (id)

