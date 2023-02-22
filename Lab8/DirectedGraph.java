/*
 * I wasn't sure how to denote if the connection was made through the calcWarshall algo,
 * so I decided to print with another indent if the connection between cities wasn't a
 * direct 1 to 1 connection.
 */

public class DirectedGraph {
	
    public DirectedGraph() {
    	super();
    }
    String[][] array = new String[50][51];
    
    void addRelation(String from, String to) {
    	int fromIndex = 0;
        int toIndex = 0;
        int i = 0;
        int j = 0;
        for (i = 0; i < 51; i++) {
      	  if (this.array == null) {
      		  array[0][0] = from;
      		  fromIndex = 0;
      		  break;
      	  }
      	  else if (array[i][0] == null) {
      		  array[i][0] = from;
      		  fromIndex = i;
      		  break;
      	  }
      	  else if (array[i][0].equals(from)) {
      		  fromIndex = i;
      		  break;
      	  }
        }
        for (j = 0; j < 51; j++) {
      	  if (array[j][0] == null) {
      		  array[j][0] = to;
      		  toIndex = j;
      		  break;
      	  }
      	  else if (array[j][0].equals(to)) {
      		  toIndex = j;
      		  break;
      	  }
        }
        array[fromIndex][toIndex + 1] = "1";
        //array[toIndex][fromIndex + 1] = "1";
    }
    void printAdjMatrix() {
    	for (int i = 0; i < 50; i++) {
    		if (array[i][0] == null) {
    			break;
    		}
    		System.out.println("Relations from " + array[i][0]);
    		for (int j = 1; j < 51; j++) {
    			if (array[i][j] != null && i != j -1) {
    				int pathLen = Integer.parseInt(array[i][j]);
    				for (int ii = 0; ii < pathLen; ii++) {
    					System.out.print("	");
    				}
    				//System.out.print("	");
    				if (array[j - 1][0] != null) {
    					System.out.println(array[j - 1][0]);
    				}
    			}
    		}
    	}
    }
    
    void calcWarshall() {
    	for (int i = 0; i < 50; i++) {
    		for (int j = 1; j < 51; j++) {
    			if (array[i][j] != null) {
    				if (array[i][j].equals("1")) {
    					for (int ii = 1; ii < 51; ii++) {
    						if (array[j - 1][ii] != null) {
	    						if (array[j - 1][ii].equals("1") && i != j - 1) {
	    							if (array[i][ii] == null) { 
	    								array[i][ii] = "2";
	    							}
	    						}
    						}
    					}
    				}
    			}
    		}
    	}
    }
    
    
    public static void main(String args[]) {
        DirectedGraph g = new DirectedGraph();
        g.addRelation("Atlanta",  "Chattanooga");
        g.addRelation("Chattanooga",  "Nashville");
        g.addRelation("Chattanooga",  "Knoxville");
        g.addRelation("Atlanta",  "Birmingham");
        g.addRelation("Atlanta",  "Columbia");
        g.addRelation("Columbia", "Charleston");
        g.addRelation("Columbia",  "Greenville");
        g.addRelation("Greenville",  "Atlanta");
        g.addRelation("Charleston",  "Savanna");
        g.addRelation("Savanna",  "Atlanta");
        g.addRelation("Savanna", "Jacksonville");
        g.addRelation("Jacksonville", "Atlanta");
        g.addRelation("Knoxville", "Greenville");
        
        g.printAdjMatrix();
        g.calcWarshall();
        g.printAdjMatrix();
     }
}