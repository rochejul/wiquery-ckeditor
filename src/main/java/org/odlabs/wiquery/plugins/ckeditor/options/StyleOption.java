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
package org.odlabs.wiquery.plugins.ckeditor.options;

import java.util.Map;
import java.util.Map.Entry;

import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.core.options.IComplexOption;

/**
 * $Id: FormatOption.java roche.jul $
 * 
 * <p>
 * 	Option for the style options
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class StyleOption implements IComplexOption {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 7256572687230056118L;
	
	// Properties
	private final HtmlTagEnum element;
	private final Map<String, String> style;
	private final Map<String, String> overrides;
	
	/**
	 * Constructor
	 * @param element
	 * @param style
	 */
	public StyleOption(HtmlTagEnum element, Map<String, String> style){
		this(element, style, null);
	}
	
	/**
	 * Constructor
	 * @param element
	 * @param style
	 */
	public StyleOption(HtmlTagEnum element, Map<String, String> style, Map<String, String> overrides){
		super();
		
		if(element == null){
			throw new NullPointerException("element cannot be null");
		}
		
		this.element = element;
		this.style = style;
		this.overrides = overrides;
	}

	/**
	 * @return the DOM element
	 */
	public HtmlTagEnum getElement() {
		return element;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{ element: " + JsUtils.quotes(element.toString()));
		
		if(style != null && style.size() > 0){
			buffer.append(", style: {");
			for(Entry<String, String> entry : style.entrySet()) {
				buffer.append(entry.getKey() + ": " + JsUtils.quotes(entry.getValue()) + ",");
			}
			buffer.append("}");
		}
		
		if(overrides != null && overrides.size() > 0){
			buffer.append(", overrides: [{");
			for(Entry<String, String> entry : overrides.entrySet()) {
				buffer.append(entry.getKey() + ": " + JsUtils.quotes(entry.getValue()) + ",");
			}
			buffer.append("}]");
		}
		
		buffer.append("}");
		
		return buffer;
	}

	public Map<String, String> getOverrides() {
		return overrides;
	}

	/**
	 * @return the style
	 */
	public Map<String, String> getStyle() {
		return style;
	}
}
