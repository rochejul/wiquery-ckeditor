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
 * $Id: Locale.java roche.jul $
 * 
 * <p>
 * 	Class contains the options for the localization
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class Locale implements Serializable {
	// Constants
	/** Constant of serialization  */
	private static final long serialVersionUID = 2595135312942325583L;

	// Variables
	private Options options;
	
	/**
	 * Default constructor
	 * @param options Instance of the {@link options}
	 */
	protected Locale(Options options) {
		super();
		
		this.options = options;
	}
	
	/**
	 * @return the contentsLangDirection option
	 */
	public String getContentsLangDirection() {
		String text = options.getLiteral("contentsLangDirection");
		return text == null ? "ltr" : text;
	}
	
	/**
	 * @return the defaultLanguage option
	 */
	public String getDefaultLanguage() {
		String text = options.getLiteral("defaultLanguage");
		return text == null ? "en" : text;
	}
	
	/**
	 * @return the language option
	 */
	public String getLanguage() {
		String text = options.getLiteral("language");
		return text == null ? "" : text;
	}
	
	/**
	 * @return the disableNativeSpellChecker option
	 */
	public boolean isDisableNativeSpellChecker() {
		if(options.get("disableNativeSpellChecker") == null){
			return true;
		}
		
		return options.getBoolean("disableNativeSpellChecker");
	}
	
	/**
	 * @return the entities_greek option
	 */
	public boolean isEntitiesGreek() {
		if(options.get("entities_greek") == null){
			return true;
		}
		
		return options.getBoolean("entities_greek");
	}
	
	/**
	 * @return the entities_latin option
	 */
	public boolean isEntitiesLatin() {
		if(options.get("entities_latin") == null){
			return true;
		}
		
		return options.getBoolean("entities_latin");
	}
	
	/**
	 * @return the entities_processNumerical option
	 */
	public boolean isEntitiesProcessNumerical() {
		if(options.get("entities_processNumerical") == null){
			return false;
		}
		
		return options.getBoolean("entities_processNumerical");
	}
	
	/**
	 * Method setting the writting direction of the language used to write the 
	 * editor contents. Allowed values are 'ltr' for Left-To-Right language 
	 * (like English), or 'rtl' for Right-To-Left languages (like Arabic). 
	 * @return the current instance
	 */
	public Locale setContentsLangDirection(String contentsLangDirection) {
		options.putLiteral("contentsLangDirection", contentsLangDirection);
		return this;
	}
	
	/**
	 * Method setting the language to be used if CKEDITOR.config.language  is 
	 * left empty and it's not possible to localize the editor to the user language. 
	 * @param defaultLanguage
	 * @return the current instance
	 */
	public Locale setDefaultLanguage(String defaultLanguage) {
		options.putLiteral("defaultLanguage", defaultLanguage);
		return this;
	}
	
	/**
	 * Method to disable the built-in spell checker while typing natively available 
	 * in the browser (currently Firefox and Safari only).
	 * @param disableNativeSpellChecker
	 * @return the current instance
	 */
	public Locale setDisableNativeSpellChecker(boolean disableNativeSpellChecker) {
		options.put("disableNativeSpellChecker", disableNativeSpellChecker);
		return this;
	}
	
	/**
	 * Whether to convert some symbols, mathematical symbols, and Greek letters 
	 * to HTML entities.
	 * @param entities_greek
	 * @return the current instance
	 */
	public Locale setEntitiesGreek(boolean entities_greek) {
		options.put("entities_greek", entities_greek);
		return this;
	}
	
	/**
	 * Whether to convert some Latin characters (Latin alphabet No. 1, ISO 8859-1) 
	 * to HTML entities.
	 * @param entities_latin
	 * @return the current instance
	 */
	public Locale setEntitiesLatin(boolean entities_latin) {
		options.put("entities_latin", entities_latin);
		return this;
	}
	
	/**
	 * Whether to convert all remaining characters, not comprised in the ASCII 
	 * character table, to their relative numeric representation of HTML entity.
	 * @param entities_processNumerical
	 * @return the current instance
	 */
	public Locale setEntitiesProcessNumerical(boolean entities_processNumerical) {
		options.put("entities_processNumerical", entities_processNumerical);
		return this;
	}
	
	/**
	 * Method setting the user interface language localization to use. If empty, 
	 * the editor automatically localize the editor to the user language, if supported
	 * @param language
	 * @return the current instance
	 */
	public Locale setLanguage(String language) {
		options.putLiteral("language", language);
		return this;
	}
}
