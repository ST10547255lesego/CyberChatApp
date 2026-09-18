/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cyberchatapp;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Main {
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        
        //User is requied to enter information
        System.out.println("Enter Username: ");
        String username = input.nextLine();
        
        System.out.println("Enter password: ");
        String password = input.nextLine();
        
        System.out.println("Enter cellphone number: ");
        String phoneNumber = input.nextLine();
        
        System.out.println("Enter First Name: ");
        String firstName = input.nextLine();
        
        System.out.println("Enter Last Name: ");
        String lastName = input.nextLine();
        
        Login login = new Login();
        
        //System checks for relivent information and executes the results  
        if(login.checkUsername(username)){
            System.out.println("Username is correct");
        } 
        else{
            System.out.println("Username is incorrect");
        }
        if (login.checkPasswordComplexity(password)){
            System.out.println("Password is correct");
        }
        else{
            System.out.println("Password is incorrect");
        }
        if (login.checkCellPhoneNumber(phoneNumber)){
            System.out.println("Cell phone number sucessfully added. ");
        } 
        else {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code");
        }
        String registeredUsername = "test_";
        String registeredPassword = "Password1!";
        if (login.loginUser(username, password, registeredUsername, registeredPassword)){
            System.out.println("Welcome"+ firstName + " " + lastName + ", it is great to see you again. ");
        }
        else{
            System.out.println("Username or password is incorrect, please try again.");
        }
    }
}

   
