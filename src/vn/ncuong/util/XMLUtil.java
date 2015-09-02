package vn.ncuong.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.xml.Log4jEntityResolver;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLUtil {
	private static XmlOptions xmlOptions = new XmlOptions();
	static {
		xmlOptions.setSavePrettyPrint();
		xmlOptions.setSavePrettyPrintIndent(4);
		xmlOptions.setUseDefaultNamespace();
	}

	/**
	 * Convert Xmlbeans object to string
	 * @param xmlObject
	 * @return
	 * @throws IOException
	 */
	public static String convertXmlObjectToString(XmlObject xmlObject) throws IOException {
		String xmlStr = null;
		ByteArrayOutputStream outputStream = null;
		try {
			outputStream = new ByteArrayOutputStream();
			xmlObject.save(outputStream, xmlOptions);
			xmlStr = outputStream.toString();
		} finally {
			IOUtils.closeQuietly(outputStream);
		}
		return xmlStr;
	}
	public static Element parseXML(InputStream xmlIS) throws DocumentException {
		return parseXML(xmlIS, false);
	}

	public static Element parseXML(InputStream xmlIS, boolean validation) throws DocumentException {
		SAXReader reader = new SAXReader();
		reader.setValidation(validation);
		Document document = reader.read(xmlIS);
		return document.getRootElement();
	}
	
	public static final String PATH_SEPARATOR = "/";

	@SuppressWarnings("unchecked")
	public static List<Element> getChildElementsByPath(Element root, String path) {
		if (path.startsWith(PATH_SEPARATOR)) {
			path = path.substring(1);
		}
		if (path.endsWith(PATH_SEPARATOR)) {
			path = path.substring(0, path.length());
		}
		if (path.indexOf(PATH_SEPARATOR) <= 0) {
			return root.elements(path);
		}
		int i = path.indexOf("/");
		String childName = path.substring(0, i);
		if (root.getName().equals(childName)) {
			return getChildElementsByPath(root, path.substring(i));
		}

		List<Element> childList = root.elements(childName);
		if (childList == null || childList.size() <= 0) {
			return null;
		}
		List<Element> result = new ArrayList<Element>();
		for (Object object : childList) {
			List<Element> list = getChildElementsByPath((Element) object, path.substring(i));
			if(list!=null) {
				result.addAll(list);
			}
		}
		return result;
	}
	public static Element parseXMLLog4j(InputStream xmlIS, boolean validation) throws DocumentException {
		SAXReader reader = new SAXReader();
		reader.setValidation(validation);
		reader.setEntityResolver(new Log4jEntityResolver());
		Document document = reader.read(xmlIS);
		return document.getRootElement();
	}
}
