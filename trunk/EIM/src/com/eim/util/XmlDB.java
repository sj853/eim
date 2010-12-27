package com.eim.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class XmlDB {
	
	public Document document;
	private int ElementSize;
	public File file;
	public  final OutputFormat format = OutputFormat.createPrettyPrint();
	public SAXReader reader;
	public Element rootElem;
	public XMLWriter writer; 
	
	public XmlDB(){
		reader = new SAXReader();
		reader.setEncoding("UTF-8");
		try {
			document = reader.read(PropertiesUtil.configPath+"basicinfo.xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		rootElem = document.getRootElement();
	}
	
	public ArrayList<String> getElements(String name){
		ArrayList<String> vals = new ArrayList<String>();
		String xpath ="/root/type[@name='"+name+"']/level";
		List<Element> children = new ArrayList<Element>();
		children = document.selectNodes(xpath);
	    this.ElementSize = children.size();
	    for (Element element : children) {
			vals.add(element.getData().toString());
		}
	    return vals;
	}
	
	
	public int getElementSize() {
		return ElementSize;
	}
	
	
	
}	
