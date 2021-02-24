CREATE TABLE admin (
    admin_id int AUTO_INCREMENT,
    email varchar,
    full_name varchar NOT NULL,
    phone_no varchar NOT NULL,
    PRIMARY KEY (admin_id)
);

CREATE TABLE user (
    user_id int AUTO_INCREMENT,
    email varchar,
    full_name varchar NOT NULL,
    phone_no varchar NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE restaurant (
    id int AUTO_INCREMENT,
    name varchar,
    type_of_food varchar NOT NULL,
    desc varchar,
    admin_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (admin_id) REFERENCES admin(admin_id)
);

CREATE TABLE dining_table (
    table_id int AUTO_INCREMENT,
    table_no int,
    restaurant_id int,
    size int NOT NULL,
    PRIMARY KEY (table_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
);

CREATE TABLE booking (
    booking_id int AUTO_INCREMENT,
    no_of_people int NOT NULL,
    user_id int,
    table_id int,
    PRIMARY KEY (booking_id),
    FOREIGN KEY (userid) REFERENCES user(user_id),
    FOREIGN KEY (tableid) REFERENCES dining_table(table_id)
);