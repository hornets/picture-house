package picturehouse.controllers;

import picturehouse.models.Admin;

/**
 *
 * @author sevabaskin
 */
public class AdminController {

    public AdminController() {
    }
    public void create(String username, String password) {
       new Admin().set("username", username)
                  .set("password", password)
                  .saveIt();
    }

    boolean verifyCredentials(String username, String password) {
        Admin admin = Admin.findFirst("username = ?", username);
        return admin.getString("password").equals(password);
    }
}