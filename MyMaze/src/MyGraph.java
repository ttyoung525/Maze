import java.util.ArrayList;


public class MyGraph implements Graph {

	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();

	@Override
	public ArrayList<Vertex> vertices() {
		return vertices;
	}
	@Override
	public Vertex addVertex(Pair p) {
		Vertex v = new MyVertex();
		v.setElement(p);
		return addVertex(v);
	}

	/**
	 * Adds a vertex to the graph
	 * 
	 * @param v The vertex to be added
	 * @return The vertex added
	 */
	@Override
	public Vertex addVertex(Vertex v) {
		if (v == null) {
			throw new NullPointerException("input is null");
		}

		vertices.add(v);
		return v;
	}

	@Override
	public boolean removeVertex(Pair p) {
		Vertex v = findVertex(p);
		return removeVertex(v);
	}

	/**
	 * Removes a vertex from the graph
	 * 
	 * @param v The vertex to be removed
	 * @return Returns true if the specified vertex was removed, otherwise false
	 */
	@Override
	public boolean removeVertex(Vertex v) {
		if (v == null) {
			return false;
		}

		boolean removed = vertices.remove(v);

		if (!removed) {
			return false;
		}

		for (Edge edge : v.incidentEdges()) {
			Vertex opposite = ((MyEdge) edge).getVertex1();

			if (!((MyEdge) edge).getVertex2().equals(v)) {
				opposite = ((MyEdge) edge).getVertex2();
			}

			((MyVertex) opposite).removeEdge(edge);

			edges.remove(edge);
		}

		return removed;
	}

	@Override
	public Vertex findVertex(Pair p) {
		if(p == null){
			throw new NullPointerException("input is null");
		}

		for(int i=0;i<vertices.size();i++){
			if(vertices.get(i).getElement().equals(p)){
				return vertices.get(i);
			}
		}
		return null;
	}

	/**
	 * Returns a list of edges in the graph
	 * 
	 * @return A list of edges in the graph
	 */
	@Override
	public ArrayList<Edge> edges() {
		return edges;
	}

	/**
	 * Adds an edge between two given vertices
	 * 
	 * @param v1 First vertex in the ordered pairing
	 * @param v2 Second vertex in the ordered pairing
	 * 
	 * @return The edge that was added
	 */
	@Override
	public Edge addEdge(Vertex v1, Vertex v2) {
		if (v1 == null || v2 == null) {
			throw new NullPointerException("input is null");
		}

		if (!vertices.contains(v1)) {
			vertices.add(v1);
		}

		if (!vertices.contains(v2)) {
			vertices.add(v2);
		}

		MyEdge edge = new MyEdge(v1, v2);
		v1.adjacentVertices().add(v2);
		v2.adjacentVertices().add(v1);
		edges.add(edge);

		return edge;
	}


	/**
	 * Adds an edge
	 * 
	 * @param e edge to be added
	 * 
	 * @return The edge that was added
	 */
	@Override
	public Edge addEdge(Edge e) {
		if (e == null) {
			throw new NullPointerException("input is null");
		}

		if (edges.contains(e)) {
			return e;
		}

		edges.add(e);
		
		e.vertices().get(0).adjacentVertices().add(e.vertices().get(1));
		e.vertices().get(1).adjacentVertices().add(e.vertices().get(0));
		
		return e;
	}

	/**
	 * Removes an edge between two vertices
	 * 
	 * @param v1 First vertex in the ordered pairing
	 * @param v2 Second vertex in the ordered pairing
	 * 
	 * @return Returns true if the specified edge was removed, otherwise false
	 */
	@Override
	public boolean removeEdge(Vertex v1, Vertex v2) {
		if (v1 == null || v2 == null) {
			return false;
		}

		for (Edge edge : edges) {
			if(((MyEdge)edge).getVertex1().equals(v1) && ((MyEdge)edge).getVertex2().equals(v2) || 
					((MyEdge)edge).getVertex1().equals(v2) && ((MyEdge)edge).getVertex2().equals(v1)) {
				((MyVertex)v1).removeEdge(edge);
				((MyVertex)v2).removeEdge(edge);
				return edges.remove(edge);
			}
		}

		return false;
	}

	/**
	 * Removes an edge
	 * 
	 * @param v1 First vertex in the ordered pairing
	 * 
	 * @return Returns true if the specified edge was removed, otherwise false
	 */
	@Override
	public boolean removeEdge(Edge e) {
		if (e == null) {
			return false;
		}

		for (Vertex v : e.vertices()) {
			((MyVertex)v).removeEdge(e);
		}

		return edges.remove(e);
	}


	/**
	 * Finds an edge between two given vertices
	 * 
	 * @param v1 First vertex in the ordered pairing
	 * @param v2 Second vertex in the ordered pairing
	 * 
	 * @return the edge between the two vertices if it exists, otherwise null
	 */
	@Override
	public Edge findEdge(Vertex v1, Vertex v2) {
		if (v1 == null || v2 == null) {
			return null;
		}

		for (Edge edge : edges) {
			if(((MyEdge)edge).getVertex1().equals(v1) && ((MyEdge)edge).getVertex2().equals(v2) || 
					((MyEdge)edge).getVertex1().equals(v2) && ((MyEdge)edge).getVertex2().equals(v1)) {
				return edge;
			}
		}

		return null;
	}


