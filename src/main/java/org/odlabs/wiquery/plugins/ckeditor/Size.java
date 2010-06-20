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
import org.odlabs.wiquery.plugins.ckeditor.options.SizeOption;
import org.odlabs.wiquery.plugins.ckeditor.options.SizeOption.SizeMetric;

/**
 * $Id: Size.java roche.jul $
 * 
 * <p>
 * 	Class contains options for the size
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class Size implements Serializable {
	// Constants
	/** Constant of serialization  */
	private static final long serialVersionUID = 2156507594804689599L;

	// Variables
	private Options options;
	
	/**
	 * Default constructor
	 * @param options Instance of the {@link options}
	 */
	protected Size(Options options) {
		super();
		
		this.options = options;
	}
	
	/**
	 * @return the height option
	 */
	public SizeOption getHeight() {
		SizeOption height = (SizeOption) options.getComplexOption("height");
		return height == null ? new SizeOption(200, SizeMetric.PIXEL) : height;
	}
	
	/**
	 * @return the resize_maxHeight option
	 */
	public int getResizeMaxHeight() {
		if(options.get("resize_maxHeight") == null){
			return 3000;
		}
		
		return options.getInt("resize_maxHeight");
	}
	
	/**
	 * @return the resize_maxWidth option
	 */
	public int getResizeMaxWidth() {
		if(options.get("resize_maxWidth") == null){
			return 3000;
		}
		
		return options.getInt("resize_maxWidth");
	}
	
	/**
	 * @return the width option
	 */
	public SizeOption getWidth() {
		return (SizeOption) options.getComplexOption("width");
	}
	
	/**
	 * @return the disableObjectResizing option
	 */
	public boolean isDisableObjectResizing() {
		if(options.get("disableObjectResizing") == null){
			return false;
		}
		
		return options.getBoolean("disableObjectResizing");
	}
	
	/**
	 * @return the fullPage option
	 */
	public boolean isFullPage() {
		if(options.get("fullPage") == null){
			return false;
		}
		
		return options.getBoolean("fullPage");
	}
	
	/**
	 * @return the resize_enabled option
	 */
	public boolean isResizeEnabled() {
		if(options.get("resize_enabled") == null){
			return true;
		}
		
		return options.getBoolean("resize_enabled");
	}
	
	/**
	 * Method to disable the ability of resize objects (image and tables) in the 
	 * editing area 
	 * @param disableObjectResizing
	 * @return the current instance
	 */
	public Size setDisableObjectResizing(boolean disableObjectResizing) {
		options.put("disableObjectResizing", disableObjectResizing);
		return this;
	}
	
	/**
	 * Method Indicating whether the contents to be edited are being inputted as 
	 * a full HTML page. A full page includes the <html>, <head> and <body> tags. 
	 * The final output will also reflect this setting, including the <body> 
	 * contents only if this setting is disabled. 
	 * @param fullPage
	 * @return the current instance
	 */
	public Size setFullPage(boolean fullPage) {
		options.put("fullPage", fullPage);
		return this;
	}
	
	/**
	 * Method setting the height in CSS size format or pixel integer
	 * @param height
	 * @return the current instance
	 */
	public Size setHeight(SizeOption height) {
		options.put("height", height);
		return this;
	}
	
	/**
	 * Method to define if the editor an be resized
	 * @param resize_enabled
	 * @return the current instance
	 */
	public Size setResizeEnabled(boolean resize_enabled) {
		options.put("resize_enabled", resize_enabled);
		return this;
	}
	
	/**
	 * Method setting the max height of the editor
	 * @param resize_maxHeight
	 * @return the current instance
	 */
	public Size setResizeMaxHeight(int resize_maxHeight) {
		options.put("resize_maxHeight", resize_maxHeight);
		return this;
	}
	
	/**
	 * Method setting the max width of the editor
	 * @param resize_maxWidth
	 * @return the current instance
	 */
	public Size setResizeMaxWidth(int resize_maxWidth) {
		options.put("resize_maxWidth", resize_maxWidth);
		return this;
	}
	
	/**
	 * Method setting the width in CSS size format or pixel integer
	 * @param width
	 * @return the current instance
	 */
	public Size setWidth(SizeOption width) {
		options.put("width", width);
		return this;
	}
}
