CREATE TABLE bids (
                      id SERIAL PRIMARY KEY,
                      content TEXT NOT NULL,
                      video_id INTEGER REFERENCES videos(id),
                      timestamp TIMESTAMP NOT NULL
);
