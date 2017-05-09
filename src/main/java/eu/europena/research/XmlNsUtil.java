package eu.europena.research;

import java.util.HashMap;
import java.util.Map;

public class XmlNsUtil {
	
    public static final String ORE="http://www.openarchives.org/ore/terms/";
    public static final String OWL="http://www.w3.org/2002/07/owl#";
	public static final String RDF="http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	public static final String SKOS="http://www.w3.org/2004/02/skos/core#";
	public static final String DCTERMS="http://purl.org/dc/terms/";
	public static final String WGS84_POS="http://www.w3.org/2003/01/geo/wgs84_pos#";
	public static final String RDAGRP2="http://rdvocab.info/ElementsGr2/";
	public static final String FOAF="http://xmlns.com/foaf/0.1/";
	public static final String EDM="http://www.europeana.eu/schemas/edm/";
	public static final String DC= "http://purl.org/dc/elements/1.1/";

	
	public static final Map<String, String> xpathEdmPrefixMap=new HashMap<String, String>() {{
		put("edm", EDM);
		put("dc", DC);
		put("dcterms", DCTERMS);
		put("ore", ORE);
		put("skos", SKOS);
	}}; 
	

}
