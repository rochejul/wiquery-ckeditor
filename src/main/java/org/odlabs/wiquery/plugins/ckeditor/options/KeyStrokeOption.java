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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.IListItemOption;

/**
 * $Id: KeyStrokeOption.java roche.jul $
 * 
 * <p>
 * 	Option to use a keystroke
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class KeyStrokeOption implements IComplexOption, IListItemOption {
	/**
	 * Enumeration of possible mask
	 */
	public enum KeyStrokeMask {
		ALT,
		CTRL,
		SHIFT;
	}
	
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 2688124675103427259L;
	
	// Properties
	private int keyCode;
	private Set<KeyStrokeMask> masks;
	
	/**
	 * Constructor
	 * @param keyCode
	 * @param masks
	 */
	public KeyStrokeOption(int keyCode, KeyStrokeMask... masks) {
		super();
		this.keyCode = keyCode;
		this.masks = new HashSet<KeyStrokeMask>(Arrays.asList(masks));
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		StringBuffer buffer = new StringBuffer();
		
		for(KeyStrokeMask m : masks) {
			buffer.append("CKEDITOR." + m.toString() + " + ");
		}
		
		buffer.append(Integer.toString(keyCode));
		return buffer;
	}

	/**
	 * @return the keyCode
	 */
	public int getKeyCode() {
		return keyCode;
	}

	/**
	 * @return the masks
	 */
	public Set<KeyStrokeMask> getMasks() {
		return masks;
	}

	/**
	 * Method setting the keyCode
	 * @param keyCode
	 */
	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	/**
	 * Method setting the masks
	 * @param masks
	 */
	public void setMasks(Set<KeyStrokeMask> masks) {
		this.masks = masks;
	}
}
