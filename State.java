import java.util.*;


public class State {
    private TreeMap<Character, Integer> suffixes;
    private String str;
    private Integer count;
    private Random rand;
    
    public State(String string) {
	this.count = 0;	//Should the count be initially set to 1, since it will only be called if the state exists?
	this.suffixes = new TreeMap<Character, Integer>();
	this.str = string;
	this.rand = new Random();
    }
    
    public void add() {
	count++;
	//System.out.println("Count: "+count);
    }
    public void add(char c) {
	if (suffixes.containsKey(c))
	    suffixes.put(c, suffixes.get(c)+1);
	else
	    suffixes.put(c,1);
	add();	//count++ <<< idek why we can't just do this instead of having a separate add method just to increase the count???
    }
    
    public Character generate() {
	//System.out.println("count: "+count);
	int r = rand.nextInt(count);
	//System.out.println("r init: "+r);
	for (Character c: suffixes.keySet()) {
	   r -= suffixes.get(c);
	   //System.out.println("r: "+r);
	   if (r<0)
	       return c;
	}
	return (char) -1;
    }
    
    public String toString() {
        String s = String.format("%d %s:", count, str);
        for (Character ch : suffixes.keySet() )
              s += String.format(" (%c %d) ", ch, suffixes.get(ch));
        return s;
    }
    
}
