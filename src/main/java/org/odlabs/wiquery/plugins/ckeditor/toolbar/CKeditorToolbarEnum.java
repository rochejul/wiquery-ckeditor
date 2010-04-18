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

/**
 * $Id: CKEditorToolbarEnum.java roche.jul $
 * 
 * <p>
 * 	Enumeration of all possible elements for the toolbar
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public enum CKeditorToolbarEnum implements CKeditorToolbarEnumLabel {
	ABOUT				("About"),
	ANCHOR				("Anchor"),
	BGCOLOR				("BGColor"),
	BLOCKQUOTE			("Blockquote"),
	BOLD				("Bold"),
	BULLETED_LIST		("BulletedList"),
	BUTTON				("Button"),
	CHECKBOX			("Checkbox"),
	COPY				("Copy"),
	CUT					("Cut"),
	FIND				("Find"),
	FLASH				("Flash"),
	FONT				("Font"),
	FONT_SIZE			("FontSize"),
	FORM				("Form"),
	FORMAT				("Format"),
	HIDDENFIELD			("HiddenField"),
	HORIZONTAL_RULE		("HorizontalRule"),
	IMAGE				("Image"),
	IMAGEBUTTON			("ImageButton"),
	INDENT				("Indent"),
	ITALIC				("Italic"),
	LINK				("Link"),
	MAXIMIZE			("Maximize"),
	NEW_PAGE			("NewPage"),
	NUMBERED_LIST		("NumberedList"),
	OUTDENT				("Outdent"),
	PAGE_BREAK			("PageBreak"),
	PASTE				("Paste"),
	PASTE_FROM_WORD		("PasteFromWord"),
	PASTE_TEXT			("PasteText"),
	PREVIEW				("Preview"),
	PRINT				("Print"),
	RADIO				("Radio"),
	REDO				("Redo"),
	REPLACE				("Replace"),
	REMOVE_FORMAT		("RemoveFormat"),
	SAVE				("Save"),
	SCAYT				("Scayt"),
	SELECT				("Select"),
	SELECT_ALL			("SelectAll"),
	SHOW_BLOCKS			("ShowBlocks"),
	SMILEY				("Smiley"),
	SOURCE				("source"),
	SPECIAL_CHAR		("SpecialChar"),
	SPELL_CHECKER		("SpellChecker"),
	STRIKE				("Strike"),
	STYLES				("Styles"),
	SUBSCRIPT			("Subscript"),
	SUPERSCRIPT			("Superscript"),
	TABLE				("Table"),
	TEMPLATES			("Templates"),
	TEXTAREA			("Textarea"),
	TEXT_COLOR			("TextColor"),
	TEXTFIELD			("TextField"),
	UNDERLINE			("Underline"),
	UNDO				("Undo"),
	UNLINK				("Unlink");
	
	// Properties
	private final String toolbarValue;
	
	/**
	 * Constructor
	 * @param toolbarValue
	 */
	CKeditorToolbarEnum(String toolbarValue){
		this.toolbarValue = toolbarValue;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.plugins.ckeditor.toolbar.CKeditorToolbarEnumLabel#getToolbarValue()
	 */
	public String getToolbarValue() {
		return toolbarValue;
	}
}
