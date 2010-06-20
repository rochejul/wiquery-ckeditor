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

import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.ICollectionItemOptions;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.plugins.ckeditor.options.StyleOption;
import org.odlabs.wiquery.plugins.ckeditor.options.list.ListOptions;

/**
 * $Id: StyleSheet.java roche.jul $
 * 
 * <p>
 * 	Class contains options for the stylesheet
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class StyleSheet implements Serializable {
	// Constants
	/** Constant of serialization  */
	private static final long serialVersionUID = 6752535353008032816L;

	// Variables
	private Options options;
	
	/**
	 * Default constructor
	 * @param options Instance of the {@link options}
	 */
	protected StyleSheet(Options options) {
		super();
		
		this.options = options;
	}
	
	/**
	 * @return the StyleOption option
	 */
	public StyleOption getColorButtonBackStyle() {
		return (StyleOption) options.getComplexOption("colorButton_backStyle");
	}
	
	/**
	 * @return the list of colors button option
	 */
	public ListOptions getColorButtonColors() {
		Object object = options.getComplexOption("colorButton_colors");
		
		if(object == null){
			return ListOptions.asList("000","800000","8B4513","2F4F4F","008080",
					"000080","4B0082","696969","B22222","A52A2A","DAA520","006400",
					"40E0D0","0000CD","800080","808080","F00","FF8C00","FFD700",
					"008000","0FF","00F","EE82EE","A9A9A9","FFA07A","FFA500",
					"FFFF00","00FF00","AFEEEE","ADD8E6","DDA0DD","D3D3D3","FFF0F5",
					"FAEBD7","FFFFE0","F0FFF0","F0FFFF","F0F8FF","E6E6FA","FFF");
		}
		
		return (ListOptions) object;
	}
	
	/**
	 * @return the StyleOption option
	 */
	public StyleOption getColorButtonForeStyle() {
		return (StyleOption) options.getComplexOption("colorButton_foreStyle");
	}
	
	/**
	 * @return the contentsCss option
	 */
	@SuppressWarnings("unchecked")
	public ArrayItemOptions<LiteralOption> getContentsCss() {
		ICollectionItemOptions coll = options.getListItemOptions("contentsCss");
		
		if(coll == null){
			ArrayItemOptions<LiteralOption> arr = new ArrayItemOptions<LiteralOption>();
			arr.add(new LiteralOption("<CKEditor folder>/contents.css"));
			return arr;
		}
		
		return (ArrayItemOptions<LiteralOption>) coll;
	}
	
	/**
	 * @return the skin option
	 */
	public String getSkin() {
		String skin = options.getLiteral("skin");
		return skin == null ? "myskin,/customstuff/myskin/" : skin;
	}
	
	/**
	 * @return the stylesSet option
	 */
	public String getStylesSet() {
		String stylesSet = options.getLiteral("stylesSet");
		return stylesSet == null ? "default" : stylesSet;
	}
	
	/**
	 * @return the templates option
	 */
	public String getTemplates() {
		String templates = options.getLiteral("templates");
		return templates == null ? "default" : templates;
	}
	
	/**
	 * @return the templates_files option
	 */
	@SuppressWarnings("unchecked")
	public ArrayItemOptions<LiteralOption> getTemplatesFiles() {
		ICollectionItemOptions coll = options.getListItemOptions("templates_files");
		
		if(coll == null){
			ArrayItemOptions<LiteralOption> arr = new ArrayItemOptions<LiteralOption>();
			arr.add(new LiteralOption("plugins/templates/templates/default.js"));
			return arr;
		}
		
		return (ArrayItemOptions<LiteralOption>) coll;
	}
	
	/**
	 * @return the theme option
	 */
	public String getTheme() {
		String theme = options.getLiteral("theme");
		return theme == null ? "default" : theme;
	}
	
	/**
	 * @return the colorButton_enableMore option
	 */
	public boolean isColorButtonEnableMore() {
		if(options.get("colorButton_enableMore") == null){
			return false;
		}
		
		return options.getBoolean("colorButton_enableMore");
	}
	
	/**
	 * @return the templates_replaceContent option
	 */
	public boolean isTemplatesReplaceContent() {
		if(options.get("templates_replaceContent") == null){
			return true;
		}
		
		return options.getBoolean("templates_replaceContent");
	}
	
	/**
	 * Method setting the style definition to be used to apply the text background color
	 * @param colorButton_foreStyle
	 * @return the current instance
	 */
	public StyleSheet setColorButtonBackStyle(StyleOption colorButton_backStyle) {
		options.put("colorButton_backStyle", colorButton_backStyle);
		return this;
	}
	
	/**
	 * Method setting the colors to be displayed in the color selectors.
	 * @param colorButton_colors
	 * @return the current instance
	 */
	public StyleSheet setColorButtonColors(ListOptions colorButton_colors) {
		options.put("colorButton_colors", colorButton_colors);
		return this;
	}
	
	/**
	 * Whether to enable the "More Colors..." button in the color selectors. 
	 * @param colorButton_enableMore
	 * @return the current instance
	 */
	public StyleSheet setColorButtonEnableMore(boolean colorButton_enableMore) {
		options.put("colorButton_enableMore", colorButton_enableMore);
		return this;
	}
	
	/**
	 * Method setting the style definition to be used to apply the text foreground color
	 * @param colorButton_foreStyle
	 * @return the current instance
	 */
	public StyleSheet setColorButtonForeStyle(StyleOption colorButton_foreStyle) {
		options.put("colorButton_foreStyle", colorButton_foreStyle);
		return this;
	}
	
	/**
	 * Method setting the CSS file(s) to be used to apply style to the contents. 
	 * It should reflect the CSS used in the final pages where the contents are 
	 * to be used. 
	 * @param contentsCss
	 * @return the current instance
	 */
	public StyleSheet setContentsCss(ArrayItemOptions<LiteralOption> contentsCss) {
		options.put("contentsCss", contentsCss);
		return this;
	}
	
	/**
	 * Method setting the base path used to build the URL for the smiley images. 
	 * It must end with a slash
	 * @param skin
	 * @return the current instance
	 */
	public StyleSheet setSkin(String skin) {
		options.putLiteral("skin", skin);
		return this;
	}
	
	/**
	 * Method setting the style to use in the editor
	 * @param stylesSet
	 * @return the current instance
	 */
	public StyleSheet setStylesSet(String stylesSet) {
		options.putLiteral("stylesSet", stylesSet);
		return this;
	}
	
	/**
	 * Method setting the templates to use
	 * @param templates
	 * @return the current instance
	 */
	public StyleSheet setTemplates(String templates) {
		options.putLiteral("templates", templates);
		return this;
	}
	
	/**
	 * Methode setting the list of templates definition files to load
	 * @param templates_files
	 * @return the current instance
	 */
	public StyleSheet setTemplatesFiles(ArrayItemOptions<LiteralOption> templates_files) {
		options.put("templates_files", templates_files);
		return this;
	}
	
	/**
	 * Whether the "Replace actual contents" checkbox is checked by default in 
	 * the Templates dialog. 
	 * @param templates_replaceContent
	 * @return the current instance
	 */
	public StyleSheet setTemplatesReplaceContent(boolean templates_replaceContent) {
		options.put("templates_replaceContent", templates_replaceContent);
		return this;
	}
	
	/**
	 * Method setting the theme to use
	 * @param theme
	 * @return the current instance
	 */
	public StyleSheet setTheme(String theme) {
		options.putLiteral("theme", theme);
		return this;
	}
}
