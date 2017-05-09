package eu.europena.research.newspapers;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class DataProvider extends AbstractDumpEntity {

	public DataProvider(File homeFolder) {
		super(homeFolder);
	}

	public DataProvider(String label) {
		super(label);
	}
	
	public List<Title> listTitles(){
		List<Title> ret=new ArrayList<>();
		for(File titleFolder : homeFolder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		})) {
			ret.add(new Title(this, titleFolder));
		}
		return ret;
	}	
}
