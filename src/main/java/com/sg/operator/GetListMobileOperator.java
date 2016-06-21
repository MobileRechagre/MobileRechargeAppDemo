package com.sg.operator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



import com.sg.service.XMLParser;

import android.content.Context;


public class GetListMobileOperator {
	static final String KEY_SONG="item";
	public static final String KEY_TITLE = "name";
	public static final String opcode = "opcode";
	public static final String KEY_THUMB_URL ="thumb_url";
	Context ctx;
	ArrayList<HashMap<String, String>> mobile_operator_list;
	InputStream in=null;
	public GetListMobileOperator(Context ctx)
	{
		this.ctx=ctx;
		mobile_operator_list = new ArrayList<HashMap<String, String>>();
	}
	public ArrayList<HashMap<String, String>> getListMobileOperator(String xmlfileName)
	{ try
	{
		XMLParser parser = new XMLParser();
		in=ctx.getAssets().open(xmlfileName);
		Document doc = parser.getDomElement(in); // getting DOM element
		doc.getDocumentElement().normalize();
		NodeList nl = doc.getElementsByTagName(KEY_SONG);
		for (int i = 0; i< nl.getLength(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
			map.put(opcode, parser.getValue(e, opcode));
			map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));
			mobile_operator_list.add(map);
		}

		return mobile_operator_list;
	}
	catch(Exception e)
	{
		return null;
	}
	}

}
