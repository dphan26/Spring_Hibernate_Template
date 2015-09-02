package vn.ncuong.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.util.StringUtils;


public class Lib {
	protected static final Log log = LogFactory.getLog(Lib.class);
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	public static final String LOG_PART_SEPARATOR = "===================================================================";
	public static final String LOG_LINE_SEPARATOR = "----------------------------------------------------------";
	private static final String TIMESTAMP_PATTERN = "yyyyMMdd-HHmmss-SSS";
	private static int MAX_INDEX = 1000;
	private static final int BUFFER_SIZE = 10000;
	public static final String KEY_SEPARATOR = "~";
	public static final Map<String, String> NUMBER_ORDERNARY_MAP = new HashMap<String, String>();

	
	static {
		NUMBER_ORDERNARY_MAP.put("1", "first");
		NUMBER_ORDERNARY_MAP.put("2", "second");
		NUMBER_ORDERNARY_MAP.put("3", "third");
		NUMBER_ORDERNARY_MAP.put("4", "fourth");
		NUMBER_ORDERNARY_MAP.put("5", "fifth");
		NUMBER_ORDERNARY_MAP.put("6", "sixth");
		NUMBER_ORDERNARY_MAP.put("7", "seventh");
		NUMBER_ORDERNARY_MAP.put("8", "eighth");
		NUMBER_ORDERNARY_MAP.put("9", "nineth");
	}
	

	public static void closeEntityManager(EntityManager em) {
		if(em!=null) {
			try {
				em.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Return true if data is null or empty
	 * @param data
	 * @return
	 */
	public static boolean isNullOrEmpty(String data) {
		return data==null || data.isEmpty();
	}
	
	public static String getProjectPath(String projectName){
		String buildPath = Lib.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String projectPath = buildPath;
		
		int i = projectPath.lastIndexOf(projectName);
		if (i > 0) {
			projectPath = projectPath.substring(0, i + projectName.length());
		}
		return projectPath;
	}
	
	/**
	 * Save given inputStream to outputFile
	 * If outputFile exists, the old content will be overwritten by inputStream
	 * If directory hierarchy of outputFile does not exist, it will be created
	 * @param inputStream
	 * @param outputFile
	 */
	public static void saveInputStreamToFile(InputStream inputStream, File outputFile){
		OutputStream output = null;
		try {
			File parentDir = outputFile.getParentFile();
			if(!parentDir.exists()){
				parentDir.mkdirs();
			}
			output = new FileOutputStream(outputFile, false);
			IOUtils.copy(inputStream, output);
		} catch (Exception e) {
			throw new RuntimeException("Failed to save InputStream to file '" + outputFile.getAbsolutePath() + "'", e);
		} finally {
			IOUtils.closeQuietly(output);
		}
	}
	
	public static final String getErrorMessage(HttpServletRequest request){
    	Object exception = request.getAttribute(WebAttributes.ACCESS_DENIED_403);
    	if(exception instanceof AccessDeniedException) {
    		AccessDeniedException deniedException = (AccessDeniedException) exception;
    		return deniedException.getMessage();
    	}
		return convertToString(exception);
	}
	
	public static Map<String, Object> buildParamsMap(String name, Object value){
		return buildParamsMap(new String[] {name}, new Object[] {value});
	}
	
	/**
	 * Helper method used in DAO to build params map
	 * @param names
	 * @param values
	 * @return
	 */
	public static Map<String, Object> buildParamsMap(String[] names, Object[] values){
		if(names==null || names.length==0 || values==null) {
			return Collections.EMPTY_MAP;
		}
		if(names.length != values.length) {
			throw new RuntimeException("Names and values must have the same size");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < names.length; i++) {
			map.put(names[i], values[i]);
		}
		return map;
	}
	
	public static boolean checkReadWritePermission(String directory) {
		if(directory==null || directory.isEmpty()) {
			throw new RuntimeException("Null or empty directory found");
		}
		File dir = new File(directory);
		if(!dir.exists()) {
			throw new RuntimeException("Directory '" + directory + "' does not exist");
		}
		if(!dir.isDirectory()) {
			throw new RuntimeException("Path '" + directory + "' is not a directory");
		}
		File testFile = new File(directory + "/Test.txt");
		try {
			Lib.createEmptyFile(testFile.getAbsolutePath());
		} catch (Exception e) {
			throw new RuntimeException("Unable to write to directory '" + directory + "'. Please check permissions", e);
		}
		boolean isDeleted = testFile.delete();
		if(!isDeleted) {
			throw new RuntimeException("Unable to delete from directory '" + directory + "'. Please check permissions");
		}
		return true;
	}
	
	public static String initLogFromXMLForClass(String logCfgFile, Class clazz) {
		String logFilePath = null;
		InputStream inStream = null;
		try {
			URL logConfigURL = clazz.getResource(logCfgFile);
			DOMConfigurator.configure(logConfigURL);
			inStream = clazz.getResourceAsStream(logCfgFile);
			logFilePath = LogUtil.getLogFilePathFromXml(inStream);
		} catch (RuntimeException e) {
			System.err.println("Could not find log file path from " + logCfgFile);
		} catch (Exception e) {
			System.err.println("Failed to configure log4j from file " + logCfgFile);
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inStream);
		}
		return logFilePath;
	}

	public static String getFileExtension(File file) {
		String name = file.getName();
		int i = name.lastIndexOf(".");
		if(i>=0) {
			return name.substring(i+1);
		}
		return "";
	}
	
	public static String[] readFromConsole(String[] labels, String[] defaultValues){
		List<String> list = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < labels.length; i++) {
			String title = labels[i];
			if(defaultValues[i]!=null) {
				title = labels[i] + " (default is '" + defaultValues[i].trim() + "', hit Return to accept): ";
			}
			System.out.println(title);
			String value = scanner.nextLine().trim();
			if(value.isEmpty()) {
				value = defaultValues[i]!=null?defaultValues[i].trim():"";
			}
			list.add(value);
		}
		return list.toArray(new String[list.size()]);
	}

	public static String[] readFromConsole(String[] labels){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < labels.length; i++) {
			String value = "";
			System.out.println(labels[i]);
			do {
				try {
					value = reader.readLine();
					value = value.trim();
				} catch (IOException e) {
					value = "";
				}
			} while (value.equals(""));
			list.add(value);
		}
		return list.toArray(new String[list.size()]);
	}

