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
import org.odlabs.wiquery.plugins.ckeditor.options.FormatOption;
import org.odlabs.wiquery.plugins.ckeditor.options.list.ListOptions;

/**
 * $Id: Format.java roche.jul $
 * 
 * <p>
 * 	Class contains options for the format
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class Format implements Serializable {
	// Constants
	/** Constant of serialization  */
	private static final long serialVersionUID = 3677225892323447498L;

	// Variables
	private Options options;
	
	/**
	 * Default constructor
	 * @param options Instance of the {@link options}
	 */
	protected Format(Options options) {
		super();
		
		this.options = options;
	}
	
	/**
	 * @return the coreStyles_bold option
	 */
	public FormatOption getCoreStylesBold() {
		return (FormatOption) options.getComplexOption("coreStyles_bold");
	}
	
	/**
	 * @return the coreStyles_italic option
	 */
	public FormatOption getCoreStylesItalic() {
		return (FormatOption) options.getComplexOption("coreStyles_italic");
	}
	
	/**
	 * @return the coreStyles_strike option
	 */
	public FormatOption getCoreStylesStrike() {
		return (FormatOption) options.getComplexOption("coreStyles_strike");
	}
	
	/**
	 * @return the coreStyles_subscript option
	 */
	public FormatOption getCoreStylesSubscript() {
		return (FormatOption) options.getComplexOption("coreStyles_subscript");
	}
	
	/**
	 * @return the coreStyles_superscript option
	 */
	public FormatOption getCoreStylesSuperscript() {
		return (FormatOption) options.getComplexOption("coreStyles_superscript");
	}
	
	/**
	 * @return the coreStyles_underline option
	 */
	public FormatOption getCoreStylesUnderline() {
		return (FormatOption) options.getComplexOption("coreStyles_underline");
	}
	
	/**
	 * @return the format_address
	 */
	public FormatOption getFormatAddress() {
		return (FormatOption) options.getComplexOption("format_address");
	}
	
	/**
	 * @return the format_div
	 */
	public FormatOption getFormatDiv() {
		return (FormatOption) options.getComplexOption("format_div");
	}
	
	/**
	 * @return the format_h1
	 */
	public FormatOption getFormatH1() {
		return (FormatOption) options.getComplexOption("format_h1");
	}
	
	/**
	 * @return the format_h2
	 */
	public FormatOption getFormatH2() {
		return (FormatOption) options.getComplexOption("format_h2");
	}
	
	/**
	 * @return the format_h3
	 */
	public FormatOption getFormatH3() {
		return (FormatOption) options.getComplexOption("format_h3");
	}
	
	/**
	 * @return the format_h4
	 */
	public FormatOption getFormatH4() {
		return (FormatOption) options.getComplexOption("format_h4");
	}
	
	/**
	 * @return the format_h5
	 */
	public FormatOption getFormatH5() {
		return (FormatOption) options.getComplexOption("format_h5");
	}
	
	/**
	 * @return the format_h6
	 */
	public FormatOption getFormatH6() {
		return (FormatOption) options.getComplexOption("format_h6");
	}
	
	/**
	 * @return the format_p
	 */
	public FormatOption getFormatP() {
		return (FormatOption) options.getComplexOption("format_p");
	}
	
	/**
	 * @return the format_pre
	 */
	public FormatOption getFormatPre() {
		return (FormatOption) options.getComplexOption("format_pre");
	}
	
	/**
	 * @return the format_tags option
	 */
	public String getFormatTags() {
		String text = options.getLiteral("format_tags");
		return text == null ? "p;h1;h2;h3;h4;h5;h6;pre;address;div" : text;
	}
	
	/**
	 * @return the removeFormatTags option
	 */
	public ListOptions getRemoveFormatTags() {
		Object object = options.getComplexOption("removeFormatTags");
		
		if(object == null){
			return ListOptions.asList("b","big","code","del","dfn","em","font",
					"i","ins","kbd","q","samp","small","span","strike","strong",
					"sub","sup","tt","u","va");
		}
		
		return (ListOptions) object;
	}
	
	/**
	 * Method setting the style definition to be used to apply the strike bold in the text
	 * @param coreStyles_bold
	 * @return the current instance
	 */
	public Format setCoreStylesBold(FormatOption coreStyles_bold) {
		options.put("coreStyles_bold", coreStyles_bold);
		return this;
	}
	
	/**
	 * Method setting the style definition to be used to apply the strike italic in the text
	 * @param coreStyles_italic
	 * @return the current instance
	 */
	public Format setCoreStylesItalic(FormatOption coreStyles_italic) {
		options.put("coreStyles_italic", coreStyles_italic);
		return this;
	}
	
	/**
	 * Method setting the style definition to be used to apply the strike style in the text
	 * @param coreStyles_strike
	 * @return the current instance
	 */
	public Format setCoreStylesStrike(FormatOption coreStyles_strike) {
		options.put("coreStyles_strike", coreStyles_strike);
		return this;
	}
	
	/**
	 * Method setting the style definition to be used to apply the subscript style in the text
	 * @param coreStyles_subscript
	 * @return the current instance
	 */
	public Format setCoreStylesSubscript(FormatOption coreStyles_subscript) {
		options.put("coreStyles_subscript", coreStyles_subscript);
		return this;
	}
	
	/**
	 * Method setting the style definition to be used to apply the superscript style in the text
	 * @param coreStyles_superscript
	 * @return the current instance
	 */
	public Format setCoreStylesSuperscript(FormatOption coreStyles_superscript) {
		options.put("coreStyles_superscript", coreStyles_superscript);
		return this;
	}
	
	/**
	 * Method setting the style definition to be used to apply the underline style in the text
	 * in the text. 
	 * @param coreStyles_underline
	 * @return the current instance
	 */
	public Format setCoreStylesUnderline(FormatOption coreStyles_underline) {
		options.put("coreStyles_underline", coreStyles_underline);
		return this;
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Address" format. 
	 * @param format_address
	 * @return the current instance
	 */
	public Format setFormatAddress(FormatOption format_address) {
		options.put("format_address", format_address);
		return this;
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Normal (DIV)" format. 
	 * @param format_div
	 * @return the current instance
	 */
	public Format setFormatDiv(FormatOption format_div) {
		options.put("format_div", format_div);
		return this;
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Heading 1" format. 
	 * @param format_h1
	 * @return the current instance
	 */
	public Format setFormatH1(FormatOption format_h1) {
		options.put("format_h1", format_h1);
		return this;
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Heading 2" format. 
	 * @param format_h2
	 * @return the current instance
	 */
	public Format setFormatH2(FormatOption format_h2) {
		options.put("format_h2", format_h2);
		return this;
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Heading 3" format. 
	 * @param format_h3
	 * @return the current instance
	 */
	public Format setFormatH3(FormatOption format_h3) {
		options.put("format_h3", format_h3);
		return this;
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Heading 4" format. 
	 * @param format_h4
	 * @return the current instance
	 */
	public Format setFormatH4(FormatOption format_h4) {
		options.put("format_h4", format_h4);
		return this;
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Heading 5" format. 
	 * @param format_h5
	 * @return the current instance
	 */
	public Format setFormatH5(FormatOption format_h5) {
		options.put("format_h5", format_h5);
		return this;
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Heading 6" format. 
	 * @param format_h6
	 * @return the current instance
	 */
	public Format setFormatH6(FormatOption format_h6) {
		options.put("format_h6", format_h6);
		return this;
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Normal" format. 
	 * @param format_p
	 * @return the current instance
	 */
	public Format setFormatP(FormatOption format_p) {
		options.put("format_p", format_p);
		return this;
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Formatted" format. 
	 * @param format_pre
	 * @return the current instance
	 */
	public Format setFormatPre(FormatOption format_pre) {
		options.put("format_pre", format_pre);
		return this;
	}
	
	/**
	 * Method setting a list of semi colon separated style names (by default tags) 
	 * representing the style definition for each entry to be displayed in the 
	 * Format combo in the toolbar. Each entry must have its relative definition 
	 * configuration in a setting named "format_(tagName)". For example, the 
	 * "p" entry has its definition taken from config.format_p. 
	 * @param format_tags
	 * @return the current instance
	 */
	public Format setFormatTags(String format_tags) {
		options.putLiteral("format_tags", format_tags);
		return this;
	}
	
	/**
	 * Method setting a comma separated list of elements to be removed when 
	 * executing the "remove " format" command. 
	 * @param removeFormatTags
	 * @return the current instance
	 */
	public Format setRemoveFormatTags(ListOptions removeFormatTags) {
		options.put("removeFormatTags", removeFormatTags);
		return this;
	}
}
