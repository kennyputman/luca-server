INSERT INTO app_user(created_at, updated_at, enabled, locked,
                     email, first_name, last_name, password,
                     user_role)
VALUES (current_timestamp, current_timestamp, TRUE, FALSE,
        'testuser@example.com', 'test', 'user', '$2a$12$LMfS.3j.TrX0N//Q7i0NLubTPI7OZFWa.447mxnL5hgFqFLuTDS7i',
        'USER');


INSERT INTO invoice(created_at, updated_at, description, payment_due, user_id)
VALUES (current_timestamp - INTERVAL '3' DAY, current_timestamp - INTERVAL '4' DAY,
        'Bathroom Renovations', current_timestamp + INTERVAL '30' DAY, 1),
       (current_timestamp - INTERVAL '2' DAY, current_timestamp - INTERVAL '3' DAY,
        'Patio & Deck', current_timestamp + INTERVAL '30' DAY, 1),
       (current_timestamp - INTERVAL '1' DAY, current_timestamp - INTERVAL '2' DAY,
        'Drywall installation', current_timestamp + INTERVAL '30' DAY, 1),
       (current_timestamp, current_timestamp, 'House upgrades', current_timestamp + INTERVAL '30' DAY, 1);


INSERT INTO item(created_at, updated_at, description, price, quantity, invoice_id)
VALUES
    (current_timestamp , current_timestamp, 'installed new kitchen sink (hrs)', 50, 3, 4 ),
    (current_timestamp , current_timestamp, 'Toto sink', 500, 1, 4 ),
    (current_timestamp , current_timestamp, 'Nest smart thermostat', 250, 1, 4 ),
    (current_timestamp , current_timestamp, 'Installed new thermostat', 50, 1, 4 );
