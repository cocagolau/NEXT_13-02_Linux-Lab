import java.util.Random;


public class Sector extends Thread{
	
	static long innerPoints = 0;
	static long totalPoints = 500000000;
	static int numOfThreads = 4;
	static long pointsPerThread = totalPoints / numOfThreads; 
	
	public void run() {
		long subInnerPoints = 0;
		Random random = new Random();
		float x, y;
		for (long i=0; i<Sector.pointsPerThread; i++) {
			x = random.nextFloat();
			y = random.nextFloat();
			if (Math.sqrt(x*x + y*y) <= 1.0)
				subInnerPoints++;
		}
		
		synchronized (this) {
			innerPoints = innerPoints + subInnerPoints;
		}
	}
	
	public static void main (String[] args) throws InterruptedException {
		Sector sector = new Sector();
		Thread[] thread = new Thread[Sector.numOfThreads];
		
		long startTime = System.currentTimeMillis();
		for (int i=0; i<Sector.numOfThreads; i++) {
			thread[i] = new Thread(sector);
			thread[i].start();
		}
		
		for (int i=0; i<Sector.numOfThreads; i++)
			thread[i].join();
		
		long time = System.currentTimeMillis() - startTime;
		System.out.printf ("PI/4: %.3f [%.3fÃÊ]\n", (float)Sector.innerPoints / (float)Sector.totalPoints, (float)time/1000);
	}
}
