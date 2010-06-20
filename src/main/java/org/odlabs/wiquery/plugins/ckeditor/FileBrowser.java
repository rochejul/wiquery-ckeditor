/*
 * Copyright (c) 2009 WiQuery team
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.odlabs.wiquery.plugins.ckeditor;

import java.io.Serializable;

import org.odlabs.wiquery.core.options.Options;

/**
 * $Id: FileBrowser.java roche.jul $
 * 
 * <p>
 * 	This class contains all the options for the file browser
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class FileBrowser implements Serializable {
	// Constants
	/** Constant of serialization  */
	private static final long serialVersionUID = -9128796752150643216L;
	
	// Variables
	private Options options;
	
	/**
	 * Default constructor
	 * @param options Instance of the {@link options}
	 */
	protected FileBrowser(Options options) {
		super();
		
		this.options = options;
	}
	
	/**
	 * @return the filebrowserBrowseUrl option
	 */
	public String getFilebrowserBrowseUrl() {
		String text = options.getLiteral("filebrowserBrowseUrl");
		return text == null ? "" : text;
	}
	
	/**
	 * @return the filebrowserFlashBrowseUrl option
	 */
	public String getFilebrowserFlashBrowseUrl() {
		String text = options.getLiteral("filebrowserFlashBrowseUrl");
		return text == null ? "" : text;
	}
	
	/**
	 * @return the filebrowserFlashUploadUrl option
	 */
	public String getFilebrowserFlashUploadUrl() {
		String text = options.getLiteral("filebrowserFlashUploadUrl");
		return text == null ? "" : text;
	}
	
	/**
	 * @return the filebrowserImageBrowseLinkUrl option
	 */
	public String getFilebrowserImageBrowseLinkUrl() {
		String text = options.getLiteral("filebrowserImageBrowseLinkUrl");
		return text == null ? "" : text;
	}
	
	/**
	 * @return the filebrowserImageBrowseUrl option
	 */
	public String getFilebrowserImageBrowseUrl() {
		String text = options.getLiteral("filebrowserImageBrowseUrl");
		return text == null ? "" : text;
	}
	
	/**
	 * @return the filebrowserImageUploadUrl option
	 */
	public String getFilebrowserImageUploadUrl() {
		String text = options.getLiteral("filebrowserImageUploadUrl");
		return text == null ? "" : text;
	}
	
	/**
	 * @return the filebrowserUploadUrl option
	 */
	public String getFilebrowserUploadUrl() {
		String text = options.getLiteral("filebrowserUploadUrl");
		return text == null ? "" : text;
	}
	
	/**
	 * Method setting the location of an external file browser, that should be 
	 * launched when "Browse Server" button is pressed in the Link tab of Image dialog.
	 * @param filebrowserImageBrowseLinkUrl
	 * @return the current instance
	 */
	public FileBrowser setFilebrowserBrowseLinkUrl(String filebrowserImageBrowseLinkUrl) {
		options.putLiteral("filebrowserImageBrowseLinkUrl", filebrowserImageBrowseLinkUrl);
		return this;
	}
	
	/**
	 * Method setting the location of an external file browser, that should be 
	 * launched when "Browse Server" button is pressed. If configured, the 
	 * "Browse Server" button will appear in Link, Image and Flash dialogs. 
	 * @param filebrowserBrowseUrl
	 * @return the current instance
	 */
	public FileBrowser setFilebrowserBrowseUrl(String filebrowserBrowseUrl) {
		options.putLiteral("filebrowserBrowseUrl", filebrowserBrowseUrl);
		return this;
	}
	
	/**
	 * Method setting the location of an external file browser, that should be 
	 * launched when "Browse Server" button is pressed in the Flash dialog.
	 * @param filebrowserFlashBrowseUrl
	 * @return the current instance
	 */
	public FileBrowser setFilebrowserFlashBrowseUrl(String filebrowserFlashBrowseUrl) {
		options.putLiteral("filebrowserFlashBrowseUrl", filebrowserFlashBrowseUrl);
		return this;
	}
	
	/**
	 * Method setting the location of a script that handles file uploads in the 
	 * Flash dialog.
	 * @param filebrowserFlashUploadUrl
	 * @return the current instance
	 */
	public FileBrowser setFilebrowserFlashUploadUrl(String filebrowserFlashUploadUrl) {
		options.putLiteral("filebrowserFlashUploadUrl", filebrowserFlashUploadUrl);
		return this;
	}
	
	/**
	 * Method setting the location of an external file browser, that should be 
	 * launched when "Browse Server" button is pressed in the Image dialog.
	 * @param filebrowserImageBrowseUrl
	 * @return the current instance
	 */
	public FileBrowser setFilebrowserImageBrowsedUrl(String filebrowserImageBrowseUrl) {
		options.putLiteral("filebrowserImageBrowseUrl", filebrowserImageBrowseUrl);
		return this;
	}
	
	/**
	 * Method setting the location of a script that handles file uploads in the 
	 * Image dialog. 
	 * @param filebrowserImageUploadUrl
	 * @return the current instance
	 */
	public FileBrowser setFilebrowserImageUploadUrl(String filebrowserImageUploadUrl) {
		options.putLiteral("filebrowserImageUploadUrl", filebrowserImageUploadUrl);
		return this;
	}
	
	/**
	 * Method setting the location of a script that handles file uploads. If set, 
	 * the "Upload" tab will appear in "Link", "Image" and "Flash" dialogs. 
	 * @param filebrowserUploadUrl
	 * @return the current instance
	 */
	public FileBrowser setFilebrowserUploadUrl(String filebrowserUploadUrl) {
		options.putLiteral("filebrowserUploadUrl", filebrowserUploadUrl);
		return this;
	}
}
