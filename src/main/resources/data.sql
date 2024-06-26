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

INSERT INTO participant (name, gender, age, club, user_id) VALUES
                                                               ('Rebecca Parker', 'Male', 41, 'Porter, Hamilton and Johnson', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Gregory Gordon', 'Female', 63, 'Leach-Goodwin', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('David Reese', 'Male', 70, 'Frank, Buckley and Thompson', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Maria Robinson', 'Female', 68, 'Gaines Inc', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Lisa Jackson', 'Female', 11, 'Nelson-Smith', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Amy Petty', 'Male', 28, 'Brown-Spencer', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Paige Mcintyre', 'Male', 68, 'Brown, Turner and Moore', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Kathryn Turner', 'Male', 37, 'Mills-Long', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Steven Brown', 'Male', 47, 'Turner Ltd', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Russell Brown', 'Male', 20, 'Richard-Olson', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Richard Meyer', 'Male', 12, 'Wu-Sanders', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Tina Randall', 'Female', 51, 'Robles and Sons', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('James Robinson', 'Male', 19, 'Powell, Garcia and Jones', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Katherine Howard DDS', 'Male', 34, 'Michael, Henderson and Henderson', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Alison Riggs', 'Female', 23, 'Fox-Washington', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Alan Baker', 'Female', 64, 'Richardson LLC', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Lisa Clements MD', 'Female', 50, 'Hill-Jones', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Matthew Richardson', 'Male', 7, 'Shaw, Allen and House', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('James Roberts', 'Female', 67, 'Pham-Hardy', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Thomas Patton', 'Male', 59, 'Brown Inc', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Brandon Kelley', 'Male', 25, 'Silva-Smith', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Douglas Lewis', 'Female', 69, 'Johnson PLC', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Kelsey Moore', 'Male', 9, 'Larsen-Calderon', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('William Garcia', 'Female', 22, 'Gibbs, Page and Escobar', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Dustin Brown', 'Female', 66, 'Patterson Ltd', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Kristy Burns', 'Male', 11, 'Wood and Sons', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Pamela Foley', 'Male', 25, 'Donovan LLC', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Alexander Lucas', 'Male', 46, 'Madden, Bell and Gibson', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Catherine Hughes', 'Female', 68, 'Johnson Group', (SELECT id FROM user WHERE username = 'admin')),
                                                               ('Kimberly Williams', 'Male', 36, 'Dickerson Group', (SELECT id FROM user WHERE username = 'admin'));
INSERT INTO discipline (name, result_type, user_id) VALUES
                                                        ('100m', 'Time', (SELECT id FROM user WHERE username = 'admin')),
                                                        ('200m', 'Time', (SELECT id FROM user WHERE username = 'admin')),
                                                        ('Long Jump', 'Distance', (SELECT id FROM user WHERE username = 'admin')),
                                                        ('High Jump', 'Height', (SELECT id FROM user WHERE username = 'admin')),
                                                        ('Discus Throw', 'Distance', (SELECT id FROM user WHERE username = 'admin')),
                                                        ('Shot Put', 'Distance', (SELECT id FROM user WHERE username = 'admin'));
INSERT INTO result (result_type, date, result_value, participant_id, discipline_id) VALUES
    ('Time', '2023-10-19', '27.7', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 0)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 1))),
('Time', '2023-10-04', '61.87', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 0)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 0))),
('Distance', '2024-03-29', '1.38', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 0)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 2))),
('Time', '2024-06-18', '39.24', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 0)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 0))),
('Time', '2023-07-25', '34.97', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 1)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 0))),
('Time', '2023-10-28', '43.79', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 1)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 1))),
('Height', '2024-03-08', '1.41', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 1)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 3))),
('Height', '2023-12-10', '3.81', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 1)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 3))),
('Distance', '2023-07-10', '2.58', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 1)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 2))),
('Distance', '2023-12-05', '10.6', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 2)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 5))),
('Time', '2023-09-05', '63.79', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 2)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 0))),
('Distance', '2024-02-03', '2.11', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 2)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 4))),
('Distance', '2023-11-12', '9.24', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 3)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 5))),
('Distance', '2024-03-01', '4.35', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 4)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 4))),
('Distance', '2024-03-03', '7.84', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 4)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 2))),
('Time', '2023-09-03', '57.73', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 4)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 1))),
('Distance', '2023-10-13', '6.72', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 4)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 2))),
('Distance', '2023-07-19', '1.54', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 4)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 4))),
('Distance', '2024-01-19', '5.47', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 5)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 2))),
('Time', '2023-11-15', '98.10', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 5)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 1))),
('Time', '2024-04-16', '30.9', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 5)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 0))),
('Distance', '2024-04-04', '1.31', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 6)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 4))),
('Height', '2023-06-28', '1.15', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 6)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 3))),
('Time', '2024-05-25', '82.49', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 7)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 1))),
('Distance', '2023-10-05', '5.35', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 7)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 2))),
('Distance', '2023-06-28', '6.75', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 7)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 4))),
('Height', '2023-08-30', '2.49', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 8)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 3))),
('Distance', '2023-06-24', '2.34', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 9)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 4))),
('Time', '2023-08-18', '73.97', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 9)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 1))),
('Distance', '2024-01-07', '6.68', (SELECT id FROM participant WHERE name = (SELECT name FROM participant LIMIT 1 OFFSET 9)), (SELECT id FROM discipline WHERE name = (SELECT name FROM discipline LIMIT 1 OFFSET 2)));
