/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cyberchatapp;

import java.io.IOException;

/**
 *
 * @author Student
 */
public class MessagesTest {

public static void main(String[] args) throws IOException {

Messages messages = new Messages();

// Create test arrays
messages.messageIDs = new String[5];
messages.recipients = new String[5];
messages.messageTexts = new String[5];
messages.messageHashes = new String[5];
messages.storedMessages = new String[5];

// Test messages from the example
messages.messageIDs[0] = "1234567890";
messages.recipients[0] = "+27834557896";
messages.messageTexts[0] = "Did you get the cake?";
messages.messageFlags[0] = "Sent";

messages.messageIDs[1] = "1234567891";
messages.recipients[1] = "+27838884567";
messages.messageTexts[1] =
"Where are you? You are late! I have asked you to be on time.";
messages.messageFlags[1] = "Stored";

messages.messageIDs[2] = "1234567892";
messages.recipients[2] = "+27834484567";
messages.messageTexts[2] = "Yohoooo, I am at your gate.";
messages.messageFlags[2] = "Disregard";

messages.messageIDs[3] = "08388284567";
messages.recipients[3] = "08388284567";
messages.messageTexts[3] = "It is dinner time!";
messages.messageFlags[3] = "Sent";

messages.messageIDs[4] = "1234567894";
messages.recipients[4] = "+27838884567";
messages.messageTexts[4] = "Ok, I am leaving without you.";
messages.messageFlags[4] = "Stored";

// Create message hashes
for (int i = 0; i < 5; i++) {
messages.messageHashes[i] =
messages.createMessageHash(
messages.messageIDs[i], i, messages.messageTexts[i]);
}

messages.totalMessages = 2;
messages.storedCount = 2;

// Sent messages
System.out.println("Sent Message");
System.out.println(messages.messageTexts[0]);
System.out.println(messages.messageTexts[3]);

// Longest message
System.out.println("Longest Message");
System.out.println(messages.longestMessage());

// Search by Message ID
System.out.println("Search Message ID");
messages.searchMessageID("08388284567");

// Search by recipient
System.out.println("Search Recipient");
messages.searchAllMessages("+27838884567");

// Delete using message hash
System.out.println("Delete Message");
System.out.println(messages.deleteMessage(messages.messageHashes[1]));

// Message report
System.out.println("Message Report");
messages.displayReport();


}

}
