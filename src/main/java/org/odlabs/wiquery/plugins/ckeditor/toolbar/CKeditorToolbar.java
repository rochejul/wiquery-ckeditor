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
package org.odlabs.wiquery.plugins.ckeditor.toolbar;

import java.util.Iterator;
import java.util.List;

import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.core.options.IComplexOption;

/**
 * $Id: CKEditorToolbar.java roche.jul $
 * 
 * <p>
 * 	Option to define the toolbar
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class CKeditorToolbar implements IComplexOption {
	/**
	 * Enumeration of possible Toolbar	
	 * @author Julien Roche
	 * @since 1.1
	 */
	public enum ToolbarType {
		BASIC	("Basic"),
		FULL	("Full");
		
		// Properties
		private final String value;
		
		/**
		 * Constructor
		 * @param toolbarValue
		 */
		ToolbarType(String value){
			this.value = value;
		}

		/**
		 * {@inheritDoc}
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return JsUtils.quotes(value);
		}
	}
	
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 2095607959087986061L;
	
	// Properties
	private final List<CKeditorToolbarGroup> toolbar;
	private final ToolbarType toolbarType;
	
	/**
	 * Constructor
	 * @param toolbar List of elements for the toolbar
	 */
	public CKeditorToolbar(List<CKeditorToolbarGroup> toolbar) {
		this.toolbarType = null;
		this.toolbar = toolbar;
	}
	
	/**
	 * Constructor
	 * @param toolbarType Type of toolbar
	 */
	public CKeditorToolbar(ToolbarType toolbarType) {
		this.toolbarType = toolbarType;
		this.toolbar = null;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		if(toolbar != null){
			StringBuffer buffer = new StringBuffer();
			buffer.append("[");
			
			if(toolbar.size() > 0){
				Iterator<CKeditorToolbarGroup> iterator = toolbar.iterator();
				buffer.append(iterator.next().getJavascriptOption());
				
				while(iterator.hasNext()){
					buffer.append(",'/'," + iterator.next().getJavascriptOption());
				}
			}
			
			buffer.append("]");
			return buffer;
		}
		
		return toolbarType.toString();
	}
}
