import java.awt.Graphics;

public class Grid {

	private int gridRowNum, gridColNum;
	private int mapWidth, mapHeight;
	
	public Grid(int rows, int cols, int mapWidth, int mapHeight){
		
		gridRowNum = rows;

		gridColNum = cols;
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		
	}
	public int getGridRowNum() {
		return gridRowNum;
	}


	public void setGridRowNum(int gridRowNum) {
		this.gridRowNum = gridRowNum;
	}


     
	


	public int getGridColNum() {
		return gridColNum;
	}


	public void setGridColNum(int gridColNum) {
		this.gridColNum = gridColNum;
	}
	
	public int getGridHeight(){
		return mapHeight/gridRowNum;
	}
	public int getGridWidth(){
		return mapWidth/gridColNum;
	}
	

	public int getCellLeft(int rowIndex, int colIndex){
		return rowIndex * getGridWidth();
		
	}
	public int getCellRight(int rowIndex, int colIndex){
		return (rowIndex+1) * getGridWidth();

	}
	public int getCellLower(int rowIndex, int colIndex){
		return rowIndex * getGridHeight();

	}

	public int getCellUpper(int rowIndex, int colIndex){
		return (rowIndex+1) * getGridHeight();

	}

	public void drawOnCanvas(Graphics g){
		int gridHeight = mapHeight/gridRowNum;
		int gridWidth = mapWidth/gridColNum;
		for (int index = 1 ; index < gridColNum ; index++) {
		g.drawLine(0, index*gridHeight,mapWidth, index*gridHeight);
	}
		for (int index = 1 ; index < gridRowNum ; index++) {
		g.drawLine(index*gridWidth,0,index*gridWidth, mapHeight);
	}
	}
}
