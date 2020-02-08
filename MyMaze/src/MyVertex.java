import java.util.ArrayList;


public class MyVertex implements Vertex{

	static int numVert = 0;
	private ArrayList<Edge> edges;
	private Pair weight = new MyPair();
	private int row = -1;
	private int col = -1;
	private int id;
	private MyVertex wall; 
	private ArrayList<Vertex> closeVertexes = new ArrayList<Vertex>();
	
	public void addCloseVertex(Vertex v){
		closeVertexes.add(v);
	}
	
	public ArrayList<Vertex> getCloseVertex(){
		return closeVertexes;
	}
	public MyVertex() {
		id = numVert;
		numVert++;
		edges = new ArrayList<Edge>();
	}

	public void setWall(MyVertex v){
		wall = v;
	}
	
	public MyVertex getWall(){
		return wall;
	}
	public void setRow(int row){
		this.row = row;
	}
	
	public int getId(){
		return id;
	}
	public void setCol(int col){
		this.col = col;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}
	
	@Override
	public Pair getElement() {
		return weight;
	}

	@Override
	public void setElement(Pair e) {
		weight = e;
	}

	@Override
	public ArrayList<Edge> incidentEdges() {
		return edges;
	}

	public void removeEdge(Edge edge) {
		edges.remove(edge);
	}
	
	public void insertEdge(Edge edge) {
		edges.add(edge);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edges == null) ? 0 : edges.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MyVertex)) {
			return false;
		}
		MyVertex other = (MyVertex) obj;
		if (other.getId() == this.id){
			return true;
		}
		if (other.getId() != this.id){
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<Vertex> adjacentVertices() {
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		
		for (Edge edge : edges) {
			if (!((MyEdge) edge).getVertex1().equals(this)) {
				vertices.add(((MyEdge) edge).getVertex1());
			}
			
			else if (!((MyEdge) edge).getVertex2().equals(this)) {
				vertices.add(((MyEdge) edge).getVertex2());
			} 
		}
		
		return vertices;
	}
	
	public String toString(){
		return "" + id;
	}
}
