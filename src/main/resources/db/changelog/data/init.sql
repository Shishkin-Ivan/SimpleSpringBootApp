INSERT INTO address (address_id, building, street, city, region, latitude, longitude)
VALUES
    (gen_random_uuid(), 12, 'Main Street', 'New York', 'NY', 40.7128, -74.0060),					-- 1
    (gen_random_uuid(), 15, 'Broadway', 'Los Angeles', 'CA', 34.0522, -118.2437),					-- 2
    (gen_random_uuid(), 25, '5th Avenue', 'New York', 'NY', 40.7580, -73.9855),					-- 3
    (gen_random_uuid(), 100, 'Market Street', 'San Francisco', 'CA', 37.7749, -122.4194),			-- 4
    (gen_random_uuid(), 200, 'Michigan Avenue', 'Chicago', 'IL', 41.8781, -87.6298),				-- 5
    (gen_random_uuid(), 50, 'Ocean Drive', 'Miami', 'FL', 25.7617, -80.1918),						-- 6
    (gen_random_uuid(), 75, 'Beale Street', 'Memphis', 'TN', 35.1495, -90.0490),					-- 7
    (gen_random_uuid(), 101, 'Bourbon Street', 'New Orleans', 'LA', 29.9584, -90.0653),			-- 8
    (gen_random_uuid(), 300, 'Las Vegas Blvd', 'Las Vegas', 'NV', 36.1699, -115.1398),			    -- 9
    (gen_random_uuid(), 500, 'Pennsylvania Avenue', 'Washington', 'DC', 38.9072, -77.0369);		-- 10

INSERT INTO services (service_id, name, description, type)
VALUES
    (gen_random_uuid(), 'Guided Tour', 'A guided tour of the attraction.', 'GUIDE'),							    -- 1
    (gen_random_uuid(), 'Audio Guide', 'An audio guide for self-guided tours.', 'GUIDE'),						-- 2
    (gen_random_uuid(), 'Skip-the-Line Pass', 'Priority access to the attraction.', 'ADDITIONAL'),				-- 3
    (gen_random_uuid(), 'Virtual Tour', 'An online virtual experience.', 'EDUCATIONAL'),						    -- 4
    (gen_random_uuid(), 'Photo Session', 'Professional photography at the attraction.', 'SPECIALIZED'),			-- 5
    (gen_random_uuid(), 'VIP Tour', 'Exclusive tour with special access.', 'GUIDE'),							    -- 6
    (gen_random_uuid(), 'Boat Tour', 'Scenic boat ride experience.', 'GUIDE'),									-- 7
    (gen_random_uuid(), 'Fast Pass', 'Quick entry to avoid long lines.', 'ADDITIONAL'),							-- 8
    (gen_random_uuid(), 'Live Performance', 'Special live show at the site.', 'SPECIALIZED'),					-- 9
    (gen_random_uuid(), 'Souvenir Package', 'Exclusive souvenirs for visitors.', 'SPECIALIZED');				    -- 10


INSERT INTO tickets_info (ticket_info_id, price, currency, availability)
VALUES (gen_random_uuid(), 20.0, 'USD', TRUE),			-- 1
       (gen_random_uuid(), 15.5, 'USD', FALSE),			-- 2
       (gen_random_uuid(), 25.0, 'USD', TRUE),			-- 3
       (gen_random_uuid(), 10.0, 'USD', TRUE),			-- 4
       (gen_random_uuid(), 30.0, 'USD', FALSE),			-- 5
       (gen_random_uuid(), 50.0, 'USD', TRUE),			-- 6
       (gen_random_uuid(), 35.0, 'USD', FALSE),			-- 7
       (gen_random_uuid(), 40.0, 'USD', TRUE),			-- 8
       (gen_random_uuid(), 22.0, 'USD', TRUE),			-- 9
       (gen_random_uuid(), 60.0, 'USD', FALSE);			-- 10


