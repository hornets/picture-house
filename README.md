Cinema Tickets Terminal
=====================

This is a coursework project assigned as part of the 2nd year Software Engineering class.
The idea was to develop a GUI ticketing terminal where customers can browse current movies, leave reviews and obviously buy tickets.

The app architecture gernally follows an MVC pattern with views, models and controllers separated in dedicated packages. The backend uses [ActiveJDBC](https://github.com/javalite/activejdbc) (a Java ORM that follows ActiveRecord pattern) and the frontend GUI was developed using Java's Swing. All of the backend Models and Controllers have been tested in a TDD fashion with JUnit, Java's unit testing framework.

## How it works
####Select among the services of terminal

![Home Screen](https://github.com/sevab/picture-house/raw/gh-pages/images/home_screen_view.png)

####Create an account

![Create Account](https://raw.githubusercontent.com/sevab/picture-house/gh-pages/images/%20create_account_view.png)

####Browse movies, write movie reviews and read reviews by other customers.

![Browse movies](https://github.com/sevab/picture-house/raw/gh-pages/images/browse_movies_view.png)

####Choose a screening time and book your seats

![Screening view](https://github.com/sevab/picture-house/raw/gh-pages/images/screenings_view.png)

####Print your tickets

![Print tickets view](https://raw.githubusercontent.com/sevab/picture-house/gh-pages/images/print_ticket_screen.png)

####Read newsletter

![Newsletter view](https://raw.githubusercontent.com/sevab/picture-house/gh-pages/images/newsletter_view.png)


## How to run it
As requested in specification this application has been developed as a true networked machine with its database stored in the cloud. As such, before running this application, you'll need to be connected to the internet. The data has been already preloaded into that database, so all you have to do is run MainFrame.java. The application will automatically connect to the database and will be ready for interaction with preloaded data.

Unfortunatelly we didn't have time to complete Admin interface so if you wish to load your own data, you can run Java code using controller methods (see examples in DataInjector.java file). E.g. this adds a new Movie to the database:

```java
Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://db4free.net:3306/picturehouse", "picturehouse", "$password");
MovieController controller = new MovieController();
controller.create("Inception", "http://www.youtube.com", "A skilled extractor is offered a chance to regain his old life as payment for a task considered to be impossible.", Date.valueOf(lastWednesdayDate));
Base.close();
```
