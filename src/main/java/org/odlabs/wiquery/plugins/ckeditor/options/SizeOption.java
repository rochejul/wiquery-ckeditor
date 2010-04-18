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

import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.core.options.IComplexOption;

/**
 * $Id: SizeOption.java roche.jul $
 * 
 * <p>
 * 	Option to specify a css size, in pixel, purcentage or em
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class SizeOption implements IComplexOption {
	public enum SizeMetric {
		EM				("em"),
		PIXEL			("px"),
		PURCENTAGE		("%");
		
		// Properties
		private final String value;
		
		/**
		 * Default constructor
		 * @param value
		 */
		SizeMetric(String value) {
			this.value = value;
		}
		
		/**
		 * @return the value of the metric
		 */
		public String getValue() {
			return value;
		}
	}
	
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	// Properties
	private int value;
	private SizeMetric metric;
	
	public SizeOption(int value, SizeMetric metric){
		super();
		this.value = value;
		this.metric = metric;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		return JsUtils.quotes(Integer.toString(value) 
				+ (metric == null ? "" : metric.getValue()));
	}

	/**
	 * @return the metric
	 */
	public SizeMetric getMetric() {
		return metric;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Method setting the metric
	 * @param metric
	 */
	public void setMetric(SizeMetric metric) {
		this.metric = metric;
	}

	/**
	 * MEtod setting the value
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
