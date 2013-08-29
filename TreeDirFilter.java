
import java.io.File;
import java.io.FileFilter;


public class TreeDirFilter implements FileFilter{

	@Override
	public boolean accept(File file) {
		return file.isDirectory();
	}

}
