CREATE TABLE customers (
    id int(11) NOT NULL auto_increment PRIMARY KEY,
    username varchar(100) NOT NULL,
    password varchar(240) NOT NULL,
    credit_card_number varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 auto_increment=1 ;


CREATE TABLE ticket_bookings (
    id int(11) NOT NULL auto_increment PRIMARY KEY,
    customer_id int(11) NOT NULL,
    screening_id int(11) NOT NULL,
    seat varchar(240) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 auto_increment=1 ;


CREATE TABLE admins (
	id int(11) NOT NULL auto_increment PRIMARY KEY,
	username varchar(100) NOT NULL,
	password varchar(240) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 auto_increment=1 ;

CREATE TABLE movies (
	id int(11) NOT NULL auto_increment PRIMARY KEY,
	title varchar(240) NOT NULL,
	trailer_url varchar(240) NOT NULL,
	synopsis text NOT NULL,
	startDate date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 auto_increment=1 ;


CREATE TABLE movie_reviews (
	id int(11) NOT NULL auto_increment PRIMARY KEY,
	movie_id int(11) NOT NULL,
	customer_id int(11) NOT NULL,
	content text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 auto_increment=1 ;


CREATE TABLE newsletters (
	id int(11) NOT NULL auto_increment PRIMARY KEY,
	content text NOT NULL,
	date date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 auto_increment=1 ;


-- CREATE TABLE screenings (
-- 	id int(11) NOT NULL auto_increment PRIMARY KEY,
-- 	movie_d int(11) NOT NULL,
-- 	price decimal NOT NULL,
-- 	-- seats varchar(240) NOT NULL,
-- 	start_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
-- ) ENGINE=InnoDB DEFAULT CHARSET=latin1 auto_increment=1 ;