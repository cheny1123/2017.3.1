package netgloo.controllers;

import netgloo.models.User;
import netgloo.models.UserDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author netgloo
 */
@Controller
public class UserController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * /create  --> Create a new user and save it in the database.
   * 
   * @param email User's email
   * @param name User's name
   * @return A string describing if the user is succesfully created or not.
   */
  @RequestMapping("/create")
  @ResponseBody
  public String create(String email, String name,String city) {
    User user = null;
    try {
      user = new User(email, name,city);
      userDao.save(user);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created! (id = " + user.getId() + ")";
  }
  
  /**
   * /delete  --> Delete the user having the passed id.
   * 
   * @param id The id of the user to delete
   * @return A string describing if the user is succesfully deleted or not.
   */
  @RequestMapping("/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      User user = new User(id);
      userDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * /get-by-id  --> Return the id for the user having the passed id.
   * 
   * @param id The id to search in the database.
   * @return The user id or a message error if the user is not found.
   */
  @RequestMapping("/get-by-id")
  @ResponseBody
  public String getById(long id) {
	    String userId;
	    String userName;
	    String userEmail;
	    String userCity;
	    String output="";
	    int i=0;
	    System.out.println(i);
	    try {
	          User user = userDao.findById(id);
	    	  userId = String.valueOf(user.getId());
	    	  userName=String.valueOf(user.getName());
	    	  userEmail=String.valueOf(user.getEmail());
	    	  userCity=String.valueOf(user.getCity());
	          System.out.println("UserId is:\t"+userId);
	          System.out.println("Name is:\t"+userName);
	          System.out.println("Email is:\t"+userEmail);
	          System.out.println("City is:\t"+userCity);
	          output+="UserId is:\t"+userId+" "+"userName is:\t"+userName+" "+"userEmail is:\t"+userEmail+" "+"userCity is:\t"+userCity+"<br>";
	    }
	    catch (Exception ex) {
	    	ex.printStackTrace();
	      return "User not found";
	    }
	    return output;
	  }
 
  /**
   * /get-by-email  --> Return the id for the user having the passed email.
   * 
   * @param email The email to search in the database.
   * @return The user id or a message error if the user is not found.
   */
  @RequestMapping("/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    String userId;
    int i=0;
    System.out.println(i);
    try {
      User user = userDao.findByEmail(email);
      userId = String.valueOf(user.getId());
      System.out.println("UserId is:\t"+userId);
    }
    catch (Exception ex) {
      return "User not found";
    }
    return "The user id is: " + userId;
  }
 
  @RequestMapping("/get-by-name")
  @ResponseBody
  public String getByName(String name) {
    String userId;
    int i=0;
    System.out.println(i);
    try {
      User user = userDao.findByName(name);
      userId = String.valueOf(user.getId());
      System.out.println("UserId is:\t"+userId);
    }
    catch (Exception ex) {
      return "User not found";
    }
    return "The user id is: " + userId;
  }
  
  @RequestMapping("/get-by-city")
  @ResponseBody
  public String getByCity(String city) {
    String userId;
    String userName;
    String userEmail;
    String userCity;
    String output="";
    try {
      
      List<User> list =userDao.findByCity(city);
      for(int i=0;i<list.size();i++){
    	  User user = list.get(i);
    	  userId = String.valueOf(user.getId());
    	  userName=String.valueOf(user.getName());
    	  userEmail=String.valueOf(user.getEmail());
    	  userCity=String.valueOf(user.getCity());
          System.out.println("UserId is:\t"+userId);
          System.out.println("Name is:\t"+userName);
          System.out.println("Email is:\t"+userEmail);
          System.out.println("City is:\t"+city);
          output+="UserId is:\t"+userId+" "+"userName is:\t"+userName+" "+"userEmail is:\t"+userEmail+" "+"userCity is:\t"+userCity+"<br>";
      }
    }
    catch (Exception ex) {
    	ex.printStackTrace();
      return "User not found";
    }
    return output;
  }
  
  /**
   * /update  --> Update the email and the name for the user in the database 
   * having the passed id.
   * 
   * @param id The id for the user to update.
   * @param email The new email.
   * @param name The new name.
   * @param city The new city.
   * @return A string describing if the user is succesfully updated or not.
   */
  @RequestMapping("/update")
  @ResponseBody
  public String updateUser(long id, String email, String name,String city) {
    try {
      User user = userDao.findOne(id);
      user.setEmail(email);
      user.setName(name);
      user.setCity(city);
      userDao.save(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  }

  @RequestMapping("/update-by-city")
  @ResponseBody
  public String updateByCityUser(long id, String city) {
	    String userId;
	    String userName;
	    String userEmail;
	    String userCity;
	    String output="";
    try {
      User user = userDao.findOne(id);
      user.setCity(city);
      userDao.save(user);
      userId = String.valueOf(user.getId());
	  userName=String.valueOf(user.getName());
	  userEmail=String.valueOf(user.getEmail());
	  userCity=String.valueOf(user.getCity());
      System.out.println("UserId is:\t"+userId);
      System.out.println("Name is:\t"+userName);
      System.out.println("Email is:\t"+userEmail);
      System.out.println("City is:\t"+city);
      output = "User succesfully updated!";
      output+="UserId is:\t"+userId+"userName is:\t"+userName+"userEmail is:\t"+userEmail+"userCity is:\t"+userCity+"<br>";
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return output;
  }
  

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------

  @Autowired
  private UserDao userDao;
  
} // class UserController
