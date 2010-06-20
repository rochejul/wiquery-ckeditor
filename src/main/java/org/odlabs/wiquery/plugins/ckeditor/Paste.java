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
 * $Id: Paste.java roche.jul $
 * 
 * <p>
 * 	Class contains options for the paste
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class Paste implements Serializable {
	// Constants
	/** Constant of serialization  */
	private static final long serialVersionUID = 3529339607543884823L;

	// Variables
	private Options options;
	
	/**
	 * Default constructor
	 * @param options Instance of the {@link options}
	 */
	protected Paste(Options options) {
		super();
		
		this.options = options;
	}
	
	/**
	 * @return the pasteFromWordCleanupFile option
	 */
	public String getPasteFromWordCleanupFile() {
		String text = options.getLiteral("pasteFromWordCleanupFile");
		return text == null ? "default" : text;
	}
	
	/**
	 * @return the forcePasteAsPlainText option
	 */
	public boolean isForcePasteAsPlainText() {
		if(options.get("forcePasteAsPlainText") == null){
			return false;
		}
		
		return options.getBoolean("forcePasteAsPlainText");
	}
	
	/**
	 * @return the pasteFromWordPromptCleanup option
	 */
	public boolean isPasteFromWordPromptCleanup() {
		if(options.get("pasteFromWordPromptCleanup") == null){
			return true;
		}
		
		return options.getBoolean("pasteFromWordPromptCleanup");
	}
	
	/**
	 * @return the pasteFromWordRemoveFontStyles option
	 */
	public boolean isPasteFromWordRemoveFontStyles() {
		if(options.get("pasteFromWordRemoveFontStyles") == null){
			return true;
		}
		
		return options.getBoolean("pasteFromWordRemoveFontStyles");
	}
	
	/**
	 * @return the pasteFromWordRemoveStyles option
	 */
	public boolean isPasteFromWordRemoveStyles() {
		if(options.get("pasteFromWordRemoveStyles") == null){
			return true;
		}
		
		return options.getBoolean("pasteFromWordRemoveStyles");
	}
	
	/**
	 * Method to to force all pasting operations to insert on plain text into the 
	 * editor, loosing any formatting information possibly available in the source text. 
	 * @param forcePasteAsPlainText
	 * @return the current instance
	 */
	public Paste setForcePasteAsPlainText(boolean forcePasteAsPlainText) {
		options.put("forcePasteAsPlainText", forcePasteAsPlainText);
		return this;
	}
	
	/**
	 * Method setting the file that provides the MS Word cleanup function for 
	 * pasting operations.  
	 * @param pasteFromWordCleanupFile
	 * @return the current instance
	 */
	public Paste setPasteFromWordCleanupFile(String pasteFromWordCleanupFile) {
		options.putLiteral("pasteFromWordCleanupFile", pasteFromWordCleanupFile);
		return this;
	}
	
	/**
	 * Method to define if text pasted from Word must be cleaned
	 * @param pasteFromWordPromptCleanup
	 * @return the current instance
	 */
	public Paste setPasteFromWordPromptCleanup(boolean pasteFromWordPromptCleanup) {
		options.put("pasteFromWordPromptCleanup", pasteFromWordPromptCleanup);
		return this;
	}
	
	/**
	 * Method to define if text pasted from Word must be cleaned
	 * @param pasteFromWordRemoveFontStyles
	 * @return the current instance
	 */
	public Paste setPasteFromWordRemoveFontStyles(boolean pasteFromWordRemoveFontStyles) {
		options.put("pasteFromWordRemoveFontStyles", pasteFromWordRemoveFontStyles);
		return this;
	}
	
	/**
	 * Method to define if text pasted from Word must be cleaned
	 * @param pasteFromWordRemoveStyles
	 * @return the current instance
	 */
	public Paste setPasteFromWordRemoveStyles(boolean pasteFromWordRemoveStyles) {
		options.put("pasteFromWordRemoveStyles", pasteFromWordRemoveStyles);
		return this;
	}
}
