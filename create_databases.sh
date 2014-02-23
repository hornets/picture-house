#  Before running the script login to mysql first by running
# mysql -u root -p

# Cretae development database
mysql> create database picturehouse_development;
Query OK, 1 row affected (0.00 sec)

mysql> grant usage on *.* to testuser@localhost identified by 'testuserpassword';
Query OK, 0 rows affected (0.00 sec)

mysql> grant all privileges on picturehouse_development.* to testuser@localhost;
Query OK, 0 rows affected (0.00 sec)

# Create test database
mysql> create database picturehouse_test;
Query OK, 1 row affected (0.01 sec)

mysql> grant all privileges on picturehouse_test.* to testuser@localhost;
Query OK, 0 rows affected (0.00 sec)
