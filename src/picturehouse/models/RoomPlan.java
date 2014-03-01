package picturehouse.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

@Table("room_plan")
@IdName("id")
public class RoomPlan extends Model{
    static {
        validatePresenceOf("seat_number").message("A valid seat number is required");
    }    
}