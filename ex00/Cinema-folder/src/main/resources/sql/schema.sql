

-- postgreslq
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL ,
    user_password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL
);

