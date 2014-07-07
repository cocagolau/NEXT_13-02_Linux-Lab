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
				System.out.print ("파일 경로1 입력: ");
				bReader = new BufferedReader (new InputStreamReader(System.in));
				String beforePath = bReader.readLine();				
				
				System.out.print ("파일 경로2 입력: ");
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
					System.out.println ("복사 성공");
				}
				else {
					System.out.println ("복사 실패 : 디렉토리임");
				}
				
			}
			catch (FileNotFoundException fnfe) { 
				System.out.println ("복사 실패 : 해당파일 찾지 못함");
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