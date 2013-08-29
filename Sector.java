import java.util.Random;


public class Sector extends Thread{
	
	static int innerPoint = 0;
	
	public void run() {
		Random random = new Random();
		Point point = new Point(random.nextDouble(), random.nextDouble());

		if (point.getDistance() <= 1) {
			synchronized (this) {
				innerPoint ++;
			}
		}
	}
	
	public static void main (String[] args) throws InterruptedException {
		
		long numOfThread = 100000;
		Sector sector = new Sector();
		Thread thread = null;
		
		for (long i=0; i<numOfThread; i++) {
			thread = new Thread(sector);
			thread.start();
			
		}
		thread.join();
		System.out.printf ("PI/4: %.3f \n", (double)Sector.innerPoint / numOfThread);
	}
	

}

class Point {
	double x,y;
	public Point (double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getDistance () {
		return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
	}
}
