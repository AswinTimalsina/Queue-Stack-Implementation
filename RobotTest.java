/**
   Driver class to test the functionality of the Robot class to be
   developed as a solution to Programming Assignment 3, Fall 2018.
   
   @author Dr. J. Cordova
*/
public class RobotTest
{
	public static void main(String[] args)
	{
		Robot r2D2 = new Robot("a,dcb,fe");
		System.out.println(r2D2.readActions("robot0.txt"));
      System.out.println(r2D2);
      System.out.println();
      Robot c_3PO = new Robot("a,dcb,fe");
		System.out.println(c_3PO.readActions("robot1.txt"));
      System.out.println(c_3PO);
	}
}
