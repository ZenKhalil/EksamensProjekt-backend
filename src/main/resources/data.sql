-- Creating roles
INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('USER');

-- Creating users
INSERT INTO user (username, password) VALUES ('admin', '1234Qwssfe5@');
INSERT INTO user (username, password) VALUES ('user1', 'password123');

-- Assign roles to users
INSERT INTO user_roles (user_id, role_id) VALUES (
                                                     (SELECT id FROM user WHERE username = 'admin'),
                                                     (SELECT id FROM role WHERE name = 'ADMIN')
                                                 );

INSERT INTO user_roles (user_id, role_id) VALUES (
                                                     (SELECT id FROM user WHERE username = 'user1'),
                                                     (SELECT id FROM role WHERE name = 'USER')
                                                 );

-- Adding participants
INSERT INTO participant (name, gender, age, club, user_id) VALUES ('Nathan West', 'Female', 64, 'Fuller LLC', (SELECT id FROM user WHERE username = 'admin'));
INSERT INTO participant (name, gender, age, club, user_id) VALUES ('Gabriella Jimenez', 'Female', 11, 'Jones, Watkins and Paul', (SELECT id FROM user WHERE username = 'admin'));

-- Adding disciplines
INSERT INTO discipline (name, result_type, user_id) VALUES ('Discus Throw', 'Distance', (SELECT id FROM user WHERE username = 'admin'));
INSERT INTO discipline (name, result_type, user_id) VALUES ('Shot Put', 'Distance', (SELECT id FROM user WHERE username = 'admin'));

-- Adding results
INSERT INTO result (result_type, date, result_value, participant_id, discipline_id) VALUES (
                                                                                               'Distance', '2024-02-15', '8.71',
                                                                                               (SELECT id FROM participant WHERE name = 'Nathan West'),
                                                                                               (SELECT id FROM discipline WHERE name = 'Discus Throw')
                                                                                           );

INSERT INTO result (result_type, date, result_value, participant_id, discipline_id) VALUES (
                                                                                               'Distance', '2023-07-04', '1.13',
                                                                                               (SELECT id FROM participant WHERE name = 'Nathan West'),
                                                                                               (SELECT id FROM discipline WHERE name = 'Shot Put')
                                                                                           );