	public static String convertToString(Object object) {
		if(object==null) {
			return null;
		}
		String data = null;
		if(object instanceof String) {
			data = (String) object;
		} else {
			try {
				data = ToStringBuilder.reflectionToString(object);
			} catch (Exception e) {
				data = object.toString();
			}
		}
		return data;
	}
	
	public static boolean isEqual(String data1, String data2) {
		if(data1==null && data2==null) {
			return true;
		}
		if(data1!=null) {
			return data1.equals(data2);
		}
		if(data2!=null) {
			return data2.equals(data1);
		}
		return false;
	}
	
	public static int unzip(String zipFilePath, String location) {
		int totalFiles = 0;
		File file = new File(zipFilePath);
		if(file.isDirectory() || file.exists()==false) {
			log.error("Zip file does not exist: '" + file.getAbsolutePath() + "'");
		} else {
			log.info(Lib.LOG_PART_SEPARATOR);
			log.info("START UN-ZIPPING FILE '" + file.getAbsolutePath() + "'...");
			InputStream zipIS = null;
			try {
				zipIS = new FileInputStream(file);
				totalFiles = Lib.unzip(zipIS, location);
			} catch (Exception e) {
				throw new RuntimeException("Failed to unzip file '" + file.getAbsolutePath() + "' to '" + location + "'", e);
			}
			finally {
				IOUtils.closeQuietly(zipIS);
			}
			log.info("FINISHED UN-ZIPPING FILE '" + file.getName() + "' successfully. Total unzipped files are " + totalFiles);
			log.info(Lib.LOG_PART_SEPARATOR);
		}
		return totalFiles;
	}
	
