import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT event classes and listener interfaces
import java.util.ArrayList;

import javax.swing.*;    // Using Swing's components and containers

import sourceCodeASTAR.AStarPathFinder;
import sourceCodeASTAR.Path;
import sourceCodeASTAR.PathFinder;
import sourceCodeASTAR.UnitMover;
 
/** Custom Drawing Code Template */
// A Swing application extends javax.swing.JFrame
public class initTest extends JFrame {
   // Define constants
   public static final int CANVAS_WIDTH  = 640;
   public static final int CANVAS_HEIGHT = 480;
 
   // Declare an instance of the drawing canvas,
   // which is an inner class called DrawCanvas extending javax.swing.JPanel.
   private DrawCanvas canvas;
   private MyMap mMap;
	private PathFinder finder;
	/** The last path found for the current unit */
	private Path path;
	static Rectangle[] testObstacleArr= {
			
	        new Rectangle(400, 350, 60, 50),
		    new Rectangle(50, 150, 20, 30),
		    new Rectangle(30, 10, 10, 5),
		    new Rectangle(72, 210, 20, 40)};
	private ArrayList<Connection> testConnections= new ArrayList<Connection>() ;
	/***
	 	
        new Rectangle(400, 350, 60, 50),
	    new Rectangle(50, 150, 20, 30),
	    new Rectangle(30, 10, 10, 5),
	    new Rectangle(72, 210, 20, 40)};
	
	 */
	/**
	 * connections (35,10) (430,300)
	 * @return 
	 */
	
	public void initConnection(){
		addConnection(0,1);
		addConnection(2,3);
	 
		
	}
	
	//TODO: NO CONNECTION REPETIVE ON ONE RECT IS ALLOWED, CHANGE IT FOR BETTER TESTING
	private void addConnection(int idA, int idB){
	
		Rectangle start = testObstacleArr[idA];
		Rectangle end = testObstacleArr[idB];
		Point startP, endP;
		
		if( testObstacleArr[idA].getLeft() < testObstacleArr[idB].getLeft()){

			start = testObstacleArr[idA];
			end = testObstacleArr[idB];

		} else {
			
			end = testObstacleArr[idA];
			start = testObstacleArr[idB];
		}
		
		startP = new Point(start.getRight(), start.getUpper() - start.getHeight()/2);
		endP = new Point(end.getRight(), end.getUpper() - end.getHeight()/2);
		testConnections.add( new Connection(startP,endP));
	}
	
	   // Constructor to set up the GUI components and event handlers
   public initTest() {
	   
	   initConnection();
	   
	   JPanel btnPanel = new JPanel(new FlowLayout());
	      JButton btnAStar = new JButton("A STAR");
	      btnPanel.add(btnAStar);
	      btnAStar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			
			
			}
	   
	      });
	   
      canvas = new DrawCanvas();    // Construct the drawing canvas
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
 
      // Set the Drawing JPanel as the JFrame's content-pane
      Container cp = getContentPane();
      cp.add(canvas);
      cp.add(btnPanel, BorderLayout.SOUTH);

      // or "setContentPane(canvas);"
 
      setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
      pack();              // Either pack() the components; or setSize()
      setTitle("TestMap");  // "super" JFrame sets the title
      setVisible(true);    // "super" JFrame show
   }
   
   public void initMap(){
    	   mMap = new MyMap();
		finder = new AStarPathFinder(mMap, 500, true);

		//iterate through all connection
		for(Connection con : testConnections){
		path = finder.findPath(mMap.getCellRowIdx(con.start.getX()),mMap.getCellColIdx(con.start.getY()),mMap.getCellRowIdx(con.end.getX()),mMap.getCellColIdx(con.end.getY()));
	
		//TODO: draw path on canvas!!!!!
		}
   }
 
   /**
    * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
    */
   private class DrawCanvas extends JPanel {
      // Override paintComponent to perform your own painting
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);     // paint parent's background
         setBackground(Color.WHITE);  // set background color for this JPanel
 
      mMap.draw(g);

      }
   }
 
   // The entry main method
   public static void main(String[] args) {
      // Run the GUI codes on the Event-Dispatching thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new initTest(); // Let the constructor do the job
         }
      });
   }
}