import java.awt.Color;
import java.awt.Graphics;

public class MyMap implements TileBasedMap {

	private int width;
	private int height;
	private int gridRowNum;
	private int gridColNum;
	private Rectangle[] obstacles;
	
	private Grid grid;
	
	private boolean[][] visited;
	private boolean[][] isBlocked ;


	

	//TODO:default mymap with default values for testing 
	public MyMap(){
	}
	
	
	public MyMap(int width, int height, int gridRowNum, int gridColNum, Rectangle[] obstacles) {
		super();
		this.width = width;
		this.height = height;
		this.gridRowNum = gridRowNum;
		this.gridColNum = gridColNum;
		this.obstacles = obstacles;
		grid = new Grid(gridRowNum,gridColNum, width, height);//draw grid for testing
		visited = new boolean[gridRowNum][gridColNum];
		isBlocked = new boolean[gridRowNum][gridColNum];
		//save map for blocked information
		for(Rectangle obstacle : obstacles){
			//check if obstacles are indeed within map range
			if(obstacle.getLeft() < 0 || obstacle.getRight() > width|| obstacle.getLower() < 0 || obstacle.getUpper() > height){
				System.out.println("ERR: Rectangle outside Map");//TODO:ERR MSG STANDARLIZE
			}
			
			
			//populate blocked flags
			int startXIndex = (int)Math.floor(obstacle.getLeft()/grid.getGridWidth());
			int endXIndex = (int)Math.floor(obstacle.getRight()/grid.getGridWidth());
			int startYIndex = (int)Math.floor(obstacle.getUpper()/grid.getGridWidth());

			int endYIndex = (int)Math.floor(obstacle.getLower()/grid.getGridWidth());

			for(int idx = startXIndex; idx < endXIndex; idx++){
				for(int idy = startYIndex; idy < endYIndex; idy++){
					isBlocked[idx][idy] = true;
				}
			}
		}
		
	}

	@Override
	public int getWidthInTiles() {
		return gridRowNum;
	}

	@Override
	public int getHeightInTiles() {
		return gridColNum;
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		visited[x][y] = true;
	}

	@Override
	public boolean blocked(int x, int y) {

	return isBlocked[x][y];
	}

	@Override
	public float getCost(int sx, int sy, int tx, int ty) {
		return 1;
	}
	
	public void draw(Graphics g){
		  
	         // Your custom painting codes. For example,
	         // Drawing primitive shapes
	        
		
	         g.setColor(Color.RED);       // change the drawing color
	    
	         for(Rectangle obstacle : obstacles){
	         
	        	 Point transformedUpperLeft = invertCoordinatesForJCanvas(obstacle.getUpperLeft());
	        	 g.fillRect((int)transformedUpperLeft.getX(), (int)transformedUpperLeft.getY(), (int)obstacle.getWidth(), (int)obstacle.getHeight());
	         }
	         
	         g.setColor(Color.BLUE);       // change the drawing color
	         grid.drawOnCanvas(g);
	}
	
	private Point invertCoordinatesForJCanvas(Point ori){
		return new Point(ori.getX(), ori.getY());
		
	}
	
	public int getCellRowIdx(double xCoor){
		
		return (int) Math.floor(xCoor/grid.getGridWidth());
	}
	
	public int getCellColIdx(double yCoor){
		
		return (int) Math.floor(yCoor/grid.getGridHeight());
	}

}
