package eu.europena.research.newspapers;

import java.io.File;

public class Issue extends AbstractDumpEntity {
	Title title;

	public Issue(String date) {
		super(date);
	}

	public Issue(Title title, File edmXmlFile) {
		super(decodeFilename(edmXmlFile.getName().substring(0,10)));
		this.homeFolder = edmXmlFile.getParentFile();
		this.title = title;
	}

	public File getEdmXml() {
		return new File(homeFolder, encodeFilename(label) + ".edm.xml");
	}

	public File getEdmJson() {
		return new File(homeFolder, encodeFilename(label) + ".edm.jsonld");
	}

	public File getFulltextAltoZipArchive() {
		return new File(homeFolder, encodeFilename(label) + ".alto.zip");
	}

	public File getCmdiXml() {
		return new File(homeFolder, encodeFilename(label) + ".edm.cmdi.xml");		
	}
	
	
}
