CREATE TABLE person
(
    id         UUID PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    birth_date DATE
);

CREATE TABLE address
(
    id           UUID PRIMARY KEY,
    street_name  VARCHAR(255),
    commune_code VARCHAR(255),
    house_number VARCHAR(255),
    flat_number  VARCHAR(255),
    is_default   BOOLEAN,
    person_id    UUID,
    FOREIGN KEY (person_id) REFERENCES person (id)
);
