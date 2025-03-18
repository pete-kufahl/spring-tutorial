INSERT INTO organizers (id, name, description) VALUES (101, 'WM', 'Waste Management');
INSERT INTO organizers (id, name, description) VALUES (102, 'CTAS', 'Cintas Corporation');

INSERT INTO venues (id, name, street, city, country) VALUES (201, 'PHX Arena', '201 E Jefferson St', 'Phoenix', 'USA');
INSERT INTO venues (id, name, street, city, country) VALUES (202,  'Mesa Convention Center', '263 N Center St', 'Mesa', 'USA');

INSERT INTO events (id, name, organizer_id, venue_id, start_date, end_date) VALUES (501, 'Waste Management Annual Conference', 101, 201, '2025-10-06', '2025-10-08');
INSERT INTO events (id, name, organizer_id, venue_id, start_date, end_date) VALUES (502, 'Cintas Annual Shareholders Meeting', 102, 201, '2025-09-07', '2025-09-08');
INSERT INTO events (id, name, organizer_id, venue_id, start_date, end_date) VALUES (503, 'Cintas Special Conference', 102, 202, '2025-10-01', '2025-10-03');

INSERT INTO products (id, event_id, name, description, price) VALUES (801, 501, 'Standard', 'Standard Conference Ticket', 49.99);
INSERT INTO products (id, event_id, name, description, price) VALUES (802, 501, 'Premium', 'Premium Conference Ticket', 64.99);
INSERT INTO products (id, event_id, name, description, price) VALUES (803, 502, 'Standard', 'Personnel Day Ticket', 19.95);
INSERT INTO products (id, event_id, name, description, price) VALUES (804, 503, 'Regular', 'Regular Entrance', 35.00);
INSERT INTO products (id, event_id, name, description, price) VALUES (805, 503, 'VIP', 'VIP Bonus Entrance', 65.00);
