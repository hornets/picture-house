README.txt
As requested in specification this application has been developed as a true networked machine with its database being in the cloud. As such, to run this application, you need to be connected to the internet. The data has been already preloaded into that database, so all you have to do is run MainFrame.java. The application will automatically connect to the database and will be ready for interaction with preloaded data.

Unfortunatelly we didn't have time to complete Admin interface so if you wish to load your own data, you can run Java code using controller methods (see examples in DataInjector.java file). E.g. this creates new Ticket booking:

Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://db4free.net:3306/picturehouse", "picturehouse", "65CEerFwXESQmL9nDaE");
TicketBookingController controller = new TicketBookingController();
controller.create(1, 5, 4, false);
Base.close();


You can also run SQL commands directly into the database the application is using. You can connect to the database by running the following command in the Unix terminal (given that you have MySql installed).
`mysql -u picturehouse -h db4free.net -p65CEerFwXESQmL9nDaE picturehouse`