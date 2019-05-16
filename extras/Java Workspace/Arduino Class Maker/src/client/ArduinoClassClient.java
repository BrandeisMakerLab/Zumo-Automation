package client;
import java.util.Scanner;

import enums.ArduinoClassExample;
import enums.ArduinoClassPrompts;

/**Name: Jacob Smith 
 * Date 5/14/2049 Personal Study, uses ArduinoClasses to autaomtically generate header, class, and keyword files
 * Bugs: array position hardcoded
 * Sources: Multline input scheme all code from https://stackoverflow.com/questions/42429134/reading-multiple-lines-into-a-scanner-object-in-java*/
public class ArduinoClassClient {
	public static final String consoleToken="!";
	
	public static void main (String[]args) {
				//This example generates a class represented as a string
				//The user can decide how these string will be inputted
				//These fields are the minimum required to generate an arduino class
				
				//use any of examples
				//simplestExample();
				scannerExample();
				
	}
	
	/**
	 * Creates an arduino cpp, header, and keyword file strings with no input or output
	 * */
	public static void simplestExample(){
	
		//call helper method to display the generated files
		ArduinoClassContainer cont=new ArduinoClassContainer(ArduinoClassExample.CLASSNAME.toString(),
				ArduinoClassExample.AUTHOR.toString(),
				ArduinoClassExample.ORGANIZATION.toString(),
				true,
				ArduinoClassExample.HEADERCOMMENTS.toString(),
				ArduinoClassExample.SUPPORTEDBOARDS.toString(),
				ArduinoClassExample.VARIABLES.toString(),
				ArduinoClassExample.PRIVATEMETHODS.toString(),
				ArduinoClassExample.PUBLICMETHODS.toString());
		System.out.println(cont);
	}
	
	/**
	 * Creates an arduino cpp, header, and keyword file strings using Scanner as input
	 * @return the created ArduinoClassContainer for testing purposes
	 * */
	public static ArduinoClassContainer scannerExample() {
		
		//initalize a new scanner to read keyboard input
		Scanner reader=new Scanner(System.in);
		//generate an array of field names
		ArduinoClassPrompts []fields=ArduinoClassPrompts.values();
		ArduinoClassExample[]examples=ArduinoClassExample.values();
		//create an array of the same length to hold the user's answers
		String []userAnswers=new String[fields.length];
		//temporary variable for code readability
		ArduinoClassPrompts field;
		ArduinoClassExample example;
		String responce;
		System.out.println("Welcome to Arduino Class Genrator by Brandeis Automation Labortory");
		System.out.println("Please enter information from each prompt and end with "+consoleToken);
		//iterate through all the fields, displaying prompt, example formatting, and reading user input
		for(int i=0;i<fields.length;i++){
			field=fields[i];
			example=examples[i];
			//display prompt
			System.out.println(field.prompt+"\n"+example.toString()+consoleToken);
			//read user input
			responce=readUserResponce(reader,consoleToken);
			userAnswers[i]=processUserResponce(responce);
			
		}
		for(int i=0;i<userAnswers.length;i++){
			System.out.println("Index:"+i+" ["+userAnswers[i]+"]");
		}
		
		ArduinoClassContainer cont=null;
		try{
			//call helper method to display the generated files, array positions hardcoded
			cont=new ArduinoClassContainer(userAnswers[0], userAnswers[1], userAnswers[2], true,userAnswers[3],userAnswers[4], userAnswers[5], userAnswers[6], userAnswers[7]);
			System.out.println(cont);
		}catch (Exception e){
			System.out.println("Sorry, there was a formatting error in your input, couldn't make class");
			System.out.println("Would you like to view the error? Y/N");
			String answer=reader.next().toLowerCase();
			//if user doens't enter y or n, display error prompt
			while(!(answer.equals("y") | answer.equals("n"))){
				System.out.print("Please enter Y/N:");
				answer=reader.next().toLowerCase();
			}
			if(answer.equals("y")){
				e.printStackTrace();
				sleepNoError(500);
			}
			
		}
		System.out.println("Thank you, closing down now");
		reader.close();
		return cont;
	
	}
	/**
	 * process user responce, converting data types such
	 * as "null" to null
	 */
	private static String processUserResponce(String responce){
		if(responce.toLowerCase().equals("null")){
			return null;
		}else{
			return responce;
		}
	}
	
	/**
	 * reads and assembles responces from the user that can consist fo many lines
	 */
	private static String readUserResponce(Scanner reader,String token){
		String line="dummy";
		String input="";
		boolean tokenFound=false;
		//keep reading user input until the token is found
		while(!tokenFound){
			//consume next line of input
			line=reader.nextLine();
			//see if token was found
			if(line.contains(token)){
				//set flag to true
				tokenFound=true;
				//remove all instances of the token
				line=line.replaceAll(token, "");
				//add the line without a newline character at the end
				input+=line;
			}else{
				//add the token free input to string
				input+=line+"\n";
			}
			
			
		}
		//return the total string of input
		return input;
	}
	/**
	 * Helper method to tell computer to sleep without needing throws declaration
	 */
	private static void sleepNoError(int sleepTime){
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e1) {
			System.out.println("Error with sleep statement");
		}
		
	}

}