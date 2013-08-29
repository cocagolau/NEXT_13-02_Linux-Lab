

import java.io.File;
import java.io.FileFilter;

public class TreeFileFilter implements FileFilter{

	@Override
	public boolean accept(File file) {
		return file.isFile();
	}
	

}
