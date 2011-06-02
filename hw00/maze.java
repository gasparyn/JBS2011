import java.util.*;

/**
 * @author Gaspar Obimba
 * Brandeis University
 * Summer 2011- JBS
 * Pre work Assignment
 * This class represents a n by m maze
 * Each position in the maze can be designated by two coordinates, x (across) and y (down).
 * For example,a 4x5 maze the top row of positions (x,y) would be (0,0), (1,0), (2, 0) and (3,0).
 */
class maze{

	int rows,columns;
	Point[][] cell=new Point[9][9]; //array to keep the maze


	/**creates a constructor for the maze
	 * The constructor of the Maze class should take two parameters for n and m.
	 * Note of course that you need to represent the walls between cells not just the cells.
	 */
	public maze(int m, int n){
		Point p;

		this.rows=m;
		this.columns=n;


		//initialise the maze
		for (int i=0;i<rows;i++){
			for (int j=0;j<columns;j++){
				p=new Point(j,i);
				p.visited=false; //each point in a cell has not been visited yet
				p.value=1; //mark cell as a wall
				cell[p.x=j][p.x=i]=p;
			}
		}

	}//end maze constructor

	/**initialize the maze to have the given string input
	 *
	 * @param mazeString -strig to be loaded into the maze
	 */
	public void load(String mazeString){

		//ensure the string given is of the right length for a given mxn maze
		if (mazeString.length()%rows==0 && mazeString.length()/columns==rows){

			for (int i=0;i<mazeString.length();i++){

				Point s=new Point(i%columns,i/columns);
				s.value =mazeString.charAt(i)-48;
				//path.add(s);
				cell[s.x][s.y]=s;
			}
		}

		else
			System.out.println("Error in String of length:"+mazeString.length()+" Should be "+columns*rows);
	}

	/**@display() prints a diagram of the maze on the console
	 *
	 *
	 */
	public void display(){

		for (int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				System.out.print(this.cell[j][i].value+" ");

			}
			System.out.println();
		}

	}

	/**a method that determines if there's a way to walk
	 * from a specified beginning position to a specified ending position
	 * @param begX begin x coordinate
	 * @param begY " y "
	 * @param endX end x coordinate
	 * @param endY end y "
	 * @return true if maze is solvable
	 */


	public boolean solve(int begX,int begY,int endX, int endY){
		if (helpSolve(begX, begY, endX, endY) == null) //check if the list is empty
			return false;
		else
			return true;
	}


	/** helper method that determines if there's a way to walk
	 * from a specified beginning position to a specified ending position
	 * @param begX
	 * @param begY
	 * @param endX
	 * @param endY
	 * @return
	 */
	public ArrayList<Point> helpSolve(int begX,int begY,int endX, int endY){
		Queue<Point> q= new LinkedList<Point>();// queue that holds all the visited points of the maze
		ArrayList<Point> path=new ArrayList<Point>();//keeps track of the path traced

		Point begin,end,p1;


		ArrayList<Point> neighbourList;
		ArrayList<Point> find = new ArrayList<Point>();

		begin=cell[begX][begY];//create the original point
		end=cell[endX][endY];

		//if the given point is a wall
		if (begin.value==1||end.value==1)
			find= null;

		//if the given point is NOT a wall
		else{

			begin.visited=true;
			q.add(begin);//insert point p into the queue
			path.add(begin);//add this point onto the path list

			//until the queue is empty
			while(!q.isEmpty()){
				p1=q.remove(); //remove point at the head of the queue

				neighbourList=getNeighbours(p1);// returns neighbours of a point p1 ie left, up,right and down
				if(!neighbourList.isEmpty())

					//for all neighbours of the point p1, try moving towards the target point
					for (int i=0;i<neighbourList.size();i++){
						if (begX == endX && begX == endY){
							path.add(begin);
							find=path;
							break;
						}
						else if(neighbourList.get(i).x==endX && neighbourList.get(i).y==endY){ // point A matches the destination point B

							neighbourList.get(i).visited=true; //mark as visited
							path.add(neighbourList.get(i)); //add this point to the path
							find= path; //mark that there's a path from begin to end
							break;
						}
						else{ //a point does not match the desired destination

							neighbourList.get(i).visited=true; //visit this point
							q.add(neighbourList.get(i)); //add this point to the queue
							path.add(neighbourList.get(i)); //add this point to the path

						}

					}//end for
			}//end while


		}
		//reset maze
		for (int i=0;i<rows;i++){
			for (int j=0;j<columns;j++){
				cell[j][i].visited = false;
			}
		}
		return find;
	}

	/** helper method that returns an unvisited points neighbouring to v and visit them
	 *
	 * @param current point
	 * @return
	 */
	public ArrayList<Point> getNeighbours(Point v){
		ArrayList<Point> neighbourList=new ArrayList<Point>();

		//get top neighbour
		if (cell[v.x][v.y-1].value==0 && cell[v.x][v.y-1].visited==false && v.y-1>0){
			neighbourList.add(cell[v.x][v.y-1]);
		}
		//get down neighbour
		if (cell[v.x][v.y+1].value==0 && cell[v.x][v.y+1].visited==false && v.y+1<rows){
			neighbourList.add(cell[v.x][v.y+1]);

		}

		//get right neighbour
		if (cell[v.x+1][v.y].value==0 && cell[v.x+1][v.y].visited==false && v.x+1<columns){
			neighbourList.add(cell[v.x+1][v.y]);
		}
		//get left neighbour
		if (cell[v.x-1][v.y].value==0 && cell[v.x-1][v.y].visited==false && v.x-1>0){
			neighbourList.add(cell[v.x-1][v.y]);
		}

		return neighbourList;
	} // end getNeighbours() and visit them




	/**method that is just like solve() but traces the positions that the solution visits.
	 *
	 * @param begX
	 * @param begY
	 * @param endX
	 * @param endY
	 */
	public void trace(int begX,int begY,int endX, int endY){

		if (solve(begX,begY,endX,endY)){ //if theres a way from begin point to end point
			ArrayList<Point> path2 = helpSolve(begX, begY, endX, endY); //c

			System.out.print("Path :");

			for(int i=0;i<path2.size();i++){
				System.out.print("("+path2.get(i).x+", "+path2.get(i).y+") => ");

			}

		}
	}
	/**redesign() will reset all the cells and walls and come up
	 * with a random new maze of the same size.
	 */
	public void redesign(){
		Random generator = new Random();
		String random="";
		for (int i=0;i<rows*columns;i++){
			String mazeValue;

			if(i%columns==0||i/columns==0)
				mazeValue="1";
			else
				mazeValue= Integer.toString(generator.nextInt(2));

			mazeValue += random;
		}

		load(random);
		display();
	}//end redisgn()

}//end class maze

