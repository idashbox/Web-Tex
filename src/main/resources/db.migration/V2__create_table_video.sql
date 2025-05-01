CREATE TABLE videos (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        description TEXT,
                        genre_id INTEGER REFERENCES genres(id),
                        bid_id INTEGER
);
