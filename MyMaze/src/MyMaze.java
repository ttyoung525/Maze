import java.util.ArrayList;


public class MyMaze implements Maze {

	private MyVertex[][] maze;
	private Graph labyrinth;
	private Vertex start;
	private Vertex end;

	public MyMaze() {
		labyrinth = new MyGraph();
	}

	public Graph getGraph (){
		return labyrinth;
	}

	public void generateMaze(int rows, int cols){
		if(rows < 2 || cols < 2){
			return;
		}

		this.maze = new MyVertex[rows][cols];

		//load the vertArray into the graph
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				maze[i][j] = new MyVertex();
				MyVertex v = maze[i][j];
				v.setCol(i);
				v.setRow(j);
				labyrinth.addVertex(v);
			}
		}
		
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				MyVertex v = maze[i][j];
				if (i-1 >= 0){
					v.addCloseVertex(maze[i-1][j]);
				}
				if (j - 1 >= 0){
					v.addCloseVertex(maze[i][j-1]);
				}
				if (i + 1 < maze.length){
					v.addCloseVertex(maze[i+1][j]);
				}
				if (j + 1 < maze[i].length){
					v.addCloseVertex(maze[i][j+1]);
				}
			}
		}

		labyrinth.minimumSpanningTree();

		int labCount = 0;
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				maze[i][j] = (MyVertex) labyrinth.vertices().get(labCount);
				labCount++;
			}
		}
		this.start = labyrinth.vertices().get((int)((Math.random()*labyrinth.vertices().size())));
		this.end = labyrinth.vertices().get((int)((Math.random()*labyrinth.vertices().size())));

		while(start.equals(end) && labyrinth.vertices().size()>1){
			end = labyrinth.vertices().get((int)((Math.random()*labyrinth.vertices().size())));	
		}

		
	}

	

	@Override
	public ArrayList<Vertex> solveMaze() {
		
		return labyrinth.shortestPath(start, end);
	}

	@Override
	public Graph toGraph() {
		return labyrinth;
	}

	@Override
	public Vertex[][] toArray() {
		return maze;
	}

	@Override
	public Vertex startVertex() {
		return start;
	}

	@Override
	public Vertex finishVertex() {
		return end;
	}

	public String toString() {
		if(labyrinth == null){
			return " ";
		}

		char[][] mazeGraph = new char[maze.length*2-1][maze[0].length*3-2];
		ArrayList<Vertex> path = solveMaze();

		for(int index=0;index<mazeGraph.length;index++){
			for(int jindex=0;jindex<mazeGraph[0].length;jindex++){
				mazeGraph[index][jindex] = ' ';
			}
		}

		for(int index=0;index<path.size();index++){
			if(index == 0){
				path.get(index).getElement().setX(-2);
			}

			else if(index == path.size()-1){
				path.get(index).getElement().setX(-5);
			}

			else{
				path.get(index).getElement().setX(-3);
			}

			if(index < path.size() -1){
				labyrinth.findEdge(path.get(index), path.get(index+1)).setElement(-4);
			}
		}

		for(int index=0;index<mazeGraph.length;index+=2){
			for(int jindex=0;jindex<mazeGraph[0].length;jindex+=3){

				int convert = (((index/2)*maze[0].length)+(jindex/3));
				
				if(labyrinth.vertices().get(convert).equals(start)){
					mazeGraph[index][jindex] = 'S';
				}

				else if(labyrinth.vertices().get(convert).getElement().getX() == -3){
					mazeGraph[index][jindex] = 'o';
				}

				else if(labyrinth.vertices().get(convert).getElement().getX() == -5){
					mazeGraph[index][jindex] = 'F';
				}

				else{
					mazeGraph[index][jindex] = '•';
				}
			}
		}

		for(int index=0;index<mazeGraph.length;index+=2){
			for(int jindex=0;jindex<mazeGraph[0].length;jindex+=3){

				int convert = (((index/2)*maze[0].length)+(jindex/3));
				int convertRight = convert+1;
				int convertDown = convert+maze.length;
				
				if(convertRight < labyrinth.vertices().size() && 
						labyrinth.areConnected(labyrinth.vertices().get(convert), labyrinth.vertices().get(convertRight))){
					mazeGraph[index][jindex+1] = '-';
					mazeGraph[index][jindex+2] = '-';
				}

				if(convertDown < labyrinth.vertices().size() && 
						labyrinth.areConnected(labyrinth.vertices().get(convert), labyrinth.vertices().get(convertDown))){
					mazeGraph[index+1][jindex] = '|';
				}

				if(convertRight < labyrinth.vertices().size() && 
						labyrinth.findEdge(labyrinth.vertices().get(convert), labyrinth.vertices().get(convertRight)) != null && 
						labyrinth.findEdge(labyrinth.vertices().get(convert), labyrinth.vertices().get(convertRight)).getElement() == -4){
					mazeGraph[index][jindex+1] = '=';
					mazeGraph[index][jindex+2] = '=';
				}

				if(convertDown < labyrinth.vertices().size() && 
						labyrinth.findEdge(labyrinth.vertices().get(convert), labyrinth.vertices().get(convertDown)) != null &&
						labyrinth.findEdge(labyrinth.vertices().get(convert), labyrinth.vertices().get(convertDown)).getElement() == -4){
					mazeGraph[index+1][jindex] = '!';
				}
			}
		}

		String print = "";

		for(int index=0;index<mazeGraph.length;index++){
			for(int jindex=0;jindex<mazeGraph[0].length;jindex++){
				print = print+mazeGraph[index][jindex];
			}
			print = print+"\n";
		}
		return print;
	}
}