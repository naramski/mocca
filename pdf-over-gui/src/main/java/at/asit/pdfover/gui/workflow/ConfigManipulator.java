/*
 * Copyright 2012 by A-SIT, Secure Information Technology Center Austria
 *
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * http://joinup.ec.europa.eu/software/page/eupl
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 */
package at.asit.pdfover.gui.workflow;

import java.io.IOException;
import java.util.Locale;

import at.asit.pdfover.signator.BKUs;
import at.asit.pdfover.signator.SignaturePosition;

/**
 * 
 */
public interface ConfigManipulator {
	
	/**
	 * The bku config parameter
	 */
	public static final String BKU_CONFIG = "BKU"; //$NON-NLS-1$
	
	/**
	 * The value for the Singature position in the configuration file
	 * values for this entry are:
	 * 
	 * x=vx;y=vy;p=vp or auto
	 * 
	 * vx:= float value
	 * vy:= float value
	 * vp:= integer value
	 */
	public static final String SIGNATURE_POSITION_CONFIG = "SIGNATURE_POSITION"; //$NON-NLS-1$
	
	/**
	 * This signature placeholder transparency config parameter (0-255)
	 */
	public static final String  SIGNATURE_PLACEHOLDER_TRANSPARENCY_CONFIG = "SIGNATURE_PLACEHOLDER_TRANSPARENCY"; //$NON-NLS-1$
	
	/**
	 * The mobile number config parameter
	 */
	public static final String MOBILE_NUMBER_CONFIG = "MOBILE_NUMBER"; //$NON-NLS-1$
	
	/**
	 * The signature note config parameter
	 */
	public static final String SIGNATURE_NOTE_CONFIG = "SIGNATURE_NOTE"; //$NON-NLS-1$
	
	
	/**
	 * Mobile bku url config parameter
	 */
	public static final String MOBILE_BKU_URL_CONFIG = "MOBILE_BKU_URL"; //$NON-NLS-1$
	
	/**
	 * The emblem config parameter
	 */
	public static final String EMBLEM_CONFIG = "EMBLEM"; //$NON-NLS-1$
	
	/**
	 * The locale config parameter
	 */
	public static final String LOCALE_CONFIG = "LOCALE"; //$NON-NLS-1$
	
	/**
	 * The locale config parameter
	 */
	public static final String SIGN_LOCALE_CONFIG = "SIGNLOCALE"; //$NON-NLS-1$
	
	/**
	 * The proxy host config parameter
	 */
	public static final String PROXY_HOST_CONFIG = "PROXY_HOST"; //$NON-NLS-1$
	
	/**
	 * The proxy port config parameter
	 */
	public static final String PROXY_PORT_CONFIG = "PROXY_PORT"; //$NON-NLS-1$
	
	/**
	 * The output folder config parameter
	 */
	public static final String OUTPUT_FOLDER_CONFIG = "OUTPUT_FOLDER"; //$NON-NLS-1$
	
	/**
	 * Sets the default bku type
	 * @param bku the bku type
	 */
	public void setDefaultBKU(BKUs bku);
	
	/**
	 * Sets the default signature position
	 * 
	 * @param signaturePosition the default signature position
	 */
	public void setDefaultSignaturePosition(SignaturePosition signaturePosition);
	
	/**
	 * Sets the signature placeholder transparency
	 * 
	 * @param transparency the signature placeholder transparency
	 */
	void setPlaceholderTransparency(int transparency);
	
	/**
	 * Sets the default mobile number
	 * @param number the default mobile number
	 */
	public void setDefaultMobileNumber(String number);
	
	/**
	 * Sets the default password
	 * @param password the default password
	 */
	public void setDefaultPassword(String password);
	
	/**
	 * Sets the default emblem
	 * 
	 * @param emblem the default emblem
	 */
	public void setDefaultEmblem(String emblem);
	
	/**
	 * Sets the proxy host
	 * @param host the proxy host
	 */
	public void setProxyHost(String host);
	
	/**
	 * Sets the proxy port
	 * 
	 * @param port the proxy port
	 */
	public void setProxyPort(int port);
	
	/**
	 * Sets the configuration file
	 * @param configurationFile
	 */
	public void setConfigurationFile(String configurationFile);
	
	/**
	 * Sets the default output folder
	 * @param outputFolder the default output folder
	 */
	public void setDefaultOutputFolder(String outputFolder);
	
	/**
	 * Sets the signature note text
	 * @param note the signature note text
	 */
	public void setSignatureNote(String note);
	
	/**
	 * Saves the current configuration to the current configuration file
	 * @throws IOException 
	 */
	public void saveCurrentConfiguration() throws IOException;
	
	/**
	 * Sets the locale to be used!
	 * @param locale the locale
	 */
	public void setLocale(Locale locale);
	
	/**
	 * Sets the locale to be used!
	 * @param locale the locale
	 */
	public void setSignLocale(Locale locale);
}
