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
 * $Id: Dialog.java roche.jul $
 * 
 * <p>
 * 	Class contains options for the dialog
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class Dialog implements Serializable {
	// Constants
	/** Constant of serialization  */
	private static final long serialVersionUID = 2867339666944034372L;

	// Variables
	private Options options;
	
	/**
	 * Default constructor
	 * @param options Instance of the {@link options}
	 */
	protected Dialog(Options options) {
		super();
		
		this.options = options;
	}
	
	/**
	 * @return the dialog_backgroundCoverColor option
	 */
	public String getDialogBackgroundCoverColor() {
		String text = options.getLiteral("dialog_backgroundCoverColor");
		return text == null ? "white" : text;
	}
	
	/**
	 * @return the dialog_magnetDistance option
	 */
	public float getDialogBackgroundCoverOpacity() {
		if(options.get("dialog_backgroundCoverOpacity") == null){
			return 0.5F;
		}
		
		return options.getFloat("dialog_backgroundCoverOpacity");
	}
	
	/**
	 * @return the dialog_magnetDistance option
	 */
	public int getDialogMagnetDistance() {
		if(options.get("dialog_magnetDistance") == null){
			return 20;
		}
		
		return options.getInt("dialog_magnetDistance");
	}
	
	/**
	 * @return the dialog_startupFocusTab option
	 */
	public boolean isDialogStartupFocusTab() {
		if(options.get("dialog_startupFocusTab") == null){
			return false;
		}
		
		return options.getBoolean("dialog_startupFocusTab");
	}
	
	/**
	 * Method setting the color of the dialog background cover. It should be 
	 * a valid CSS color string 
	 * @param dialog_backgroundCoverColor
	 * @return the current instance
	 */
	public Dialog setDialogBackgroundCoverColor(String dialog_backgroundCoverColor) {
		options.putLiteral("dialog_backgroundCoverColor", dialog_backgroundCoverColor);
		return this;
	}
	
	/**
	 * Method setting theopacity of the dialog background cover. It should be 
	 * a number within the range [0.0, 1.0]. 
	 * @param dialog_backgroundCoverOpacity
	 * @return the current instance
	 */
	public Dialog setDialogBackgroundCoverOpacity(float dialog_backgroundCoverOpacity) {
		options.put("dialog_backgroundCoverOpacity", dialog_backgroundCoverOpacity);
		return this;
	}
	
	/**
	 * Method setting the distance of magnetic borders used in moving and 
	 * resizing dialogs, measured in pixels. 
	 * @param dialog_magnetDistance
	 * @return the current instance
	 */
	public Dialog setDialogMagnetDistance(int dialog_magnetDistance) {
		options.put("dialog_magnetDistance", dialog_magnetDistance);
		return this;
	}
	
	/**
	 * Method to put focus into the first tab as soon as dialog is opened. 
	 * @param dialog_startupFocusTab
	 * @return the current instance
	 */
	public Dialog setDialogStartupFocusTab(boolean dialog_startupFocusTab) {
		options.put("dialog_startupFocusTab", dialog_startupFocusTab);
		return this;
	}
}
