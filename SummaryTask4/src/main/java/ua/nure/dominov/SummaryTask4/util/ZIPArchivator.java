/**
 * 
 */
package ua.nure.dominov.SummaryTask4.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author calango
 *
 */
public final class ZIPArchivator {

	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			ZIPArchivator.class.getName());
	
	/**
	 * 
	 */
	private ZIPArchivator() {
		super();
	}
	
	/**
     * Compress the given directory with all its files.
     */
	public static byte[] zipFiles(final XMLFile[] xmlFiles) {
		byte[] result = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ZipOutputStream zos = new ZipOutputStream(baos)) {
				for (XMLFile xmlFile : xmlFiles) {
					ZipEntry entry = new ZipEntry(xmlFile.getFileName());
					entry.setSize(xmlFile.getFile().length);
					zos.putNextEntry(entry);
					zos.write(xmlFile.getFile());
					zos.closeEntry();
				}
			}
			result = baos.toByteArray();
		} catch (IOException e) {
			LOGGER.error("Issue in zipFiles(final XMLFile[] xmlFiles): " + e);
		}
		return result;
		
    }
    
}
