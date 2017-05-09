package eu.europena.research.newspapers;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class Title extends AbstractDumpEntity {
	DataProvider dataProvider;

	public Title(String title) {
		super(title);
	}

	protected Title(DataProvider dataProvider, File titleFolder) {
		super(titleFolder);
		this.dataProvider = dataProvider; 
	}

	
	public List<Issue> listIssues(){
		List<Issue> ret=new ArrayList<>();
		for(File issueFiler : homeFolder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return !pathname.isDirectory() && pathname.getName().matches("^\\d\\d\\d\\d-\\d\\d-\\d\\d\\.edm\\.xml");
			}
		})) {
			ret.add(new Issue(this, issueFiler));
		}
		return ret;
	}

	public File getEdmXml() {
		return new File(homeFolder, "newspaper_title_metadata.edm.xml");
	}

	public File getEdmJson() {
		return new File(homeFolder, "newspaper_title_metadata.edm.jsonld");
	}

	public File getCmdiXml() {
		return new File(homeFolder, "newspaper_title_metadata.edm.cmdi.xml");
	}

	public File getFulltextAltoZipArchive() {
		return new File(homeFolder, homeFolder.getName()+".alto.zip");
	}
}