	/**
	 * This method extracts files (NOT folders) of a zip file to location
	 * @param zipIS
	 * @param location
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static int unzip(InputStream zipIS, String location) {
		File targetFolder = Lib.createFolders(location, true);
		FileOutputStream fos = null;
		ZipInputStream zipInputStream = null;
		int totalFiles = 0;
		try {
			zipInputStream = new ZipInputStream(zipIS);
			for (ZipEntry entry = zipInputStream.getNextEntry(); entry!=null; ) {
				File entryFile = new File(targetFolder.getAbsolutePath() + File.separator + entry.getName());
				fos = new FileOutputStream(entryFile, false);
				BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int length = zipInputStream.read(buffer, 0, BUFFER_SIZE);
				for (;length!=-1;) {
					dest.write(buffer, 0, length);
					length = zipInputStream.read(buffer, 0, BUFFER_SIZE);
				}
				dest.close();
				log.info("Unziped entry to '" + entryFile.getAbsolutePath() + "' successfully!");
				totalFiles++;
				entry = zipInputStream.getNextEntry();
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to unzip InputStream '" + zipIS + "' to '" + location + "'", e);
		}
		finally {
			IOUtils.closeQuietly(zipInputStream);
			IOUtils.closeQuietly(fos);
		}
		return totalFiles;
	}
	
	public static String trim(String data) {
		if(data==null) {
			return data;
		}
		return data.trim();
	}
	
	
	/**
	 * This method checks if a file existed, if yes, add an index to the end of the name
	 * ie, D:\Gateway\
	 * @param parent
	 * @param name
	 * @return
	 */
	public synchronized static File getIndexedFileIfExisted(String parent, String name) {
		String filePath = parent + File.separator + name;
		File file = new File(filePath);
		if (file.exists() == false) {
			return file;
		}
		/**
		 * Already existed, we add an index to the end
		 */
		String extension = "";
		boolean isFile = file.isFile();
		if(isFile) {
			String fileName = file.getName();
			int offset = fileName.lastIndexOf(".");
			if(offset >= 0) {
				filePath = parent + File.separator + fileName.substring(0, offset);
				extension = fileName.substring(offset);
			}
		}
		for (int i = 1; i < MAX_INDEX; i++) {
			String index = Integer.toString(1000 + i);
			String indexedFilePath = filePath + "_" + index.substring(1) + extension;
			file = new File(indexedFilePath);
			if (file.exists() == false) {
				return file;
			}
		}
		throw new RuntimeException("Could not create folder for '" + filePath + "': exceed maximum index " + MAX_INDEX);
	}
	
	/**
	 * This method will add an index to the end of the name and make sure that the indexed file name doesn't exist
	 * E.g. QuestionM~Gateway~Closing_11-Image.png --> QuestionM~Gateway~Closing_11-Image001.png
	 * 
	 * @param parent
	 * @param name
	 * @return
	 */
	public synchronized static File addIndexUntilIfExisted(String parent, String name) {
		String filePath = parent + File.separator + name;
		File file = new File(filePath);

		String[] splitedFileName = splitFileToNameAndExtension(file);
		String fileNameNoExt = splitedFileName[0];
		String extension = splitedFileName[1];
		
		filePath = parent + File.separator + fileNameNoExt;

		for (int i = 1; i < MAX_INDEX; i++) {
			String index = Integer.toString(1000 + i);
			String indexedFilePath = filePath + index.substring(1) + extension;
			file = new File(indexedFilePath);
			if (!file.exists()) {
				return file;
			}
		}
		
		throw new RuntimeException("Could not create folder for '" + filePath + "': exceed maximum index " + MAX_INDEX);
	}
	
	/**
	 * Split file to name and extension. E.g. "document.doc" --> ["document", ".doc"], "document" --> ["document", ""]
	 * 
	 * @param file
	 * @return
	 */
	public static String[] splitFileToNameAndExtension(File file) {
		String name = file.getName();
		String extension = "";
		String fileName = file.getName();
		int offset = fileName.lastIndexOf(".");
		if (offset >= 0) {
			name = fileName.substring(0, offset);
			extension = fileName.substring(offset);
		}
		
		return new String[] { name, extension };
	}
	
