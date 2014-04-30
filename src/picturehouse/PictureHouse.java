package picturehouse;
import java.util.List;
import picturehouse.models.Customer;
import org.javalite.activejdbc.Base;
import picturehouse.models.Movie;
import picturehouse.models.TicketBooking;


/**
 *
 * @author sevabaskin
 */
// TODO: make as a singleton
public class PictureHouse {

    private Customer currentCustomer = null;
    private Movie selectedMovie;
    // latest batch of TicketBookings by logged in Customer
    // used to print out newly booked tickets
    private List<TicketBooking> currentTicketBookings;
    public PictureHouse() {}

    public static void main(String[] args) {}
    
    
    public void flushCurrentUserData() {
        this.currentCustomer = null;
        this.selectedMovie = null;
        this.currentTicketBookings = null;
    }
    public void setCurrentTicketBookings(List<TicketBooking> ticketBookingsList) {
        this.currentTicketBookings = ticketBookingsList;
    }
    public List<TicketBooking> getCurrentTicketBookings() {
        return this.currentTicketBookings;
    }
    public void resetCurrentTicketBookings() {
        this.currentTicketBookings = null;
    }
    public void setSelectedMovie(Movie movie) {
        this.selectedMovie = movie;
    }
    public Movie getSelectedMovie() {
        return this.selectedMovie;
    }
    public void resetCurrentlySelectedMovie() {
        this.selectedMovie = null;
    }
    public void setCurrentCustomer(Customer customer) {
        this.currentCustomer = customer;
    }
    public Customer getCurrentCustomer() {
        return this.currentCustomer;
    }
    public boolean isCurrentUserAuthorized() {
        return this.currentCustomer != null;
    }
}
