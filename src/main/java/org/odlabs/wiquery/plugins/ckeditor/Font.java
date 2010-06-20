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
import org.odlabs.wiquery.plugins.ckeditor.options.StyleOption;

/**
 * $Id: Font.java roche.jul $
 * 
 * <p>
 * 	Class contains options for the font
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class Font implements Serializable {
	// Constants
	/** Constant of serialization  */
	private static final long serialVersionUID = 4562535767008032816L;

	// Variables
	private Options options;
	
	/**
	 * Default constructor
	 * @param options Instance of the {@link options}
	 */
	protected Font(Options options) {
		super();
		
		this.options = options;
	}
	
	/**
	 * @return the font_defaultLabel option
	 */
	public String getFontDefaultLabel() {
		String text = options.getLiteral("font_defaultLabel");
		return text == null ? "Arial" : text;
	}
	
	/**
	 * Method setting the text to be displayed in the Font combo is none of the 
	 * available values matches the current cursor position or text selection. 
	 * @param font_defaultLabel
	 * @return the current instance
	 */
	public Font setFontDefaultLabel(String font_defaultLabel) {
		options.putLiteral("font_defaultLabel", font_defaultLabel);
		return this;
	}
	
	/**
	 * @return the font_names option
	 */
	public String getFontNames() {
		String text = options.getLiteral("font_names");
		return text == null ? "Arial;Times New Roman;Verdana" : text;
	}
	
	/**
	 * Method setting the list of fonts names to be displayed in the Font combo 
	 * in the toolbar 
	 * @param font_names
	 * @return the current instance
	 */
	public Font setFontNames(String font_names) {
		options.putLiteral("font_names", font_names);
		return this;
	}
	
	/**
	 * @return the font_style option
	 */
	public StyleOption getFontStyle() {
		return (StyleOption) options.getComplexOption("font_style");
	}
	
	/**
	 * Method setting the style definition to be used to apply the font in the text. 
	 * in the text. 
	 * @param font_style
	 * @return the current instance
	 */
	public Font setFontStyle(StyleOption font_style) {
		options.put("font_style", font_style);
		return this;
	}
	
	/**
	 * @return the fontSize_defaultLabel option
	 */
	public String getFontSizeDefaultLabel() {
		String text = options.getLiteral("fontSize_defaultLabel");
		return text == null ? "12px" : text;
	}
	
	/**
	 * Method setting a text to be displayed in the Font Size combo is none of the 
	 * available values matches the current cursor position or text selection. 
	 * @param fontSize_defaultLabel
	 * @return the current instance
	 */
	public Font setFontSizeDefaultLabel(String fontSize_defaultLabel) {
		options.putLiteral("fontSize_defaultLabel", fontSize_defaultLabel);
		return this;
	}
	
	/**
	 * @return the fontSize_sizes option
	 */
	public String getFontSizeSizes() {
		String text = options.getLiteral("fontSize_sizes");
		return text == null ? "8/8px;9/9px;10/10px;11/11px;12/12px;14/14px;16/16px;18/18px;20/20px;22/22px;24/24px;26/26px;28/28px;36/36px;48/48px;72/72px" : text;
	}
	
	/**
	 * Method setting a list of fonts size to be displayed in the Font Size combo 
	 * in the toolbar. 
	 * @param fontSize_sizes
	 * @return the current instance
	 */
	public Font setFontSizeSizes(String fontSize_sizes) {
		options.putLiteral("fontSize_sizes", fontSize_sizes);
		return this;
	}
	
	/**
	 * @return the fontSize_style option
	 */
	public StyleOption getFontSizeStyle() {
		return (StyleOption) options.getComplexOption("fontSize_style");
	}
	
	/**
	 * Method setting the style definition to be used to apply the font size 
	 * in the text. 
	 * @param fontSize_style
	 * @return the current instance
	 */
	public Font setFontSizeStyle(StyleOption fontSize_style) {
		options.put("fontSize_style", fontSize_style);
		return this;
	}
}
