

-- postgreslq
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL ,
    -- first_name , last_name, combination must be unique
    CONSTRAINT unique_user UNIQUE (first_name, last_name),
    email VARCHAR(255) NOT NULL UNIQUE,
    user_password VARCHAR(60) NOT NULL,
    phone_number VARCHAR(255) NOT NULL UNIQUE
    );



CREATE TABLE IF NOT EXISTS file_mapping (
    original_file_name VARCHAR(255) NOT NULL,
    generated_file_name VARCHAR(255) NOT NULL,
    size INTEGER NOT NULL,
    mime_type VARCHAR(50) NOT NULL,
    PRIMARY KEY (original_file_name, generated_file_name),
    user_id INTEGER NOT NULL,
    path VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE

);


CREATE TABLE IF NOT EXISTS auth_logs (
    ip_address VARCHAR(255) NOT NULL,
    attempted_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    time_spent INTEGER DEFAULT 0,
    user_id INTEGER NOT NULL,
    PRIMARY KEY (ip_address, attempted_at, user_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);