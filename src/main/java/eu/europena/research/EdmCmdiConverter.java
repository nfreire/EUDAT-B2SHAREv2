package eu.europena.research;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.w3c.dom.Document;

public class EdmCmdiConverter {

	
	public static Document toCmdiMetadata(Document edmDom) {
		
		InputStream xsltStream = EdmCmdiConverter.class.getClassLoader().getResourceAsStream("edm2cmdi.xsl");
		Document xsltDom;
		try {
			xsltDom = XmlUtil.parseDom(new InputStreamReader(xsltStream, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		try {
			xsltStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		Document edmInternalDom = XmlUtil.transform(edmDom, xsltDom);
		return edmInternalDom;
	}

}