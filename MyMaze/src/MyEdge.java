import java.util.ArrayList;


public class MyEdge implements Edge {

	private Vertex vertex1;
	private Vertex vertex2;
	private int weight = 0;

	public MyEdge(Vertex v1, Vertex v2) {
		vertex1 = v1;
		vertex2 = v2;
		((MyVertex)v1).insertEdge(this);
		((MyVertex)v2).insertEdge(this);
	}

	public Vertex getVertex1() {
		return vertex1;
	}

	public Vertex getVertex2() {
		return vertex2;
	}

	@Override
	public int getElement() {
		return weight;
	}

	@Override
	public void setElement(int e) {
		weight = e;
	}

	@Override
	public ArrayList<Vertex> vertices() {
		ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
		vertexList.add(vertex1);
		vertexList.add(vertex2);
		return vertexList;
	}

	public String toString (){
		return "[<" + vertex1 + ", " + vertex2 + ">]";			
	}
}
