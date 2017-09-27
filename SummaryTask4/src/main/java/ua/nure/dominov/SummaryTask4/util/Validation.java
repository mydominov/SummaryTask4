/**
 * 
 */
package ua.nure.dominov.SummaryTask4.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.ResourceBundle;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author calango
 *
 */
public final class Validation {
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			Validation.class.getName());
	
	/**
	 * 
	 */
	private Validation() {
		super();
	}
	
	/**
	 * @param gRecaptchaResponse recapcha response
	 * @return recapcha is valid
	 */
	public static boolean verifyRecaptcha(final String gRecaptchaResponse) {
		try {
			if (gRecaptchaResponse == null || gRecaptchaResponse.equals("")) {
	            return false;
	        }
			final ResourceBundle resourceBundle = ResourceBundle.getBundle(
					FilesPathsStorage.RECAPCHAFILEPATH); 
			final URL obj = new URL(resourceBundle.getString("url"));
			String inputLine;
			final StringBuffer response = new StringBuffer();
			final HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			final String postParams = "secret=" + resourceBundle.getString("secret") + "&response="
	                + gRecaptchaResponse;
			// Send post request
	        con.setDoOutput(true);
	        try (DataOutputStream dos = new DataOutputStream(
	        		con.getOutputStream())) {
	        	dos.writeBytes(postParams);
	            dos.flush();
	        }
	        try (BufferedReader in = new BufferedReader(new InputStreamReader(
	                con.getInputStream()))) {
	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	        }
	        try (JsonReader jsonReader = Json.createReader(
	        		new StringReader(response.toString()))) {
	        	JsonObject jsonObject = jsonReader.readObject();
	        	return jsonObject.getBoolean("success");
	        }
		} catch (Exception e) {
            LOGGER.error("Exception in validation: " + e);
            return false;
        }
	}
	
}
