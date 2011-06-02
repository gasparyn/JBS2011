/**
* class that creates a point struct
* each point has x,y coordinate, a value it holds(wall or space)
* and whether the point's been visited or not
*/
public class Point{
		int x,y;
		int value;
		boolean visited=false;
	
		
		
		 Point(int x, int y){
			 this.x=x;
			 this.y=y;
			 
		 }

}