	/**
	 * Returns true if the two vertices are connected, otherwise false
	 * 
	 * @param v1 First vertex in the ordered pairing
	 * @param v2 Second vertex in the ordered pairing
	 * 
	 * @return true if the two vertices are connected, otherwise false
	 */
	@Override
	public boolean areConnected(Vertex v1, Vertex v2) {
		if (v1 == null || v2 == null) {
			return false;
		}

		if (v1.adjacentVertices().size() < v2.adjacentVertices().size()) {
			return v1.adjacentVertices().contains(v2);
		}

		return v2.adjacentVertices().contains(v1);
	}

	/**
	 * Returns a list of all vertices connected to the given vertex
	 * 
	 * @param v1 the given vertex
	 * 
	 * @return A list of all connected vertices
	 */
	@Override
	public ArrayList<Vertex> adjacentVertices(Vertex v1) {
		return v1.adjacentVertices();
	}

	/**
	 * Finds all edges connected to a given vertex
	 * 
	 * @param v1 The given vertex
	 * 
	 * @return a list of all edges connected to the given vertex
	 */
	@Override
	public ArrayList<Edge> incidentEdges(Vertex v1) {
		return v1.incidentEdges();
	}

	@Override
	public ArrayList<Vertex> shortestPath(Vertex v1, Vertex v2) {

		MyPriorityQueue myPQ = new MyPriorityQueue();
		int infinite = Integer.MAX_VALUE;
		ArrayList<Vertex> path = new ArrayList<Vertex>();
		Vertex curr = v2;
		
		ArrayList<Edge> edges = (ArrayList<Edge>) this.edges.clone();
		ArrayList<Vertex> vertices = (ArrayList<Vertex>) this.vertices.clone();

		for(int i=0;i<vertices.size();i++){
			if(vertices.get(i).equals(v1)){
				vertices.get(i).getElement().setX(0);
			}

			else{
				vertices.get(i).getElement().setX(infinite);
			}

			vertices.get(i).getElement().setY(-1);
			myPQ.insert(vertices.get(i));
		}

		while(!myPQ.isEmpty()){
			Vertex min = myPQ.removeMin();

			if (min.equals(v2)) {
				break;
			}

			for(Edge edge : min.incidentEdges()){
				Vertex opposite = ((MyEdge) edge).getVertex1();

				if (!((MyEdge) edge).getVertex2().equals(min)) {
					opposite = ((MyEdge) edge).getVertex2();
				}

				int newDistance = min.getElement().getX()+edge.getElement();

				if(opposite.getElement().getX() > newDistance){
					opposite.getElement().setX(newDistance);
					opposite.getElement().setY(vertices.indexOf(min));
					myPQ.replaceKey(opposite);
				}
			}
		}



		while(!curr.equals(v1) && curr.getElement().getY() != -1){
			path.add(curr);
			curr = vertices.get(curr.getElement().getY());
		}

		if(!curr.equals(v1)){
			return new ArrayList<Vertex>();
		}

		int index=0;
		int jindex = path.size()-1;

		while(index<jindex){
			Vertex temp = path.get(index);
			path.set(index, path.get(jindex));
			path.set(jindex, temp);
			index++;
			jindex--;
		}

		ArrayList<Vertex> tempPath = new ArrayList<Vertex>();
		tempPath.add(v1);
		for (int i = 0; i < path.size(); i++){
			tempPath.add(path.get(i));
		}
		
		path = tempPath;
		
		return path;
	}

	@Override
	public Graph minimumSpanningTree() {

		Graph spanTree = new MyGraph();

		//arrayList of connected vertices
		ArrayList<MyVertex> mazeVert = new ArrayList<MyVertex>();

		//add starting position for spanning algorithm
		mazeVert.add((MyVertex) this.vertices.get((int) (Math.random()*this.vertices().size())));

		ArrayList<MyVertex> walls = walls(mazeVert.get(0));
		

		//implement Prim's algorithm 
		while(walls.size()>0 && mazeVert.size() < this.vertices().size()){
			MyVertex v1 = walls.get((int) (Math.random()*walls.size()));
			
			if(!(mazeVert.contains(v1))){
				MyVertex v2 = v1.getWall();
				this.addEdge(v1, v2);
				mazeVert.add(v1);
				ArrayList<MyVertex> tempWalls = this.walls(v1);
				for (int i = 0; i < tempWalls.size(); i++){

					walls.add(tempWalls.get(i));
				}
				walls.remove(v1);
			}
		}

		return spanTree;
	}
	//helper method for spanning tree algorithm
		private ArrayList<MyVertex> walls(MyVertex v){
			ArrayList<MyVertex> walls = new ArrayList<MyVertex>();
			

			for (int i = 0; i < v.getCloseVertex().size(); i++){
				walls.add((MyVertex) v.getCloseVertex().get(i));
				 ((MyVertex)v.getCloseVertex().get(i)).setWall(v);
			}

			//remove any that are already connected
			for (int i = 0; i < walls.size(); i++){
				if (v.adjacentVertices().contains(walls.get(i))){
					walls.remove(i);
					i--;
				}
			}

			return walls;
		}
}
