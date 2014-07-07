
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class SearchFile {

	public static void main (String[] args) throws IOException{
		
		SearchFile tree = new SearchFile();
		String dirPath, fileName;
		boolean existFile = false;
		File dir;
	
		BufferedReader bReader = null;
		try {
			
			System.out.print ("1. 디렉토리 경로 입력: ");
			bReader = new BufferedReader (new InputStreamReader(System.in));
			dirPath = bReader.readLine();				
			
			System.out.print ("2. 파일 이름 입력: ");
			bReader = new BufferedReader (new InputStreamReader(System.in));
			fileName = bReader.readLine();
			
			
			dir = new File (dirPath);
			if(dir.exists()) {
				for (File f : dir.listFiles()) {
					if (existFile) break;
					if (f.getName().equals(fileName)) {
						existFile = true;
						tree.makeTree (0, dir);
					}
				}
				if (!existFile)
					System.out.println ("검색 실패: 해당 파일이 없음");	
			}
			else
				System.out.println ("검색 실패: 해당 디렉토리가 없음");
			

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

	
	
	private int makeTree(int depth, File parentFile) throws IOException {
		
		File[] dirs = parentFile.listFiles(new TreeDirFilter());
		File[] files = parentFile.listFiles(new TreeFileFilter());
		
		for (File dir : dirs) {
			printContents(depth, dir);
			depth = makeTree(++depth, dir);
		}
		
		for (File file : files) {
			printContents(depth, file);
		}
		System.out.println();
		return --depth;
		
	}

	private void printContents(int depth, File file) {
		if (file.isDirectory()) {
			for (int i=0; i<depth; i++)
				System.out.print("   ");
			System.out.println ("["+file+"]");
		}
		else {
			for (int i=0; i<depth; i++)
				System.out.print("   ");
			
			if (depth == 0)
				System.out.println (file);
			else
				System.out.println ("└"+file);
		}
	}

}
