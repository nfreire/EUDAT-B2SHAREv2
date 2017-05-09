package eu.europena.research.newspapers;

import java.io.File;
import java.io.FileFilter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDumpEntity {

	File homeFolder;
	String label;

	protected AbstractDumpEntity(String label) {
		super();
		this.label = label;
	}

	protected AbstractDumpEntity(File homeFolder) {
		this(decodeFilename(homeFolder.getName()));
		this.homeFolder=homeFolder;
	}

	public String getLabel() {
		return label;
	}

	protected static String encodeFilename(String filename) {
		try {
			return URLEncoder.encode(filename, "UTF8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UTF8 not supported: "+e.getMessage(), e);
		}
	}
	
	protected static String decodeFilename(String filename) {
		try {
			return URLDecoder.decode(filename, "UTF8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UTF8 not supported: "+e.getMessage(), e);
		}
	}
}
