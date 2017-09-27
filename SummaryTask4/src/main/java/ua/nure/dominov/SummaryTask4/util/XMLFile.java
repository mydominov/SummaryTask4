/**
 * 
 */
package ua.nure.dominov.SummaryTask4.util;

/**
 * @author calango
 *
 */
public class XMLFile {
	
	/**
	 * 
	 */
	private String fileName;
	
	/**
	 * 
	 */
	private byte[] file;
	
	/**
	 * @param fileName
	 * @param file
	 */
	public XMLFile(final String fileName, final byte[] file) {
		this.fileName = fileName;
		this.file = file;
	}

	/**
	 * @return the fileName
	 */
	public final String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public final void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the file
	 */
	public final byte[] getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public final void setFile(final byte[] file) {
		this.file = file;
	}
	
}
