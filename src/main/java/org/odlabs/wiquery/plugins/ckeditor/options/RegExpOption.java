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

import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.IListItemOption;

/**
 * $Id: RegExpOption roche.jul $
 * 
 * <p>
 * 	Option to specify a regular expression
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class RegExpOption implements IComplexOption, IListItemOption {
	/**
	 * Enumeration of possible flags
	 */
	public enum RegExpFlag {
		G,
		GI,
		I;
	}
	
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	// Properties
	private String regExp;
	private RegExpFlag flag;
	
	public RegExpOption(String regExp, RegExpFlag flag){
		super();
		this.regExp = regExp;
		this.flag = flag;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		if(regExp == null){
			return "";
		}
		
		return "/" + regExp + "/" + (flag == null ? "" : flag.toString().toLowerCase()); 
	}

	/**
	 * @return the flag
	 */
	public RegExpFlag getFlag() {
		return flag;
	}

	/**
	 * @return the regExp
	 */
	public String getRegExp() {
		return regExp;
	}

	/**
	 * Method setting the flag
	 * @param flag
	 */
	public void setFlag(RegExpFlag flag) {
		this.flag = flag;
	}

	/**
	 * Method setting the regExp
	 * @param regExp
	 */
	public void setRegExp(String regExp) {
		this.regExp = regExp;
	}
}
