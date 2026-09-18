/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cyberchatapp;


public class LoginTest {
    public static void main(String[]args){
        
        Login login = new Login();
        
        System.out.println(login.checkUsername("abc_"));
        System.out.println(login.checkUsername("abcdef"));
                
        System.out.println(login.checkPasswordComplexity("Password1!"));
        System.out.println(login.checkPasswordComplexity("password"));
        
        System.out.println(login.checkCellPhoneNumber("+27821234567"));
        System.out.println(login.loginUser("abc_","Password1!" , "abc_","Password1!"));
    }
    
}
