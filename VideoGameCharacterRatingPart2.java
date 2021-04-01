// Names: Jeffery Hu and David Geng
// Teacher name: Ms. Krasteva
// File name: VideoGameCharacterRatingPart2.java
// Date: February 25, 2021.
// Description: This class will collect the statistics (name, strength, health speed, and wisdom) for video game characters from a file. It will then be able to display this data and show the overall best character.
// Citation: The syntax for how to get input using JOptionPane was learned from: https://www.homeandlearn.co.uk/java/java_option_panes.html

//Import statements
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class VideoGameCharacterRatingPart2{
   // Variable declarations
   String[][] characters; // Declare a String 2D-array called characters, which will store all the attributes of all the characters
   String menuInput = ""; // Declare a String called menuInput, which will store what the user inputs in the menu
   boolean back = false; // Declare a boolean called back, which will store if the user has already seen the main menu. If so, it will display a different message at the top ("You are now back at the main menu").
   
   //Class constructor
   public VideoGameCharacterRatingPart2(){
   }
   
   /*
   The getArraySize() method, which will go through the file the first time to determine how many values/lines it contains.
   Loops:
   1. The while loop is meant to go through each line in the file until there aren't any left.
   */
   public void getArraySize() throws IOException {
      Scanner file = new Scanner(new File("CharacterRatings.txt")); // Declare and initialize a scanner called file, which will be used to scan the file the first time to determine the number of lines
      int i = 0; // Declare an integer called i, assign it the value of 0 (to track the amount of values/lines in the file)
      String fileInput = ""; // Declare and initialize a String called fileInput, which will serve as a storing variable for the first scanner as it determines the number of lines
      
      while (file.hasNext()) // Keep going until it reaches the end of the file
      {
         fileInput = file.nextLine();
         i++;
      }
      characters = new String[i/5][5]; // Initialize the characers 2D-array to the appropriate size (depending on the length of the file).
   }
   
   /*
   The readFile() method, which will read the attributes and characters from the given file and store them into the arrays.
   Loops:
   1. The nested for loops are used to cycle through the rows (outer) and columns (inner) of the characters array. 
      a) First, the specific value inside the file is read and stored in the proper place in the 2D array (each row is a new character)
      b) The first while loop inside of the for loops is used to make sure that the name for each character is a valid input
      c) The second while loop and try-catch statements is used to make sure that the values of strength, health, speed, and wisdom are valid
      *If an invalid input is encountered either in the file of through user input, a JOptionPane will pop up and ask the user to enter another value to replace it.
   */
   public void readFile() throws IOException {
      Scanner fileTwo = new Scanner(new File("CharacterRatings.txt")); // Declare and initialize a scanner called fileTwo, which will be used to scan the file the second time to extract/copy the values into the array.
      int testIsInt = 0; // Declare and initalize an integer called testIsInt, which will be used to see if the values that are suppose to be integers in the file can be converted into it.
      
      for (int w = 0; w < characters.length; w++) // Cycle through the rows in characters
      {
         for (int t = 0; t < characters[0].length; t++){ // Cycle through the columns in characters
            characters[w][t] = fileTwo.nextLine(); // Reads the next line and stores the String value in the proper location
            while (t == 0 && (characters[w][t].equals("") || characters[w][t].equals(" "))){ // Keep asking for a valid character name
               characters[w][t] = JOptionPane.showInputDialog ("\"" + characters[w][t] + "\"" + " is not a valid name. Please enter a valid name for the character (line " + (w*5+1) + "):");
               if (characters[w][t] == null){ // If the user clicks the "cancel" button
                  characters[w][t] = "No Name";
               }
            }
            if (t > 0){ // For the character's strength, health, speed, and wisdom attibutes
               while (true){ // Keep prompting for a valid input
                  try{
                     testIsInt = Integer.parseInt(characters[w][t]); // See if the input from the file is an integer
                     if (testIsInt < 0 || testIsInt > 100){ // see if the input from the file is within the specified range
                        throw new IllegalArgumentException ();
                     }
                     break;
                  } catch(NumberFormatException e){ // Catch if the input from the file is not an integer
                     characters[w][t] = JOptionPane.showInputDialog ("\"" + characters[w][t] + "\"" + " is not a valid input for "+ characters[w][0] + "'s strength. Please enter an integer from 0 to 100:"); // error message
                  } catch(IllegalArgumentException E){ // Catch if the input from the file is not in the specific range
                     //Display personalized error messages telling the user specifically which value is not valid
                     if (t == 1){
                        characters[w][t] = JOptionPane.showInputDialog ("\"" + characters[w][t] + "\"" + " is not a valid input for "+ characters[w][0] + "'s strength. Please enter a valid rating from 0 to 100:");
                     }
                     else if (t == 2){
                        characters[w][t] = JOptionPane.showInputDialog ("\"" + characters[w][t] + "\"" + " is not a valid input for "+ characters[w][0] + "'s health. Please enter a valid rating from 0 to 100:");
                     }
                     else if (t == 3){
                        characters[w][t] = JOptionPane.showInputDialog ("\"" + characters[w][t] + "\"" + " is not a valid input for "+ characters[w][0] + "'s speed. Please enter a valid rating from 0 to 100:");
                     }
                     else if (t == 4){
                        characters[w][t] = JOptionPane.showInputDialog ("\"" + characters[w][t] + "\"" + " is not a valid input for "+ characters[w][0] + "'s wisdom. Please enter a valid rating from 0 to 100:");
                     }
                  } catch (NullPointerException e){ // Catch if the user presses the "cancel" button
                     if (t == 1){
                        characters[w][t] = JOptionPane.showInputDialog ("Error: Please enter an integer from 0 to 100 for " + characters[w][0] + "'s strength:");
                     }
                     else if (t == 2){
                        characters[w][t] = JOptionPane.showInputDialog ("Error: Please enter an integer from 0 to 100 for " + characters[w][0] + "'s health:");
                     }
                     else if (t == 3){
                        characters[w][t] = JOptionPane.showInputDialog ("Error: Please enter an integer from 0 to 100 for " + characters[w][0] + "'s speed:");
                     }
                     else if (t == 4){
                        characters[w][t] = JOptionPane.showInputDialog ("Error: Please enter an integer from 0 to 100 for " + characters[w][0] + "'s wisdom:");
                     }
                  }
               }
            }
         }
      }
   }
   
   /*
   The mainMenu() method, which will display the description of the program along with the menu options.
   If-else statement:
   1. The first if-else statement is used to see if this is the user's first time at the main menu. If it is, it will display a welcome message along
      with a description of the program. If not, it will display "You are now back at the main menu".
   While loop:
   1. The while loop with the try catch statement inside is used to make sure that the user enters a valid input from the menu options (either 1, 2, or 3).
   */
   public void mainMenu(){
      Scanner input = new Scanner (System.in); // Declare and initialize a new Scanner to take user input
      
      if (back == true){
         System.out.println("You are now back at the main menu."); // 'Welcome back to the main menu' message
      }
      else{
         System.out.println("Character Rating Program!"); // Description of the program
         System.out.println("This program will allow you to view all of the attributes associated with each video game character (as provided by the file).\nThese attributes inlcude their name, strength, health, speed, and wisdom. Additionally, you can also view the strongest character overall.");
         System.out.println("Each attribute will be ranked on a scale of 0 to 100, with 0 being very poor and 100 being very strong in that attribute.");
         back = true;
      }
      System.out.println("Press 1 if you want to see the all the information for all the characters."); // Menu options
      System.out.println("Press 2 if you want to see the best overall character (this is calculated by using the following formula: Strength x 35 + Health x 30 + Speed x 25 + Wisdom x 10).");
      System.out.println("Press 3 to exit the program.");
      menuInput = input.nextLine(); // Collects user input for the menu options
      while (true){ // error traps the input for the menu options
         try {
            if (menuInput.equals("1")||menuInput.equals("2")||menuInput.equals("3")){ // If the input is valid
               break;
            }
            else{
               throw new IllegalArgumentException ();
            }
         } catch(NullPointerException e){ // Catches if they try to press 'cancel'
            menuInput = JOptionPane.showInputDialog ("Error! Please enter either 1, 2, or 3.");
         } catch(IllegalArgumentException e){ // Catches if they don't enter either 1, 2, or 3
            menuInput = JOptionPane.showInputDialog ("Error! Please enter either 1, 2, or 3.");
         }
         
      }
   }
   
   /*
   The display() method, which will display the either all the characters and their attributes or the top overall player.
   If-else statement:
   1. The if-else statement is used to see if the user wants to see all the characters and their attributes or just the top overall player
   */
   public void display(){
      int score = 0; // Declare an integer named score and assign it the value of 0 (this will store the score of each character as they for loop cycles through them)
      int highestScore = 0; // Declare an integer named highestScore and assign it the value of 0 (this will store the highest score found)
      int highestPlayerIndex = 0; // Declare an integer named highestPlayerIndex and assign it the value of 0 (this will store the index of the character with the highest score)
      int strength; // Declare an integer named strength, which will store the value of each character's strength as they are cycled through
      int health; // Declare an integer named health, which will store the value of each character's health as they are cycled through
      int speed; // Declare an integer named speed, which will store the value of each character's speed as they are cycled through
      int wisdom; // Declare an integer named wisdom, which will store the value of each character's wisdom as they are cycled through
      
      if (menuInput.equals("1")){ // If the user wants to see all the characters and their attributes
         for (int w = 0; w < characters.length; w++){ // Cycles through each character (each row of the 2D array)
            System.out.println("Character " + (w+1) + ":");
            for (int d = 0; d < 5; d++){ // Cycles through each attribute (each column of the 2D array)
               if (d == 0){
                  System.out.println("Name: " + characters[w][d]); // Print each attribute
               } else if (d == 1){
                  System.out.println("Strength: " + characters[w][d]);
               } else if (d == 2){
                  System.out.println("Health: " + characters[w][d]);
               } else if (d == 3){
                  System.out.println("Speed: " + characters[w][d]);
               } else {
                  System.out.println("Wisdom: " + characters[w][d]);
                  System.out.println();
               }
            }
         }
      }
      else if (menuInput.equals("2")){ // If the user wants to see the top overall player
         for (int j = 0; j < characters.length-1; j++){ // Cycles through each character
            strength = Integer.parseInt(characters[j][1]); // Store the values of each attribute in their corresponding attribute variable
            health = Integer.parseInt(characters[j][2]);
            speed = Integer.parseInt(characters[j][3]);
            wisdom = Integer.parseInt(characters[j][4]);
            score = strength * 35 + health * 30 + speed * 25 + wisdom * 10; // Using the attribute variables, calculate the character's score
            if (score > highestScore){ // If their score is the highest
               highestScore = score; // Store their score
               highestPlayerIndex = j; // Store the index of the row they are at
            }
         }
         System.out.println(characters[highestPlayerIndex][0] + " is the greatest overall character with a score of " + highestScore + "!");
         System.out.println("Here are their statistics:"); // Prints their stats
         System.out.println("Strength: " + characters[highestPlayerIndex][1]);
         System.out.println("Health: " + characters[highestPlayerIndex][2]);
         System.out.println("Speed: " + characters[highestPlayerIndex][3]);
         System.out.println("Wisdom: " + characters[highestPlayerIndex][4]);
         System.out.println();
      }
   }
   
   /*
   The goodbye() method, which prints a farewell message.
   */
   public void goodbye(){
      System.out.println("Thank you for using this program designed by Jeffery Hu and David Geng. See you next time!");
   }
   
   /*
   The main method, which controls the flow of the program.
   */
   public static void main(String[] args) throws IOException {
      VideoGameCharacterRatingPart2 v = new VideoGameCharacterRatingPart2(); // Creates an object of the class
      v.getArraySize();
      v.readFile(); // Read from file
      while(!v.menuInput.equals("3")){ // As long as the user hasn't inputted option 3 at the main menu, keep running the mainMenu and display methods
         v.mainMenu();
         v.display();
      }
      v.goodbye(); // Display farewell message
   }
      
}