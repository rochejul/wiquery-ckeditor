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
package org.odlabs.wiquery.plugins.ckeditor.options.toolbar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.core.options.IComplexOption;

/**
 * $Id: CKeditorToolbarGroup.java roche.jul $
 * 
 * <p>
 * 	Group of toolbar elements
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class CKeditorToolbarGroup extends ArrayList<List<? extends CKeditorToolbarEnumLabel>> implements IComplexOption {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -6331798744153129415L;
	
	/**
	 * Method converting a list of elements in a charsquence
	 * @param elements
	 * @return the charsequence
	 */
	private CharSequence getInternalJavascriptArray(List<? extends CKeditorToolbarEnumLabel> elements){
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		
		if(elements != null && elements.size() > 0){
			Iterator<? extends CKeditorToolbarEnumLabel> iterator = elements.iterator();
			buffer.append(JsUtils.quotes(iterator.next().getToolbarValue()));
			
			while(iterator.hasNext()){
				buffer.append("," + JsUtils.quotes(iterator.next().getToolbarValue()));
			}
		}
		
		buffer.append("]");
		
		return buffer;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public final CharSequence getJavascriptOption(){
		StringBuffer buffer = new StringBuffer();

		if(size() > 0){
			Iterator<List<? extends CKeditorToolbarEnumLabel>> iterator = iterator();
			buffer.append(getInternalJavascriptArray(iterator.next()));
			
			while(iterator.hasNext()){
				buffer.append("," + getInternalJavascriptArray(iterator.next()));
			}
		}
		
		return buffer;
	}
}
