/* Name: Jacob Smith
Date: May 13 2019
Assignment: Personal Study, allows the user to create a scanner object which iterates
over a base string and returns one token at a time
Email: jsmith2021@brandeis.edu
*/

public class miniScanner{
	
	
	private boolean primed;
	private String base;
	private String token;
	private int curIndex;


	private String nextString;
	
	/*instantiates a miniScanner object*/
	public miniScanner(){
		initClass();
	}
	
	/* Helper method to initialize or reset all of the classes instance variables
	*/
	public void initClass(){
		primed=false;
		base=null;
		token=null;
		curIndex=0;
		nextString=null;
	}
	
	/**
	* Shows an example file with this class
	*/
	public static void main (String[] args){
		//this example iterates over a base stirng with spaces used as a token
		miniScanner reader=new miniScanner();
		
		System.out.println("test prime with base string separated by spaces");
		reader.prime("This is a test"," ");
		while(reader.hasNext()){
			System.out.println(reader.next());
		}
		
		System.out.println("test prime with base string separated by colons");
		reader.prime("This:is:also:a:test",":");
		while(reader.hasNext()){
			System.out.println(reader.next());
		}
		
	}
		
    /*Gets a miniScanner reader to iterate over the miniScanner*/
	public void prime(String base, String token){
	
		this.base=base;
		this.token=token;
		curIndex=0;
		primed=true;
	
	}
	
	/*returns whether the scanner is reader to iterate over
		updates the nextString field*/
	public boolean hasNext(){
		checkPrimed();
		//return whether there is a next instance of the token
		int nextIndex=base.indexOf(token,curIndex);
		boolean  hasNextToken=nextIndex!=-1;
		
		//if there is a next token, update nextString field return true
		if (hasNextToken){
			nextString=base.substring(curIndex,nextIndex);
			curIndex=nextIndex+token.length();
			return true;
		}
		//otherwise, see if you are at the last index
		//and if you are update nextString with the rets of the string
		if(curIndex<base.length()){
			nextString=base.substring(curIndex,base.length());
			curIndex=base.length();
			return true;
		}
		//if there is no next String, reset the class
		initClass();
		return false;
		
	}
	
	/*returns the next string, which will be null if there is no next string*/
	public String next(){
		checkPrimed();
		String temp=nextString;
		nextString=null;
		return temp;
		
	}
	
	/*throws an exception if the user hasn't primed the miniScanner*/
	private void checkPrimed(){
		if(!primed){
			throw new IllegalArgumentException("Error, Scanner not primed");
		}
	}
	

	
}