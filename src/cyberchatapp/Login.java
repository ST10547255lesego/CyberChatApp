/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cyberchatapp;

    
    

public class Login {
    public boolean checkUsername(String username){
        return username.contains("_") && username.length()<=5;
        }
    //Checks if password, Cellphone number and Login user information
    public boolean checkPasswordComplexity(String password){
        boolean length = password.length() >=8;
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecial = password.chars().anyMatch(c -> !Character.isLetterOrDigit(c));
        
        return length && hasUpper && hasDigit && hasSpecial;
    }
    public boolean checkCellPhoneNumber(String phoneNumber){
        return phoneNumber.matches("^\\+27[0-9]{9}$");
    }
    public boolean loginUser(String username, String password, String registeredUsername, String registeredPassword){
        
        return username.equals(registeredUsername)
                && password.equals(registeredPassword);
    }




    
}
