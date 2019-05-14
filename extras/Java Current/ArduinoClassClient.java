/**Name: Jacob Smith ate 5/14/2049 Personal Study, uses ArduinoClasses to autaomtically generate header, class, and keyword files*/
public class ArduinoClassClient {
	public static void main (String[]args) {
		//This example generates a class represented as a string
				//The user can decide how these string will be inputted
				//These fields are the minimum required to generate an arudino class
				
				//Titles are one word
				String className="Timer";
				//This information is useful in the header comment
				String author="Jacob Smith";
				String organization="Brandeis Robotics Club";
						
					
				String headerComments="A timer class to allow the user to create loops and maintain program control";
					
				String supportedBoards="ARDUINO_AVR_UNO ESP8266_WEMOSD1R1";
				//variables will always be private, I ain't in the business of allowing bad programming
				//format of variables is dataType varName
				String variables="";
				variables+="long|initTime|the beginning time of the interval"+"\n";
				variables+="Apple|test|a test varible for the parser";
						
				//Methods are an array of strings, each string is format
				//returnType methodName comments
				//method bodies are the code that goes in the methods
				String publicMethods="";
					
				publicMethods+="initTime=millis();\n\n";
				publicMethods+="long|resetTime|resets the Initial Time|\n";
				publicMethods+="initTime=millis();\nreturn getTime();\n\n";
				publicMethods+="long|getTime|returns the current time|\n";
				publicMethods+="return millis()-initTime;\n\n";
				publicMethods+="long|getAndResetTime|returns the current time and the initial time|\n";
				publicMethods+="long curTime=getTime();\nresetTime();\nreturn curTime;\n\n";
					
							
				String  privateMethods=null;
				
				ArduinoClassCpp template=new ArduinoClassCpp(className,author,organization,headerComments,supportedBoards,variables,privateMethods,publicMethods);
				System.out.println(template.toString());
				ArduinoClassH template2=new ArduinoClassH(className,author,organization,headerComments,supportedBoards,variables,privateMethods,publicMethods);
				System.out.println(template2.toString());
				System.out.println(template2.getKeywords());
	}
}