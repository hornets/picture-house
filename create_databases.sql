-- Before running the script login to mysql first by running
-- mysql -u root -p

-- Cretae development database
create database picturehouse_development;

grant usage on *.* to testuser@localhost identified by 'testuserpassword';

grant all privileges on picturehouse_development.* to testuser@localhost;

-- Cretae test database
create database picturehouse_test;
grant all privileges on picturehouse_test.* to testuser@localhost;
