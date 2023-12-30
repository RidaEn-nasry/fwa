BEGIN;

INSERT INTO users (first_name, last_name, password, phone_number) 
VALUES 
('ahmed', 'bouzid', '123456', '123456789'),
('mohammed', 'salman', '123456', '123456789'),
('samir', 'nassim', '123456', '123456789'),
('mounir', 'nassmi', '123456', '123456789');

COMMIT;