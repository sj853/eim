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

/**
 * 
 * @author element
 *XML解析类
 */
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
	
	/**
	 * 得到属性name=X的type节点下子节点
	 * @param name 属性值
	 * @return 子节点内容的数组
	 */
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
	
	/**
	 * 得到节点的个数
	 * @return
	 */
	public int getElementSize() {
		return ElementSize;
	}
	
	
	
}	
