// Josh Newsome
// COS230 ASG05
// Various bits of code taken from the textbook Data Structures and Algorithms by Robert Lafore. Help also gotten from Damien King on various parts of code.
// ConvertGPSCoordinates class taken from assignment upload.
// See http://www.movable-type.co.uk/scripts/latlong.html

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


class ConvertGPSCoordinantes {
// calcDist take two latitude / longitude pairs and returns the
// distance between them in KILOMETERS
	double calcDist(double lat1, double lon1, double lat2, double lon2){ // Takes in longitude and latitude and returns distance between two points on a map in kilometers
		double R = 6371; //km
		double radLat1 = Math.toRadians(lat1);
		double radLat2 = Math.toRadians(lat2);
		double radDiffLat = Math.toRadians(lat2-lat1);
		double radDiffLon = Math.toRadians(lon2-lon1);

		double a = Math.sin(radDiffLat/2) * Math.sin(radDiffLat/2) +
				Math.cos(radLat1) * Math.cos(radLat2) *
				Math.sin(radDiffLon/2) * Math.sin(radDiffLon/2);
		double c = 2 * Math.atan2(Math.sqrt(a),  Math.sqrt(1-a));

		return R * c;
	}
	public ConvertGPSCoordinantes() {
		// TODO Auto-generated constructor stub
	}
}

class hwyNameList { // Linked List specially made to work with hwy route names.
	public class Node {
	public int num1; // data stored in linked list
	public int num2;
	public String name;
	public Node next; // self-referencing pointer

		Node(int number1, int number2, Node nextNode, String name) { // Constructor
			num1 = number1;
			num2 = number2;
			next = nextNode;
			this.name = name;
		}
	}

	public Node head;

	public void addToFront(int n1, int n2, String n) { // Add new node to front of list.
		head = new Node(n1, n2, head, n);
	}

	public void addToEnd(int n1, int n2, String n) { // Add new node to end of list.
		if (head == null) {
			addToFront(n1, n2, n);
		} else {
			Node current = null;
			// could use while loop, but not going to
			for (current = head; current.next != null; current = current.next) {
				// do nothing; just get me to the end
			}
			current.next = new Node(n1, n2, null, n);
		}
	}

	public String getName(int n1, int n2) { // enter index's to receive route name according to position.
		String name = "";
		//
		//boolean checker = false;
		Node current = null;
		for (current = head; current != null; current = current.next) {
			if (current.num1 == n1 && current.num2 == n2) {
				name = current.name;
				System.out.println();
				break;
			}
			else if (current.num2 == n1 && current.num1 == n2) {
				name = current.name;
				break;
			}
		}

		return name;
	}
}

class adjacencyList { // adjacency list to hold in connection points between two cities.

	   public weightList[] wl;

	   //constructor for the adjacency list
	    public adjacencyList(int length){
	        wl = new weightList[length];
	    }

	    //takes in two indexes and returns the weight value stored at the given point
	    public double retrieve(int b, int e){
	        LNode c = wl[b].head;
	       // boolean found = false;
	        double weight = 1000000;

	        while(c!= null){
	            if(c.index == e){
	               // found = true;
	                weight = c.weight;
	                break;
	            }
	            c = c.next;
	        }
	        return weight;
	    }

	    public String getName(int b, int e) {

			return null;

	    }

	    //loops through the array and creates a new linked list for each entry
	    public void initialize(){
	        for(int i = 0; i < wl.length; i++){
	            wl[i] = new weightList();
	        }
	    }

	    //adds a edge as a node in a given linked list
	    public void edgeAdd(int b, int e, double w, String name){
	        wl[b].add(w,e, name);
	    }


	    //creates a linked list of weight nodes
	    class weightList {
	        LNode head;
	        public void add(double w, int i, String name) {
	            if (head == null)
	                head = new LNode(w, i, name);
	            else {
	                LNode n = new LNode(w, i, name);
	                n.next = head;
	                head = n;
	            }
	        }
	    }

	    //creates a node that holds an index and weight
	    class LNode{
	        double weight;
	         int index;
	         LNode next;
	         String name;
	         public LNode(double w, int i, String n){
	        	 name = n;
	             weight = w;
	             index = i;
	         }
	    }
}

class DistPar // distance and parent
{ // items stored in sPath array
    public double distance; // distance from start to this vertex
    public int parentVert; // current parent of this vertex
    // -------------------------------------------------------------
    public DistPar(int pv, double d) // constructor
    {
        distance = d;
        parentVert = pv;
    }
}

