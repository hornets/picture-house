DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
    id int(11) NOT NULL auto_increment PRIMARY KEY,
    username varchar(100) NOT NULL,
    password varchar(240) NOT NULL,
    credit_card_number varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 auto_increment=1 ;

DROP TABLE IF EXISTS ticket_bookings;
CREATE TABLE ticket_bookings (
    id int(11) NOT NULL auto_increment PRIMARY KEY,
    customer_id int(11) NOT NULL,
    screening_id int(11) NOT NULL,
    seat varchar(240) NOT NULL,
    is_printed 
) ENGINE=InnoDB DEFAULT CHARSET=latin1 auto_increment=1 ;

DROP TABLE IF EXISTS admins;
CREATE TABLE admins (
	id int(11) NOT NULL auto_increment PRIMARY KEY,
	username varchar(100) NOT NULL,
	password varchar(240) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 auto_increment=1 ;

DROP TABLE IF EXISTS movies;
CREATE TABLE movies (
	id int(11) NOT NULL auto_increment PRIMARY KEY,
	title varchar(240) NOT NULL,
	trailer_url varchar(240) NOT NULL,
	synopsis text NOT NULL,
	start_date date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 auto_increment=1 ;

DROP TABLE IF EXISTS movie_reviews;
CREATE TABLE movie_reviews (
	id int(11) NOT NULL auto_increment PRIMARY KEY,
	movie_id int(11) NOT NULL,
	customer_id int(11) NOT NULL,
	content text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 auto_increment=1 ;

DROP TABLE IF EXISTS newsletters;
CREATE TABLE newsletters (
	id int(11) NOT NULL auto_increment PRIMARY KEY,
	content text NOT NULL,
	date date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 auto_increment=1 ;

DROP TABLE IF EXISTS screenings;
CREATE TABLE screenings (
	id int(11) NOT NULL auto_increment PRIMARY KEY,
	movie_id int(11) NOT NULL,
	price decimal NOT NULL,
	start_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1 auto_increment=1 ;