INSERT INTO attractions (attraction_id, name, description, type, address_id, ticket_info_id)
VALUES
    -- 1 --
    (gen_random_uuid(), 'Central Park', 'A large urban park in Manhattan.', 'PARK',
     (SELECT address_id FROM address WHERE street = 'Main Street'),
     (SELECT ticket_info_id FROM tickets_info WHERE price = 15.5)),
    -- 2 --
    (gen_random_uuid(), 'Hollywood Sign', 'Iconic landmark in Los Angeles.', 'LANDMARK',
     (SELECT address_id FROM address WHERE street = 'Broadway'),
     (SELECT ticket_info_id FROM tickets_info WHERE price = 20.0)),
    -- 3 --
    (gen_random_uuid(), 'Empire State Building', 'Iconic skyscraper with observation decks.', 'LANDMARK',
     (SELECT address_id FROM address WHERE street = '5th Avenue'),
     (SELECT ticket_info_id FROM tickets_info WHERE price = 25.0)),
    -- 4 --
    (gen_random_uuid(), 'Golden Gate Bridge', 'Famous suspension bridge in San Francisco.', 'LANDMARK',
     (SELECT address_id FROM address WHERE street = 'Market Street'),
     (SELECT ticket_info_id FROM tickets_info WHERE price = 10.0)),
    -- 5 --
    (gen_random_uuid(), 'Cloud Gate', 	'Public sculpture known as "The Bean" in Chicago.', 'LANDMARK',
     (SELECT address_id FROM address WHERE street = 'Michigan Avenue'),
     (SELECT ticket_info_id FROM tickets_info WHERE price = 30.0)),
    -- 6 --
    (gen_random_uuid(), 'Miami Beach', 'Popular beach with nightlife and art deco architecture.', 'PARK',
     (SELECT address_id FROM address WHERE street = 'Ocean Drive'),
     (SELECT ticket_info_id FROM tickets_info WHERE price = 35.0)),
    -- 7 --
    (gen_random_uuid(), 'Graceland', 'Home of Elvis Presley.', 'MUSEUM',
     (SELECT address_id FROM address WHERE street = 'Beale Street'),
     (SELECT ticket_info_id FROM tickets_info WHERE price = 50.0)),
    -- 8 --
    (gen_random_uuid(), 'French Quarter', 'Historic district known for jazz music and festivals.', 'CULTURAL',
     (SELECT address_id FROM address WHERE street = 'Bourbon Street'),
     (SELECT ticket_info_id FROM tickets_info WHERE price = 40.0)),
    -- 9 --
    (gen_random_uuid(), 'Las Vegas Strip', 'Famous road with casinos and entertainment.', 'ENTERTAINMENT',
     (SELECT address_id FROM address WHERE street = 'Las Vegas Blvd'),
     (SELECT ticket_info_id FROM tickets_info WHERE price = 22.0)),
    -- 10 --
    (gen_random_uuid(), 'White House', 'Official residence of the U.S. President.', 'GOVERNMENT',
     (SELECT address_id FROM address WHERE street = 'Pennsylvania Avenue'),
     (SELECT ticket_info_id FROM tickets_info WHERE price = 60.0));


INSERT INTO attraction_service (attraction_id, service_id)
VALUES
    -- 1 --
    ((SELECT attraction_id FROM attractions WHERE name = 'Central Park'),
     (SELECT service_id FROM services WHERE name = 'Guided Tour')),
    -- 2 --
    ((SELECT attraction_id FROM attractions WHERE name = 'Hollywood Sign'),
     (SELECT service_id FROM services WHERE name = 'Audio Guide')),
    -- 3 --
    ((SELECT attraction_id FROM attractions WHERE name = 'Empire State Building'),
     (SELECT service_id FROM services WHERE name = 'Skip-the-Line Pass')),
    -- 4 --
    ((SELECT attraction_id FROM attractions WHERE name = 'Golden Gate Bridge'),
     (SELECT service_id FROM services WHERE name = 'Virtual Tour')),
    -- 5 --
    ((SELECT attraction_id FROM attractions WHERE name = 'Cloud Gate'),
     (SELECT service_id FROM services WHERE name = 'Photo Session')),
    -- 6 --
    ((SELECT attraction_id FROM attractions WHERE name = 'Miami Beach'),
     (SELECT service_id FROM services WHERE name = 'Boat Tour')),
    -- 7 --
    ((SELECT attraction_id FROM attractions WHERE name = 'Graceland'),
     (SELECT service_id FROM services WHERE name = 'VIP Tour')),
    -- 8 --
    ((SELECT attraction_id FROM attractions WHERE name = 'French Quarter'),
     (SELECT service_id FROM services WHERE name = 'Live Performance')),
    -- 9 --
    ((SELECT attraction_id FROM attractions WHERE name = 'Las Vegas Strip'),
     (SELECT service_id FROM services WHERE name = 'Fast Pass')),
    -- 10 --
    ((SELECT attraction_id FROM attractions WHERE name = 'White House'),
     (SELECT service_id FROM services WHERE name = 'Souvenir Package')),
    -- 11 --
    ((SELECT attraction_id FROM attractions WHERE name = 'Golden Gate Bridge'),
     (SELECT service_id FROM services WHERE name = 'Photo Session')),
    -- 12 --
    ((SELECT attraction_id FROM attractions WHERE name = 'Las Vegas Strip'),
     (SELECT service_id FROM services WHERE name = 'Photo Session')),
    -- 13 --
    ((SELECT attraction_id FROM attractions WHERE name = 'Central Park'),
     (SELECT service_id FROM services WHERE name = 'Photo Session')),
    -- 14 --
    ((SELECT attraction_id FROM attractions WHERE name = 'Central Park'),
     (SELECT service_id FROM services WHERE name = 'Audio Guide'));
