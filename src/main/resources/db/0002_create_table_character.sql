CREATE TABLE origin
(
    id   BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255),
    url  VARCHAR(255)
);

CREATE TABLE location
(
    id   BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255),
    url  VARCHAR(255)
);

CREATE TABLE episode
(
    id    BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title VARCHAR(255)
);

CREATE TABLE character
(
    id          INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name        VARCHAR(255),
    status      VARCHAR(255),
    species     VARCHAR(255),
    type        VARCHAR(255),
    gender      VARCHAR(255),
    origin_id   INT,
    location_id INT,
    image       VARCHAR(255),
    FOREIGN KEY (origin_id) REFERENCES origin (id),
    FOREIGN KEY (location_id) REFERENCES location (id)
);