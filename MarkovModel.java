import java.io.*;
import java.util.*;

public class MarkovModel {
    private Integer K;
    private HashMap<String, State> model;
    
    public MarkovModel(Integer K, String fName) {
	this.K = K;
	this.model = new HashMap<String, State>();
	train(fName);
    }
    
    public void train(String fName) {
	try {
	    FileReader R = new FileReader(fName);
	    String s = "";
	    for (int i=0;i<K;i++)
		s += (char) R.read();
	    
	   // System.out.println(s);
	    
	    Boolean done = false;
	    while (!done) {
		int c = R.read();
		if (c==-1)
		    done = true;
		else {
		    //System.out.print(c);
		    if (model.containsKey(s)) {
			State state = model.get(s); //<<The State that is already in model
			//state.add(); 
			state.add((char) c);
		    } else {
			State state = new State(s);
			//state.add();
			state.add((char) c);
			model.put(s, state);
		    }
		    s = s.substring(1) + (char) c;
		}
	    }
	    R.close();
	}
	catch (FileNotFoundException e) {System.out.println( "Bad file name" );}
	catch (IOException e) { System.out.println( "IOException" ); }
    }
    
    public String generateText( int M, String start) {
	String text = start;
	String s = text;
	State state;
	//System.out.println("Start: "+start);
	for (int i=0;i<M;i++) {
	    if (model.containsKey(s))
		state = model.get(s);
	    else
		state = model.get(start);
	    //System.out.println("state: "+state);
	    char c = state.generate();
	    //System.out.println("c: "+c);
	    text += c;
	    s = s.substring(1) + c;
	}
	return text;
    }
    
    public void printModel() {
        //System.out.printf("%d distinct states:\n", model.size());
        for (String s : model.keySet())
              System.out.printf("   %s\n", model.get(s));
    }
    
    
}
