CREATE TABLE customers (
    id int(11) NOT NULL auto_increment PRIMARY KEY,
    username varchar(100) NOT NULL,
    password varchar(240) NOT NULL,
    credit_card_number int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;