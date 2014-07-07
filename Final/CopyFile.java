import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class CopyFile {
		
		public static void main (String[] args) {
			
			CopyFile fCopy = new CopyFile();
			BufferedReader bReader = null;
			try {
				System.out.print ("���� ���1 �Է�: ");
				bReader = new BufferedReader (new InputStreamReader(System.in));
				String beforePath = bReader.readLine();				
				
				System.out.print ("���� ���2 �Է�: ");
				bReader = new BufferedReader (new InputStreamReader(System.in));
				String afterPath = bReader.readLine();
				
				fCopy.copyFile(beforePath, afterPath);
			}
			catch (IOException ioe) { ioe.printStackTrace(); }
			catch (Exception e) { e.printStackTrace(); }
			finally {
				if (bReader != null) {
					try { bReader.close(); }
					catch (IOException ioe) {ioe.printStackTrace(); }
				}
			}			
		}


		public void copyFile (String beforePath, String afterPath){
			File beforeFile = new File (beforePath);
			BufferedReader bReader = null;
			BufferedWriter bWriter = null;
			
			try {
				if (!beforeFile.isDirectory()) {
					bWriter = new BufferedWriter (new FileWriter (afterPath+File.separator+beforeFile.getName()));
					bReader = new BufferedReader (new FileReader (beforePath));
					
					String line;
					while ( (line=bReader.readLine()) != null ) {
						bWriter.write(line);
						bWriter.newLine();
					}
					System.out.println ("���� ����");
				}
				else {
					System.out.println ("���� ���� : ���丮��");
				}
				
			}
			catch (FileNotFoundException fnfe) { 
				System.out.println ("���� ���� : �ش����� ã�� ����");
			}
			catch (IOException ioe) { ioe.printStackTrace(); }
			catch (Exception e) { e.printStackTrace(); }
			finally {
				
				if (bReader != null) {
					try { bReader.close(); }
					catch (IOException ioe) {ioe.printStackTrace(); }
				}
		
				if (bWriter != null) {
					try { bWriter.close(); }
					catch (IOException ioe) {ioe.printStackTrace(); }
				}

			}
		}
	}