	/**
	 * This method keeps only letters or digits to make it a valid Java identifier
	 * For example, input = "9Document Name" => output = "DocumentName"
	 * @param string
	 * @return
	 */
	public static String makeValidJavaIdentifier(String string) {
		if(string==null || string.length() <=0 ) {
			throw new RuntimeException("Null or empty data found: " + string);
		}
		char[] chars = string.toCharArray();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			if(buffer.length()==0) {
				if(Character.isLetter(chars[i])){
					buffer.append(chars[i]);
				}
			} else {
				if(Character.isLetterOrDigit(chars[i])){
					buffer.append(chars[i]);
				}
			}
		}
		return buffer.toString();
	}
	
	public static void sortByAlphabeOrder(File[] imageFiles, final boolean caseSensitive) {
		Arrays.sort(imageFiles, new Comparator<File>() {
			public int compare(File o1, File o2) {
				Collator collator = Collator.getInstance();
				String name1 = o1.getName();
				String name2 = o2.getName();
				if(caseSensitive==false) {
					collator.setStrength(Collator.PRIMARY);
				}
				int result = collator.compare(name1, name2);
				result = result == 0 ? -1 : result;
				return result;
			}
		});
	}

	/**
	 * Read a resource file from classpath
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public static String getFileContentFromClassPath(String name) throws IOException {
		InputStream inputStream = Lib.class.getResourceAsStream(name);
		if(inputStream==null) {
			throw new RuntimeException("Could not find resource '" + name + "' from classpath");
		}
		try {
			return getFileContent(inputStream);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
	}

	/**
	 * This method reads and returns content of a text file
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String getFileContent(String filePath) throws IOException {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(filePath));
			return getFileContent(inputStream);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
	}

	/**
	 * Read content of an InputStream
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static String getFileContent(InputStream inputStream) throws IOException {
		StringBuffer fileContent = new StringBuffer();
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		while ((line = in.readLine()) != null) {
			if(fileContent.length() > 0){
				fileContent.append(LINE_SEPARATOR);
			}
			fileContent.append(line);
		}
		return fileContent.toString();
	}

	public static boolean deleteFolder(String fullFolderPath) {
		if(fullFolderPath==null) {
			return true;
		}
		return deleteFolder(new File(fullFolderPath));
	}

	/**
	 * This method will delete all files and sub-folders of fullFolderPath
	 * @param fullFolderPath String
	 * @return boolean
	 */
	public static boolean deleteFolder(File folder) {
		if(folder==null) {
			return true;
		}
		boolean isDeleteOK = true;
		File[] files = folder.listFiles();
		if (files != null && files.length > 0) {
			for (int i = 0, j = files.length; i < j; i++) {
				File file = files[i];
				if (file.isFile()) {
					isDeleteOK = isDeleteOK && file.delete();
				} else {
					isDeleteOK = isDeleteOK && deleteFolder(file);
				}
			}
		}
		isDeleteOK = isDeleteOK && folder.delete();
		return isDeleteOK;
	}

	/**
	 * Copy content (files and subfolders) of a given folder
	 * Example:
	 *  + if we want to copy C:\temp\mydata to D:\projects so that we will have D:\projects\mydata, simply call:
	 *       copyFolder("C:\temp\mydata", "D:\projects");
	 * @param sourceFolder String
	 * @param destFolder String
	 * @return boolean
	 * @throws IOException
	 */
	public static boolean copyFolder(String sourceFolder, String destFolder) throws IOException {
		boolean isCopyOK = false;

		destFolder = destFolder + File.separator + new File(sourceFolder).getName();

		File targetFolder = new File(destFolder);

		//create destination folder if not existed
		if (!targetFolder.exists()) {
			isCopyOK = targetFolder.mkdirs();
		}

		File folder = new File(sourceFolder);
		File[] files = folder.listFiles();
		if (files != null && files.length > 0) {
			for (int i = 0, j = files.length; i < j; i++) {
				File file = files[i];
				String name = file.getName();
				if (file.isFile()) {
					String destFilePath = destFolder + File.separator + name;
					copyFile(file.getAbsolutePath(), destFilePath);
				} else {
					copyFolder(file.getAbsolutePath(), destFolder);
				}
			}
		}
		return isCopyOK;
	}
	
	public static boolean moveFile(String inputFilePath, String outputFilePath) throws FileNotFoundException, IOException {
		File inputFile = new File(inputFilePath);
		File outputFile = new File(outputFilePath);
		boolean isOK = moveFile(inputFile, outputFile);
		return isOK;
	}

	public static boolean moveFile(File inputFile, File outputFile) throws FileNotFoundException, IOException {
		boolean isOK = false;
		isOK = inputFile.renameTo(outputFile);
		if(!isOK || !outputFile.exists()){
			isOK = copyFile(inputFile, outputFile);
			isOK = isOK && inputFile.delete();
		}
		return isOK;
	}
	
	/**
	 * Copy a given file to a new place.
	 * @param inputFilePath
	 * @param outputFilePath
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static boolean copyFile(String inputFilePath, String outputFilePath) throws FileNotFoundException, IOException {
		File inputFile = new File(inputFilePath);
		File outputFile = new File(outputFilePath);
		boolean isOK = copyFile(inputFile, outputFile);
		return isOK;
	}

	public static boolean copyFile(File inputFile, File outputFile) throws FileNotFoundException, IOException {
		InputStream in = new FileInputStream(inputFile);
		OutputStream out = new FileOutputStream(outputFile);
		byte[] buf = new byte[100000];
        int len;
        try {
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } finally {
        	IOUtils.closeQuietly(in);
        	IOUtils.closeQuietly(out);
        }
        return true;
	}
	
	public static void createEmptyFile(String filePath) {
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(filePath, false);
			outputStream.write("".getBytes());
		} catch (Exception e2) {
			throw new RuntimeException("Failed to create file '" + filePath + "'", e2);
		}
		finally {
			IOUtils.closeQuietly(outputStream);
		}
	}

	public static boolean deleteIfExisted(String filePath) {
		if(filePath==null) {
			return false;
		}
		File file = new File(filePath);
		return deleteIfExisted(file);
	}

	public static boolean deleteIfExisted(File file) {
		if(file!=null && file.exists()) {
			try {
				FileUtils.forceDelete(file);
				return true;
			} catch (IOException e) {
				throw new RuntimeException("Could not delete file '" + file.getAbsolutePath() + "'", e);
			}
		}
		return false;
	}
	
	public static File moveFile(File srcFile, String destFolder) throws IOException {
		if(srcFile==null || destFolder==null) {
			return null;
		}
		File destFile = srcFile;
		if(srcFile.exists() && srcFile.isFile()) {
			destFolder = destFolder.trim();
			if(!destFolder.endsWith(File.separator)) {
				destFolder = destFolder + File.separator;
			}
			destFile = new File(destFolder + srcFile.getName());
			/**
			 * check to make sure srcFile and destFile are not the same
			 */
			boolean isTheSame = srcFile.getAbsolutePath().equals(destFile.getAbsolutePath());
			if(isTheSame) {
				//do nothing
			} else {
				FileUtils.copyFile(srcFile, destFile, true);
				FileUtils.forceDelete(srcFile);
			}
		}
		return destFile;
	}
	/**
	 * This method is used to get stack info like log4j
	 * @param e
	 * @return
	 */
	public static String getStackTrace(Throwable e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
	}
	
	public static String getAllMessages(Throwable e){
        String message = e.getMessage();
        Throwable cause = e.getCause();
        if(cause!=null) {
        	return message + " -> " + getAllMessages(cause);
        }
        return message;
	}
	
	public static File createFolders(String path, boolean checkCreated) {
		File folder = new File(path);
		if(!folder.exists()) {
			boolean isCreated = folder.mkdirs();
			if(checkCreated && !isCreated && !folder.exists()) {
				throw new RuntimeException("Failed to create folder '" + path + "'. Please check permission/symlink.");
			}
		}
		return folder;
	}
	
	public static long toFileSize(String value, long defaultValue) {
		if (value == null)
			return defaultValue;

		String numPart = value.trim().toUpperCase();
		long multiplier = 1;
		int index;

		if ((index = numPart.indexOf("KB")) != -1) {
			multiplier = 1024;
			numPart = numPart.substring(0, index);
		} else if ((index = numPart.indexOf("MB")) != -1) {
			multiplier = 1024 * 1024;
			numPart = numPart.substring(0, index);
		} else if ((index = numPart.indexOf("GB")) != -1) {
			multiplier = 1024 * 1024 * 1024;
			numPart = numPart.substring(0, index);
		}
		
		if (numPart != null) {
			try {
				return Long.valueOf(numPart).longValue() * multiplier;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return defaultValue;
	}

	public static String getTimeSpentText(long start) {
		return "Time spent is " + (System.currentTimeMillis() - start) + " millisecs";
	}

	public static String getNotNullValue(String errMsg, String value) {
		if(value==null || value.trim().length()==0) {
			throw new RuntimeException("Null or empty " + errMsg + ". Please double-check.");
		}
		return value.trim();
	}
	
	public static boolean deleteFolderIfEmpty(File folder) {
		if(folder!=null && folder.isDirectory()) {
			String[] items = folder.list();
			if(items==null || items.length==0) {
				return folder.delete();
			}
		}
		return false;
	}

	public static boolean isListNullOrEmpty (List l) {
		return (l == null || l.isEmpty());
	}
	
	public static long getLongValue(Object obj) {
		try {
			if (obj != null) {
				return Long.parseLong(obj.toString().trim());
			}
		} catch (NumberFormatException numberFormatException) {
			log.warn("Error while formatting the Object to long value.");
		} catch (NullPointerException nullPointerException) {
			log.warn("Error while formatting the Object to long value.");
		}
		return 0l;
	}
	
	public static double getDoubleValue(Object obj) {
		try {
			if (obj != null) {
				return Double.parseDouble(obj.toString().trim());
			}
		} catch (NumberFormatException numberFormatException) {
			log.warn("Error while formatting the Object to double value.");
		} catch (NullPointerException nullPointerException) {
			log.warn("Error while formatting the Object to double value.");
		}
		return 0;
	}
	
	public static int getIntValue(Object obj) {
		try {
			if (obj != null) {
				return Integer.parseInt(obj.toString().trim());
			}
		} catch (Exception e) {
			log.warn("Error while formatting the Object to int value.");
		}
		return 0;
	}
	
	public static String getStringValue(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString().trim();
		}
	}
	
	public static boolean getBooleanValue(Object object) {
		if (object == null) {
			return false;
		}
		return BooleanUtils.toBoolean(object.toString());
	}
	
	public static final String getHostUrl(HttpServletRequest request) {
		StringBuffer redirectURL = new StringBuffer("");
		redirectURL.append(request.getScheme());
		redirectURL.append("://");
		redirectURL.append(request.getServerName());
		redirectURL.append(request.getServerPort() != 80 ? ":" + request.getServerPort() : "");
		return redirectURL.toString();
	}
	
	public static final String getSiteUrl(HttpServletRequest request) {
		return Lib.getHostUrl(request) + request.getContextPath();
	}
	
	public static List<Integer> convertStringToIntegerList(String s, String regex) {
		List<Integer> iList = new ArrayList<Integer> (); 
		try {
			if (StringUtils.hasText(s) && regex != null) {
				String[] sArr = s.split(regex);
				if (sArr.length > 0) {
					for (String sValue : sArr) {
						iList.add(Integer.parseInt(sValue.trim())); 
					}
					return iList;
				}
			}
		} catch (NumberFormatException e) {
			log.error("Failed to convert string [" + s + "] to integer list", e);
		}
		return iList;
	}
	
	public static String replaceNonASCIIString(String str) {
		return str.replaceAll("[^\\x00-\\x7F]", " ");
	}

	public static void close(Connection conn, CallableStatement storeProcStmt) {
		try {
			if (storeProcStmt !=null){
				storeProcStmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (conn !=null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	public static boolean getBoolParameter(Map paramMap, String parameterName) {
		boolean value = Lib.getBooleanValue(paramMap.get(parameterName));
		return value;
	}
	
	public static int getIntParameter(Map paramMap, String parameterName) {
		int value = Lib.getIntValue(paramMap.get(parameterName));
		return value;
	}
	
	public static String getStringParameter(Map paramMap, String parameterName) {
		String value = Lib.getStringValue(paramMap.get(parameterName));
		return value;
	}

	/**
	 * Write message into session to provide information to the browser
	 * @param request
	 * @param messageName
	 * @param messages
	 */
	public static void writeMessageIntoSession(HttpServletRequest request, String messageName, String messages) {
		request.getSession().setAttribute(messageName, messages);
	}
	
	/**
	 * Clear message out of session
	 * @param request
	 * @param messageName
	 */
	public static void clearMessageOutOfSession(HttpServletRequest request, String messageName) {
		request.getSession().setAttribute(messageName, "");
	}
	
	public static String getBuildPath() {
		String buildPath = Lib.class.getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		if (buildPath.endsWith("/")) {
			buildPath = buildPath.substring(0, buildPath.length() - 1);
		}
		int i = buildPath.lastIndexOf("/");
		if (i > 0) {
			buildPath = buildPath.substring(0, i);
		}
		return buildPath;
	}
	
	public static String getProjectPath() {
		String projectPath = Lib.getBuildPath();
		if (projectPath.endsWith("/")) {
			projectPath = projectPath.substring(0, projectPath.length() - 1);
		}
		int i = projectPath.lastIndexOf("/");
		if (i > 0) {
			projectPath = projectPath.substring(0, i);
		}
		
		return projectPath;
	}
	
	/**
	 * get db schema db from connection URL i.e. the jdbc:mysql://localhost:3306/drool_flow_db?zeroDateTimeBehavior=convertToNull URL
	 * will return drool_flow_db
	 * @param url
	 */
	public static String getDbSchemaFromUrl(String url) {
		String cleanUrl = url.substring(url.indexOf(":") + 1);
		URI uri = URI.create(cleanUrl);
		
		/**
		 * Path has format "/drools_flow_db"
		 */
		String path = uri.getPath();
		String dbSchemaName = path.substring(1);
		return dbSchemaName;
	}
	
	public static String getFullRequestUrl(HttpServletRequest request) {
		String uri = request.getScheme() + "://" +
	             request.getServerName() + 
	             ("http".equals(request.getScheme()) && request.getServerPort() == 80 || "https".equals(request.getScheme()) && request.getServerPort() == 443 ? "" : ":" + request.getServerPort() ) +
	             request.getRequestURI() +
	            (request.getQueryString() != null ? "?" + request.getQueryString() : "");
		return uri;
	}
	
	public static BigInteger getBigInteger(Object obj) {
		try {
			if (obj != null) {
				return new BigInteger(obj.toString().trim());
			}
		} catch (Exception e) {
			log.warn("Error while formatting the Object to BigInteger value.");
		}
		return null;
	}
	
	public static String[] semicolonDelimitedListToStringArray(String s) {
		if (!StringUtils.hasText(s)) {
			return new String[0];
		}
		return s.split(";");
	}

	public static Integer[] convertStringToIntegerArray(String s, String regex) {
		Integer[] iArray = null; 
		try {
			if (StringUtils.hasText(s) && regex != null) {
				String[] sArr = s.split(regex);
				if (sArr.length > 0) {
					iArray = new Integer[sArr.length];
					for (int i = 0; i < sArr.length; i++) {
						iArray[i] = Integer.parseInt(sArr[i].trim()); 
					}
					return iArray;
				}
			}
		} catch (NumberFormatException e) {
			log.error("Failed to convert string [" + s + "] to integer array", e);
		}
		return iArray;
	}
	
	public static String buildCompositeKeyForMap(String... keys) {
		return org.apache.commons.lang3.StringUtils.join(keys, KEY_SEPARATOR, 0, keys.length);
	}
	
	public static List<Integer> convertStringListToIntegerList(List<String> stringList) {
		List<Integer> rsList = new ArrayList<Integer>();
		for(String str : stringList) {
			try {
				rsList.add(Integer.parseInt(str)); 
			} catch (Exception e) {
				log.error("Failed to convert string [" + str + "] to integer.", e);
			}
		}
		return rsList;
	}
}
