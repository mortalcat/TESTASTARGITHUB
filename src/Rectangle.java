
public class Rectangle {

	private Point[] vertice;

	private Point UpperLeft, UpperRight, LowerLeft, LowerRight;
	
	public Point getUpperLeft() {
		return UpperLeft;
	}




	public Point getUpperRight() {
		return UpperRight;
	}




	public Point getLowerLeft() {
		return LowerLeft;
	}




	public Point getLowerRight() {
		return LowerRight;
	}




	public Rectangle(Point[] vertice){
		
		if(vertice.length !=4){
			System.out.println("ERR: not a rectangle");
		return;
		}


		this.vertice = vertice;

	if(!identifyVertice()){
		System.out.println("ERR: rectangle vertices can not be identified");
		return;
	}
	
	}

		
	public Rectangle(double upperLeftX, double upperLeftY, double width, double height){
		this.UpperLeft = new Point(upperLeftX, upperLeftY);
		this.UpperRight = new Point(upperLeftX+width, upperLeftY);
		this.LowerLeft  = new Point(upperLeftX, upperLeftY - height);
		this.LowerRight = new Point(upperLeftX + width, upperLeftY-height);
	}
	
	private boolean identifyVertice(){
		
		Point first = vertice[0];
		double lowerC = first.getY();
		double leftC = first.getX();
		for(Point vertex : vertice){
			
			if(vertex.getX() < leftC) 
			leftC = vertex.getX();
			if(vertex.getY() < lowerC) 
			leftC = vertex.getY();
		}
		
		for(Point vertex : vertice){
		if(vertex.getY() == lowerC && vertex.getX() == leftC){
			LowerLeft = vertex;
		} else if( vertex.getY() == lowerC && vertex.getX() != leftC){
			LowerRight = vertex;
		} else if(vertex.getY()!= lowerC && vertex.getX() == leftC){
			UpperLeft =vertex;
		} else {
			UpperRight = vertex;
		}
		
		}
		//test if all 4 points are assigned
		
		
		return (UpperRight!=null && UpperLeft!= null && LowerRight!=null && LowerLeft!=null);
	}




	public double getHeight() {

		return UpperLeft.getY() - LowerLeft.getY();
	}








	public double getWidth() {
		return UpperRight.getX() - UpperLeft.getX();
	}



  public double getLeft(){
	  return UpperLeft.getX();
  }
  
  public double getRight(){
	  return UpperRight.getX();
  }
  
  public double getLower(){
	  return LowerLeft.getY();
  }
  
  public double getUpper(){
	  return UpperLeft.getY();
  }

	
	}
	
	
	

