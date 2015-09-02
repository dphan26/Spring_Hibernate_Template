package vn.ncuong.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.dom4j.Element;

import vn.ncuong.util.XMLUtil;

import vn.ncuong.constants.CommonConstants;

public class LogUtil {

	public static Logger getLogger(String loggerName, String appenderName,
			String logFilePath) throws IOException {
		return getLogger(loggerName, appenderName, logFilePath, false);
	}

	public static Logger getLogger(String loggerName, String appenderName,
			String logFilePath, boolean additivity) throws IOException {
		Logger logger = Logger.getLogger(loggerName);
		FileAppender appender = (FileAppender) logger.getAppender(appenderName);
		if (appender == null) {
			Layout layout = new PatternLayout(
					CommonConstants.LOG_MESSAGE_PATTERN);
			appender = new FileAppender(layout, logFilePath, true);
			appender.setName(appenderName);
			appender.setAppend(true);
			logger.addAppender(appender);
		} else {
			appender.setFile(logFilePath);
			appender.activateOptions();
		}

		logger.setAdditivity(additivity);

		return logger;
	}

	public static String getLogFilePathFromXML(String logConfigFilePath) {
		String logFilePath = null;
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(new File(logConfigFilePath));
			logFilePath = getLogFilePathFromXml(inStream);
		} catch (Exception e) {
			throw new RuntimeException("Failed get log file path from "
					+ logConfigFilePath, e);
		} finally {
			IOUtils.closeQuietly(inStream);
		}

		return logFilePath;
	}

	public static String getLogFilePathFromXml(InputStream inStream) {
		String logFilePath = null;
		try {
			Element objectRoot = XMLUtil.parseXMLLog4j(inStream, false);
			List propList = XMLUtil.getChildElementsByPath(objectRoot,
					"/appender/param");
			for (int j = 0; j < propList.size(); j++) {
				Element property = (Element) propList.get(j);
				String name = property.attributeValue("name");
				if ("File".equals(name)) {
					logFilePath = property.attributeValue("value");
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed get log file path", e);
		}

		if (logFilePath == null) {
			throw new RuntimeException("Could not find log file path");
		}

		return logFilePath;
	}

}