class graReader { // readers .gra file and outputs needed data for rest of code.
    public Graph read(String fn){
        try {
            File fi = new File(fn);
            Scanner f = new Scanner(fi);
            int vertTot;
            int edgeTot;
            String in [] = f.nextLine().split(" ");
            vertTot = Integer.parseInt(in[0]);
            edgeTot = Integer.parseInt(in[1]);
            Graph g = new Graph(vertTot);

            for(int i = 0; i < vertTot; i++){
                in = f.nextLine().split(" ");
               in[1] = in[1].replace(",","");
                g.addvList(in[0],Double.parseDouble(in[1]), Double.parseDouble(in[2]));
            }
            for(int i = 0; i < edgeTot; i++){
                in = f.nextLine().split(" ");
                //WILL NEED TO ADD PATH NAME HERE ONCE CHANGED TO ADJACENCY LIST
                g.addEdge(Integer.parseInt(in[0]),Integer.parseInt(in[1]), in[2]);
            }
            f.close();
            return g;
        }
        catch(IOException i){
            System.out.println("File Not Found!");
            System.exit(0);
        }
        catch(NumberFormatException n){
            System.out.println("Input error");
        }
        return null;
    }
}

class Graph { // taken from book, is primary block for running dijkstra's algorithm
    private final int INFINITY = 1000000;
    private vList[] vertexList; // list of vertices
    //private vList[] newVertexList;
    private int nVerts; // current number of vertices
    private int nTree; // number of verts in tree
    private DistPar[] sPath; // array for shortest-path data
    private int currentVert; // current vertex
    private double startToCurrent; // distance to currentVert
    int startTree;
    private linkedList vList = new linkedList();
    private int vCount;
    private int eCount;
    private adjacencyList list;
    private hwyNameList nameList = new hwyNameList();
    private String[] ogArr;
    private int initCount = 0;
    private int vertC;

    // -------------------------------------------------------------

    //constructor for the graph uses is called from the file reader
    public Graph(int vertC) // constructor
    {
    	this.vertC = vertC;
        vertexList = new vList[vertC];
        //this.vertexList = vertexList;
        // adjacency matrix
        //adjMat = new double[6000][6000];
        list = new adjacencyList(vertC);
        nVerts = 0;
        nTree = 0;

        list.initialize();
        sPath = new DistPar[vertC]; // shortest paths
        ogArr = new String[vertC];
    }

    //adds a vertex into the array of vertexes
    public void addvList(String lab, double lat, double lo) {
    	ogArr[initCount] = lab;
    	initCount++;
        vertexList[nVerts++] = new vList(lab, lat, lo);
    }
    // -------------------------------------------------------------

    //adds an edge into the adjacency list
    public void addEdge(int start, int end, String name)
    {
        ConvertGPSCoordinantes gp = new ConvertGPSCoordinantes();
        vList s = vertexList[start];
        vList e = vertexList[end];
        double weight = gp.calcDist(s.latitude,s.longitude,e.latitude,e.longitude);
        list.edgeAdd(start,end,weight, name);
        list.edgeAdd(end,start,weight, name);
        nameList.addToEnd(start, end, name);
       // adjMat[start][end] = weight; // (directed)
       // adjMat[end][start] = weight;
    }
    // -------------------------------------------------------------

    //calculates the shortest possible path between all points form a given starting point
    public void path(int start) // find all shortest paths
    {
        startTree = start; // start at vertex 0
        vertexList[startTree].isInTree = true;
        nTree = 0; // put it in tree
        // transfer row of distances from adjMat to sPath
        for(int j=0; j<nVerts; j++)
        {
            //double tempDist = adjMat[startTree][j];
            double tempDist = list.retrieve(startTree,j);
            sPath[j] = new DistPar(startTree, tempDist);
        }
        // until all vertices are in the tree
        while(nTree < nVerts) {
            if (nTree != start) {
                int indexMin = getMin(); // get minimum from sPath
                double minDist = sPath[indexMin].distance;
                if (minDist == INFINITY) // if all infinite
                { // or in tree,
                   // System.out.println("There are unreachable vertices");
                    break; // sPath is complete
                }
                else { // reset currentVert
                    currentVert = indexMin; // to closest vert
                    startToCurrent = sPath[indexMin].distance;
            // minimum distance from startTree is
            // to currentVert, and is startToCurrent
                }
            // put current vertex in tree
                vertexList[currentVert].isInTree = true;
            }
                nTree++;
                adjust_sPath(); // update sPath[] array
            }
           // end while(nTree<nVerts)
            //displayPaths(); // display sPath[] contents
            nTree = 0; // clear tree
            for (int j = 0; j < nVerts; j++)
                vertexList[j].isInTree = false;
    }

    //gets the next destination with the smallest weight
    public int getMin() // get entry from sPath
    {
        // with minimum distance
        double minDist = INFINITY; // assume minimum
        int indexMin = 0;
        for(int j=1; j<nVerts; j++) // for each vertex,
        {
            if( !vertexList[j].isInTree && // smaller than old one
                    sPath[j].distance < minDist )
            {
                minDist = sPath[j].distance;
                indexMin = j; // update minimum
            }
        } // end for
        return indexMin; // return index of minimum
    }

