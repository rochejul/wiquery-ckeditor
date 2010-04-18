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
 * 	Option for the format options
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class FormatOption implements IComplexOption {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 7256572687230056118L;
	
	// Properties
	private final HtmlTagEnum element;
	private final Map<String, String> attributes;
	private final String overrides;
	
	/**
	 * Constructor
	 * @param element
	 * @param attributes
	 */
	public FormatOption(HtmlTagEnum element, Map<String, String> attributes){
		this(element, attributes, null);
	}
	
	/**
	 * Constructor
	 * @param element
	 * @param attributes
	 */
	public FormatOption(HtmlTagEnum element, Map<String, String> attributes, String overrides){
		super();
		
		if(element == null){
			throw new NullPointerException("element cannot be null");
		}
		
		this.element = element;
		this.attributes = attributes;
		this.overrides = overrides;
	}

	/**
	 * @return the attributes
	 */
	public Map<String, String> getAttributes() {
		return attributes;
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
		
		if(attributes != null && attributes.size() > 0){
			buffer.append(", attributes: {");
			for(Entry<String, String> entry : attributes.entrySet()) {
				buffer.append(entry.getKey() + ": " + JsUtils.quotes(entry.getValue()) + ",");
			}
			buffer.append("}");
		}
		
		if(overrides != null){
			buffer.append(", overrides: " + JsUtils.quotes(overrides));
		}
		
		buffer.append("}");
		
		return buffer;
	}
	
	public String getOverrides() {
		return overrides;
	}
}
