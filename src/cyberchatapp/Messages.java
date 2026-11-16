/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cyberchatapp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.util.Objects.hash;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Messages {
    
    Scanner input = new Scanner(System.in);
    Random random = new Random();
    
    String[] messageIDs;
    String[] recipients;
    String[] messageTexts;
    String[] messageHashes;
    String[] storedMessages;
    String[] messageFlags;
    
    int totalMessages = 0;
    int storedCount = 0;
    int messagesEntered = 0;
    int messageLimit = 0;
    
    //Display the QuickChat menu
    public void displayM() throws IOException{
        System.out.println("Welcome to QuickChat.");
        
        System.out.print("How many messages do you want to enter? ");
        messageLimit = input.nextInt();
        input.nextLine();
        
        if (messageLimit < 0){
            System.out.println("Invaild number of messages. ");
            return;
        }
        
        messageIDs = new String[messageLimit];
        recipients = new String[messageLimit];
        messageTexts = new String[messageLimit];
        messageHashes = new String[messageLimit];
        storedMessages = new String[messageLimit];
        messageFlags = new String[messageLimit];
        
        int opt;
        
        do{
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.println("4. Stored Messages");
            System.out.println("Choose an option: ");
            
            opt = input.nextInt();
            input.nextLine();
            
            if (opt == 1){
                
                if (messagesEntered < messageLimit){
                   
                     for (int i = messagesEntered; i < messageLimit; i++){ SentMessage();}
                
                } else {
                    System.out.println("Message limit reached.");
                }
            }else if (opt == 2){
                    System.out.println("Coming Soon.");
                        
            } else if (opt == 3){ 
                 System.out.println("Quit");
                    
            }else if (opt == 4) {
                System.out.println("Stored Messages");
                System.out.println("1. Display sender and recipient");
                System.out.println("2. Display longest message");
                System.out.println("3. Search recipient");
                System.out.println("4. Search recipient");
                System.out.println("5. Delete message using hash");
                System.out.println("Display full report");
                System.out.print("Choose an option: ");
                
                int reportChoice = input.nextInt();
                input.nextLine();
                
                if (reportChoice == 1){
                    readStoredMessages();
                }
                else if (reportChoice == 2) {
                    System.out.println("Longest message: " + longestMessage());
                }
                 else if (reportChoice == 3) {
                    System.out.println("Enter message ID: ");
                 }
                  else if (reportChoice == 4) {
                    System.out.println("Enter recipient number: ");
                    String recipient = input.nextLine();
                    searchAllMessages(recipient);
                  
                  else if (reportChoice == 5) {
                    System.out.print("Enter message hash: ");
                    String hash = input.nextLine();
                   }
                   if (deleteMessage(hash)){
                       System.out.println("Message successfully deleted.");
                   }
                   else {
                       System.out.println("Message not found. ");
                   }
                }else if (reportChoice == 6) {
                    displayReport();
                }
                else {
                    System.out.println("This option is not added yet.");
                }
                
            } 
            else{
                System.out.println("Invaild input.");
                
            }
        } while (opt != 3);
            System.out.println("Total messges sent: " + returnTotalMessages());
    }
    
    //Check the message ID
    public boolean checkMessageID(String messageID){
        return messageID.length() == 10;
    }
    
    //check the recipient cellphone number
    public boolean checkRecipientCell(String cellNumber){
        return cellNumber.matches("\\+27[0-9]{9}");
    }
    
    //Check the message hash
    public String createMessageHash(String messageID, int messageNumber, String message){
        String[] words = message.trim().split("\\s+");
        
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        String hash = messageID.substring(0, 2)+":"+ messageNumber + ":"+ firstWord + lastWord;
        
        return hash.toUpperCase();
    }
     // Send store or disregard a message
    public String SentMessage() throws IOException{
        messagesEntered++;
        
        String messageID = String.valueOf(1000000000 + random.nextInt(900000000));
        
        System.out.println("Message ID: " + messageID);
        
        if (!checkMessageID(messageID)){
            System.out.println("Invalid message ID.");
            return "Invalid message ID"; 
        }
        
        System.out.print("Enter recipient number (+27....):");
        String recipient = input.nextLine();
        
        if (!checkRecipientCell(recipient)){
            System.out.println("Invaild cellphone number. ");
            return "Invaild cellphone number entered";  
        }
        
        System.out.print("enter your message");
        String message = input.nextLine();
        
        if (message.length()>= 250 || message.trim().isEmpty()){
            System.out.println("Please enter a message of less than 250 characters.");
            return "Invaild message";
        }
        
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message to send later");
        System.out.print("Choose an option: ");
        
        int choice = input.nextInt();
        input.nextLine();
        
        if (choice == 1) {
            messageIDs[totalMessages] = messageID;
            recipients[totalMessages] = recipient;
            messageTexts[totalMessages] = message;
            messageHashes[totalMessages] = createMessageHash(messageID, totalMessages, message);
            messageFlags[totalMessages] = "Sent";
            
            System.out.println("Message successfully sent");
            System.out.println("Message ID:"+ messageIDs[totalMessages]);
            System.out.println("Message Hash: " + messageHashes[totalMessages]);
            System.out.println("Recipients: " + recipients[totalMessages]);
            System.out.println("Message: " + messageTexts[totalMessages]);
            
            totalMessages++;
            return "Message sent";
        }
        else if (choice == 2){
            
            messageIDs[totalMessages] = messageID;
            recipients[totalMessages] = recipient;
            messageTexts[totalMessages] = message;
            messageHashes[totalMessages] = createMessageHash(messageID, totalMessages, message);
            messageFlags[totalMessages] = "Disregarded";
            totalMessages++;
            System.out.println("message disregarded. ");
            return "Message disregarded";
        }
        else if (choice == 3){
            
            messageIDs[totalMessages] = messageID;
            recipients[totalMessages] = recipient;
            messageTexts[totalMessages] = message;
            messageHashes[totalMessages] = createMessageHash(messageID, totalMessages, message);
            messageFlags[totalMessages] = "Stored";
            storedMessages[storedCount] = "{\"messageID\":\""+ messageID + "\",\"recipient\":\""+ recipient +
                    "\",\"message\":\""+ message.replace("\\", "\\\\").replace("\"", "\\\"")+"\"}";
            totalMessages++;
            storeMessage();
            storedCount++;
            storeMessage();
            System.out.println("Message successfully stored");
            return "Message stored";
        }
        else{
            System.out.println("Invaild option.");
            return "Invaild option";
        }
        
         
    }
    //Display allsent messages
    public String printMessages(){
        String result = "";
        
        for (int i = 0; i < totalMessages; i++){
            result += "Message ID: " + messageIDs[i]+"\n";
            result += "Message Hash: " + messageHashes[i]+"\n";
            result += "Recipient: " + recipients[i]+"\n";
            result += "Message: " + messageTexts[i]+"\n";
        }
        return result;
    } 
    //Return the total number of messages sent
    public int returnTotalMessages(){
        return totalMessages;
    }
    
    //Store messages in a JSON file without try/catch
    public void storeMessage() throws IOException{
        
        FileWriter writer = new FileWriter("messages.json");
        writer.write("[\n");
        boolean first = true;
        
        for (int i = 0; i < totalMessages; i++){
            if ("Stored".equals(messageFlags[i])){
                if (!first) {
                    writer.write(",\n");
                }
            }
            writer.write("{\"messageID\":\"" + messageIDs[i]+"\",\"messageHash\":\""
                    + messageHashes[i] + "\",\"recipient\":\"" + recipients[i] + "\",\"message\":\"" + 
                    messageTexts[i].replace("\\", "\\\\").replace("\"", "\\\"") +
                    "\",\"flag\":\"" + messageFlags[i] + "\"}");
            first = false;
            
            if (i < storedCount - 1){
                writer.write(",");
            }
            writer.write("\n");
        }
        writer.write("]");
        writer.close();
    }
    public String longestMessage() {
        String longest = "";
        
        for (int i = 0; i < totalMessages; i++){
            if ("Stored".equals(messageFlags[i])){
            }
            if (messageTexts[i].length()>longest.length()){
                longest = messageTexts[i];
            }
            if (longest.isEmpty()){
                return "No Stored messages found.";
            }
        }
        return longest;
        
    }
    public void searchMessageID(String id){
        for (int i = 0; i < totalMessages; i++){
            
            if ("Stored".equals(messageFlags[i]) && messageIDs[i].equals(id)) {
                System.out.println("Recipent: "+ recipients[i]);
                System.out.println("Message: " + messageTexts[i]);
                return;
            }
        }
        System.out.println("Messages not found.");
    }
    
    public void searchAllMessages(String recipient){
        boolean found = false;
        
        for (int i = 0; i < totalMessages; i++) {
            if ("Stored".equals(messageFlags[i]) && recipients[i].equals(recipients)) {
                System.out.println("Message ID: " + messageTexts[i]);
                 System.out.println("Message:  " + messageTexts[i]);
                 
                 found = true;
            }
        }
        if (!found){
            System.out.println("No messages found for this recipient.");
        }
    }
    
    public boolean deleteMessage(String hash) throws IOException {
        for (int i = 0; i < totalMessages; i++){
            if("Stored".equals(messageFlags[i]) && messageHashes[i].equals(hash))
                
                for (int j = i; j < totalMessages - 1; j++) {
                    messageIDs[j] = messageIDs[j + 1];
                    recipients[j] = recipients[j + 1];
                    messageTexts[j] = messageTexts[j + 1];
                    messageHashes[j] = messageHashes[j + 1];
                    messageFlags[j] = messageFlags[j + 1];
                }
                totalMessages--;
                storeMessage();
                return true;
            }
        
        return false;
    }
    
    public void displayReport() {
        boolean found = false;
        
        if (totalMessages == 0){
            System.out.println("No messages available for the report. ");
            return;
        }
        System.out.println("Full Message Report");
        System.out.println("-----------------------");
        for (int i = 0; i < totalMessages; i++) {
            System.out.println("Message ID: " + messageIDs[i]);
            System.out.println("Message Hash: " + messageHashes[i]);
            System.out.println("Recipient: " + recipients[i]);
            System.out.println("Message: " + messageTexts[i]);
            System.out.println("Flag: " + messageFlags[i]);
            System.out.println("------------------------");
            
            found = true;
        }
        if (!found) {
            System.out.println("No stored messages available");
    }
    }   
   public void readStoredMessages() throws IOException {
       File file = new File ("messages.json");
       
       if (!file.exists()) {
           System.out.println("No stored messages found.");
           return;
       }
       Scanner reader = new Scanner(file);
       
       while (reader.hasNextLine()){
           System.out.println(reader.nextLine());
       }
       reader.close();
   }
   public void displayStoredSenderRecipient() {
       boolean found = false;
       
       for (int i = 0; i < totalMessages; i++) {
           
           if ("Stored".equals(messageFlags[i])) {
               System.out.println("Recipient: " + recipients[i]);
               System.out.println("-----------------------");
               found = true;
           }
       }
       if (!found) {
           System.out.println("No stored messages found. ");
       }
   }

    
 
    

    
        

   
}