    // adjust values in shortest-path array sPath
    public void adjust_sPath() {
        int column = 0; // skip starting vertex
        while(column < nVerts) // go across columns
        {
                // if this column’s vertex already in tree, skip it
                if (vertexList[column].isInTree) {
                    column++;
                    continue;
                }
            // calculate distance for one sPath entry
            // get edge from currentVert to column
            //double currentToFringe = adjMat[currentVert][column];
            double currentToFringe = list.retrieve(currentVert,column);
            // add distance from start
            double startToFringe = startToCurrent + currentToFringe;
            // get distance of current sPath entry
            double sPathDist = sPath[column].distance;
            // compare distance from start with sPath entry
            if (startToFringe < sPathDist) // if shorter,
            { // update sPath
                sPath[column].parentVert = currentVert;
                sPath[column].distance = startToFringe;
            }

            column++;
        } // end while(column < nVerts)
    }

    public void getR(String b, String e){ // finds route from a to b, creates vertex to call back to when writing file
        int i = 0;
        boolean complete = false;
        if (b.equals(e)) {
            System.out.println("The start and end points can't be the same");
            return;
        }
        try {
        while(vertexList[i] != null){
            if(vertexList[i].label.equals(e)){
                complete = true;
                break;
            }
            else {
                i++;
            }
        }
        } catch(Exception e1) {
        	System.out.println("Invalid input");
        }
        if(!complete) {
            System.out.println("Input end not found");
            return;
        }
        complete = false;
        String current = vertexList[i].label;
        int place = i;
        int vertTot = 0;
        int edgeTot = 0;
        while(!complete){
            if(current.equals(b)){
                vList.addToBeginning(vertexList[place]);
                vertTot++;
                complete = true;
            }
            else if(current.equals(vertexList[sPath[place].parentVert].label)){
                System.out.println("End point wasn't found");
                break;
            }
            else {
                vList.addToBeginning(vertexList[place]);
                vertTot++;
                edgeTot++;
                place = sPath[place].parentVert;
                current = vertexList[place].label;
            }
        }
        vCount = vertTot;
        eCount = edgeTot;
        vList.reIndex();
    }

    public void graWrite(String fn) { // writes data to .gra file
        try {
            File f = new File(fn);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            }
            else {
                System.out.println("File already exists.");
            }
            FileWriter fw = new FileWriter(fn);
            fw.write(vCount + " " + eCount);
            String[] arr = new String[vCount];
            Node c = vList.head;
            int counter = 0;
            while(c !=null){
                fw.write(c.data.label+ " " + c.data.latitude + ", " + c.data.longitude);
                arr[counter] = c.data.label;
                counter++;
                c = c.next;
            }

            if(vList.head == null || vList.head.next == null){
                System.out.println("Invalid path");
                fw.close();
                return;
            }
            c = vList.head.next;
            Node prev = vList.head;
            //int count = 0;
            while(c !=null){
            	String cName1 = arr[prev.index];
            	String cName2 = arr[c.index];
            	int index1 = 0;
            	int index2 = 0;
            	for (int ii = 0; ii < vertC; ii++) {
            		if (ogArr[ii] == cName1) {
            			index1 = ii;
            		}
            		if (ogArr[ii] == cName2) {
            			index2 = ii;
            		}
            	}
                fw.write(prev.index+ " " + c.index + " " + nameList.getName(index1, index2));
                prev = c;
                c = c.next;
            }
            fw.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    int getIndex(String s){
        int i;
        for(i = 0; i < vertexList.length; i++){
            if(vertexList[i].label.equals(s))
                break;
        }
        return i;
    }
}


class Node{
    Node next;
    vList data;
    int index;
    public Node(vList d){
        data = d;
    }
}

class vList { // structure for vectors
    public String label; // label (e.g. ‘A’)
    public boolean isInTree;
    double latitude;
    double longitude;
    public vList(String lab,double lat, double lo) // constructor
    {
        label = lab;
        isInTree = false;
        latitude = lat;
        longitude = lo;
    }
    public vList() {

    }

}

class linkedList { // another linked list
    Node head;

    public void addToEnd(vList d){
        if(head == null)
            head = new Node(d);
        else{
            Node c = head;
            while(c.next != null){
                c = c.next;
            }
            c.next = new Node(d);
        }
    }

    public void addToBeginning(vList d){
        if(head == null)
            head = new Node(d);
        else{
            Node t = head;
            head = new Node(d);
            head.next = t;
        }
    }

    public void changeHeadIndex(int i){
        head.index = i;
    }

    public void reIndex(){
        Node c = head;
        int counter = 0;
        while(c != null){
            c.index = counter;
            c = c.next;
            counter++;
        }
    }

    public void printList(){
        Node c = head;
        while(c != null){
            if(c.next != null)
                System.out.print(c.data.label + " -> ");
            else
                System.out.print(c.data.label);
            c = c.next;
        }
        System.out.println();
    }
}

public class DijkstraHighway { // driver with main to run the program.
	public static void main(String[] args) {
        if(args.length == 4) {
        	try {
	            graReader v1 = new graReader();
	            Graph v2 = v1.read(args[0]);
	            int start = v2.getIndex(args[2]);
	            v2.path(start);
	            v2.getR(args[2], args[3]);
	            v2.graWrite(args[1]);
        	} catch (Exception e) {
        		System.out.println("Sanitized. Invalid input, try again.");
        	}
        }
    }

}
