import java.io.*;
//import java.util.*;

public class TextGenerator {
    public static void main(String[] args) {
	int K = Integer.parseInt(args[0]);
	int M = Integer.parseInt(args[1]);
	String fName = args[2];
	String start = "";
	
	MarkovModel mark = new MarkovModel(K, fName);
	try {
	    FileReader reader = new FileReader(new File(args[2]));
	    for (int i=0;i<K;i++) {
		start += (char) reader.read();
	    }
	    //System.out.println(start);
	    reader.close();
	    
	    System.out.println(mark.generateText(M, start));
	} 
	catch (FileNotFoundException e) {System.out.println( "Bad file name" );}
	catch (IOException e) { System.out.println( "IOException" ); }
    }
}
