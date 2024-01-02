

-- postgreslq
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL ,
    -- first_name , last_name, combination must be unique
    CONSTRAINT unique_user UNIQUE (first_name, last_name),
    email VARCHAR(255) NOT NULL UNIQUE,
    user_password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL UNIQUE
);

