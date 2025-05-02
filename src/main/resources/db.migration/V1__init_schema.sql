CREATE TABLE genre (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL
);

CREATE TABLE video (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       genre_id INT REFERENCES genre(id),
                       bid_id INT
);

CREATE TABLE bid (
                     id SERIAL PRIMARY KEY,
                     content TEXT NOT NULL,
                     video_id INT REFERENCES video(id),
                     timestamp TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);
