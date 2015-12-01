package csc4330.mike.dreamlink.components;

/**
 * This class was for future use to deal with inheritance of ParseUsers and to be able to create them locally.
 */
public class Contact {

   private String  username;
   private  String password;
   private  String userEmail;

    public Contact(){

    }

    public Contact(String uName, String pWord, String uEmail){

        username = uName;
        password = pWord;
        userEmail = uEmail;

    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
    }
    public String getUserPassword() {
        return password;
    }

    public void setUserPassword(String userPass) {
        this.password= userPass;
    }



}
