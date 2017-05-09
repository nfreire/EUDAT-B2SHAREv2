package eu.europena.research.newspapers;

import java.io.File;
import java.io.FileFilter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class DumpRepository {

	File homeFolder;

	public DumpRepository(File homeFolder) {
		super();
		this.homeFolder = homeFolder;
	}
	
	
	public DumpRepository(String homeFolderPath) {
		this(new File(homeFolderPath));
	}


	public List<DataProvider> listDataProviders(){
		List<DataProvider> ret=new ArrayList<>();
		for(File providerFolder : homeFolder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		})) {
			ret.add(new DataProvider(providerFolder));
		}
		return ret;
	}
	

	
	

	
}
