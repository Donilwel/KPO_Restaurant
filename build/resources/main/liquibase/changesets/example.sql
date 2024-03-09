CREATE TABLE IF NOT EXISTS persons (
    id SERIAL PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS dishes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    amount INTEGER NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    time BIGINT NOT NULL,
    is_in_menu BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    order_status VARCHAR(50),
    client_id BIGINT,
    date_time TIMESTAMP
);

CREATE TABLE IF NOT EXISTS dish_amount (
    id SERIAL PRIMARY KEY,
    dish_id BIGINT NOT NULL,
    amount INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS persons_dish_amount (
    person_id BIGINT NOT NULL,
    dish_amount_id BIGINT NOT NULL,
    PRIMARY KEY (person_id, dish_amount_id),
    CONSTRAINT fk_persons_dish_amount_persons FOREIGN KEY(person_id) REFERENCES persons(id) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_persons_dish_amount_dish_amount FOREIGN KEY(dish_amount_id) REFERENCES dish_amount(id) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS feedback (
    id SERIAL PRIMARY KEY,
    grade VARCHAR(50),
    comment TEXT,
    dish_id BIGINT,
    author_id BIGINT
);
