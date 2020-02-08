import java.util.ArrayList;

public class MyPriorityQueue {

	private ArrayList<Edge> myEdges = new ArrayList<Edge>();
 	private ArrayList<Vertex> myVertices = new ArrayList<Vertex>();
	
	public MyPriorityQueue(){
		
	}

	public Vertex removeMin(){
		return myVertices.remove(myVertices.size()-1);
	}
	
	public Edge removeMinEdges(){
		return myEdges.remove(myEdges.size()-1);
	}
	
	public void insert(Vertex v){
		for(int i=0;i<myVertices.size();i++){
			if(v.getElement().getX() > myVertices.get(i).getElement().getX()){
				myVertices.add(i, v);
				return;
			}
		}
		
		myVertices.add(v);
	}
	
	public void insertEdges(Edge e){
		for(int i=0;i<myEdges.size();i++){
			if(e.getElement() > myEdges.get(i).getElement()){
				myEdges.add(i, e);
				return;
			}
		}
		
		myEdges.add(e);
	}
	
	public boolean isEmpty(){
		return myVertices.isEmpty();
	}
	
	public boolean isEmptyEdges(){
		return myEdges.isEmpty();
	}
	
	public void replaceKey(Vertex v){
		myVertices.remove(v);
		insert(v);
	}
	
	public void replaceKey(Edge e){
		myEdges.remove(e);
		insertEdges(e);
	}
}
