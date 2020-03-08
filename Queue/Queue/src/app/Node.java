package app;

public class Node {
	private String name;
	private Node next;
	public Node(){
		name=null;
		next=null;
	}
	public Node(String aName, Node aNext){
		name=aName;
		next=aNext;
	}
	public void setName(String aName){name=aName;}
	public void setNext(Node aNext){next=aNext;}
	public String getName(){return name;}
	public Node getNext(){return next;}
	public static void main(String[] args) {
		
	}
}