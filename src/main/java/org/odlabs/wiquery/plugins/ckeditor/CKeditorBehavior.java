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

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.ICollectionItemOptions;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.plugins.ckeditor.options.CommandKeyStrokeOption;
import org.odlabs.wiquery.plugins.ckeditor.options.ElementPathListOptions;
import org.odlabs.wiquery.plugins.ckeditor.options.FormatOption;
import org.odlabs.wiquery.plugins.ckeditor.options.KeyStrokeOption;
import org.odlabs.wiquery.plugins.ckeditor.options.ListOptions;
import org.odlabs.wiquery.plugins.ckeditor.options.RegExpOption;
import org.odlabs.wiquery.plugins.ckeditor.options.SizeOption;
import org.odlabs.wiquery.plugins.ckeditor.options.StyleOption;
import org.odlabs.wiquery.plugins.ckeditor.options.SizeOption.SizeMetric;
import org.odlabs.wiquery.plugins.ckeditor.toolbar.CKeditorToolbar;

/**
 * $Id: CKeditorBehavior.java roche.jul $
 * 
 * <p>
 * 	Behavior to load CKeditor resources and instantiate a rich text editor
 * </p>
 * 
 * <p>
 * 	See http://docs.cksource.com/ckeditor_api/symbols/CKEDITOR.config.html
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class CKeditorBehavior extends WiQueryAbstractBehavior {
	/**
	 * 
	 * <p>
	 * 	Enter mode (for the enter and shift enter mode)
	 * </p>
	 *
	 * @author Julien Roche
	 * @since 1.1
	 */
	public enum EnterMode {
		BR,
		DIV,
		P;

		/**
		 * {@inheritDoc}
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return "CKEDITOR.ENTER_" + super.toString();
		}
	}
	
	/**
	 * 
	 * <p>
	 * 	Startup mode
	 * </p>
	 *
	 * @author Julien Roche
	 * @since 1.1
	 */
	public enum StartupMode {
		SOURCE,
		WYSIWYG;
	}
	
	/**
	 * 
	 * <p>
	 * 	Location of the toolbar
	 * </p>
	 *
	 * @author Julien Roche
	 * @since 1.1
	 */
	public enum ToolbarLocation {
		BOTTOM,
		TOP;
	}
	
	// Constants
	/** Constant for the CKeditor resource */
	public static final JavascriptResourceReference WIQUERY_CKEDITOR_JS = 
		new JavascriptResourceReference(CKeditorBehavior.class, "ckeditor.js");
	
	/** Constant for the jQuery CKeditor adapter resource */
	public static final JavascriptResourceReference WIQUERY_CKEDITOR_JQUERY_ADAPTER_JS = 
		new JavascriptResourceReference(CKeditorBehavior.class, "jquery-ckeditor-adapter.js");
	
	/** Constant of serialization */
	private static final long serialVersionUID = 9160233123669245269L;
	
	// Properties
	private Options options;
	
	public CKeditorBehavior() {
		super();
		options = new Options();
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		super.contribute(wiQueryResourceManager);
		wiQueryResourceManager.addJavaScriptResource(WIQUERY_CKEDITOR_JS);
		wiQueryResourceManager.addJavaScriptResource(WIQUERY_CKEDITOR_JQUERY_ADAPTER_JS);
	}
	
	/**
	 * @return the options for the CKEditor
	 */
	protected final Options getCKeditorOptions() {
		return options;
	}

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.behavior.AbstractBehavior#onComponentTag(org.apache.wicket.Component, org.apache.wicket.markup.ComponentTag)
	 */
	@Override
	public void onComponentTag(Component component, ComponentTag tag) {
		if(!tag.getName().equalsIgnoreCase("textarea")){
			throw new MarkupException("The jQuery CKeditor behavior needs a textarea");
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsQuery(getComponent()).$().chain("ckeditor", 
				options.getJavaScriptOptions());
	}
	
	/*---- Options section ---*/
	
	/**
	 * @return the autoUpdateElement option
	 */
	public boolean isAutoUpdateElement() {
		if(options.get("autoUpdateElement") == null){
			return true;
		}
		
		return options.getBoolean("autoUpdateElement");
	}
	
	/**
	 * Whether the replaced element (usually a textarea) is to be updated 
	 * automatically when posting the form containing the editor. 
	 * @param autoUpdateElement
	 * @return the current instance
	 */
	public CKeditorBehavior setAutoUpdateElement(boolean autoUpdateElement) {
		options.put("autoUpdateElement", autoUpdateElement);
		return this;
	}
	
	/**
	 * @return the baseFloatZIndex option
	 */
	public int getBaseFloatZIndex() {
		if(options.get("baseFloatZIndex") == null){
			return 10000;
		}
		
		return options.getInt("baseFloatZIndex");
	}
	
	/**
	 * Method setting the base Z-index for floating dialogs and popups. 
	 * @param baseFloatZIndex
	 * @return the current instance
	 */
	public CKeditorBehavior setBaseFloatZIndex(int baseFloatZIndex) {
		options.put("baseFloatZIndex", baseFloatZIndex);
		return this;
	}
	
	/**
	 * @return the baseHref option
	 */
	public String getBaseHref() {
		String text = options.getLiteral("baseHref");
		return text == null ? "" : text;
	}
	
	/**
	 * Method setting the "class" attribute to be used on the body element 
	 * of the editing area
	 * @param baseHref
	 * @return the current instance
	 */
	public CKeditorBehavior setBasHref(String baseHref) {
		options.putLiteral("baseHref", baseHref);
		return this;
	}
	
	/**
	 * @return the blockedKeystrokes option
	 */
	@SuppressWarnings("unchecked")
	public ArrayItemOptions<KeyStrokeOption> getBlockedKeyStrokes() {
		ICollectionItemOptions coll = options.getListItemOptions("blockedKeystrokes");
		
		if(coll == null){
			ArrayItemOptions<KeyStrokeOption> arr = new ArrayItemOptions<KeyStrokeOption>();
			arr.add(new KeyStrokeOption(66, KeyStrokeOption.KeyStrokeMask.CTRL));
			arr.add(new KeyStrokeOption(73, KeyStrokeOption.KeyStrokeMask.CTRL));
			arr.add(new KeyStrokeOption(85, KeyStrokeOption.KeyStrokeMask.CTRL));
			return arr;
		}
		
		return (ArrayItemOptions<KeyStrokeOption>) coll;
	}
	
	/**
	 * Method setting the list of keystrokes to be blocked
	 * @param blockedKeystrokes
	 * @return the current instance
	 */
	public CKeditorBehavior setBlockedKeyStrokes(ArrayItemOptions<KeyStrokeOption> blockedKeystrokes) {
		options.put("blockedKeystrokes", blockedKeystrokes);
		return this;
	}
	
	/**
	 * @return the bodyClass option
	 */
	public String getBodyClass() {
		String text = options.getLiteral("bodyClass");
		return text == null ? "" : text;
	}
	
	/**
	 * Method setting the "class" attribute to be used on the body element 
	 * of the editing area
	 * @param bodyClass
	 * @return the current instance
	 */
	public CKeditorBehavior setBodyClass(String bodyClass) {
		options.putLiteral("bodyClass", bodyClass);
		return this;
	}
	
	/**
	 * @return the bodyId option
	 */
	public String getBodyId() {
		String text = options.getLiteral("bodyId");
		return text == null ? "" : text;
	}
	
	/**
	 * Method setting the "id" attribute to be used on the body element 
	 * of the editing area
	 * @param bodyId
	 * @return the current instance
	 */
	public CKeditorBehavior setBodyId(String bodyId) {
		options.putLiteral("bodyId", bodyId);
		return this;
	}
	
	/**
	 * @return the browserContextMenuOnCtrl option
	 */
	public boolean isBrowserContextMenuOnCtrl() {
		if(options.get("browserContextMenuOnCtrl") == null){
			return true;
		}
		
		return options.getBoolean("browserContextMenuOnCtrl");
	}
	
	/**
	 * Whether to enable the "More Colors..." button in the color selectors. 
	 * @param browserContextMenuOnCtrl
	 * @return the current instance
	 */
	public CKeditorBehavior setBrowserContextMenuOnCtrl(boolean browserContextMenuOnCtrl) {
		options.put("browserContextMenuOnCtrl", browserContextMenuOnCtrl);
		return this;
	}
	
	/**
	 * @return the removePlugins option
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
	 * Method setting the colors to be displayed in the color selectors.
	 * @param colorButton_colors
	 * @return the current instance
	 */
	public CKeditorBehavior setColorButtonColors(ListOptions colorButton_colors) {
		options.put("colorButton_colors", colorButton_colors);
		return this;
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
	 * Whether to enable the "More Colors..." button in the color selectors. 
	 * @param colorButton_enableMore
	 * @return the current instance
	 */
	public CKeditorBehavior setColorButtonEnableMore(boolean colorButton_enableMore) {
		options.put("colorButton_enableMore", colorButton_enableMore);
		return this;
	}
	
	/**
	 * @return the StyleOption option
	 */
	public StyleOption getColorButtonBackStyle() {
		return (StyleOption) options.getComplexOption("colorButton_backStyle");
	}
	
	/**
	 * Method setting the style definition to be used to apply the text background color
	 * @param colorButton_foreStyle
	 * @return the current instance
	 */
	public CKeditorBehavior setColorButtonBackStyle(StyleOption colorButton_backStyle) {
		options.put("colorButton_backStyle", colorButton_backStyle);
		return this;
	}
	
	/**
	 * @return the StyleOption option
	 */
	public StyleOption getColorButtonForeStyle() {
		return (StyleOption) options.getComplexOption("colorButton_foreStyle");
	}
	
	/**
	 * Method setting the style definition to be used to apply the text foreground color
	 * @param colorButton_foreStyle
	 * @return the current instance
	 */
	public CKeditorBehavior setColorButtonForeStyle(StyleOption colorButton_foreStyle) {
		options.put("colorButton_foreStyle", colorButton_foreStyle);
		return this;
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
	 * Method setting the CSS file(s) to be used to apply style to the contents. 
	 * It should reflect the CSS used in the final pages where the contents are 
	 * to be used. 
	 * @param contentsCss
	 * @return the current instance
	 */
	public CKeditorBehavior setContentsCss(ArrayItemOptions<LiteralOption> contentsCss) {
		options.put("contentsCss", contentsCss);
		return this;
	}
	
	/**
	 * @return the contentsLangDirection option
	 */
	public String getContentsLangDirection() {
		String text = options.getLiteral("contentsLangDirection");
		return text == null ? "ltr" : text;
	}
	
	/**
	 * Method setting the writting direction of the language used to write the 
	 * editor contents. Allowed values are 'ltr' for Left-To-Right language 
	 * (like English), or 'rtl' for Right-To-Left languages (like Arabic). 
	 * @return the current instance
	 */
	public CKeditorBehavior setContentsLangDirection(String contentsLangDirection) {
		options.putLiteral("contentsLangDirection", contentsLangDirection);
		return this;
	}
	
	/**
	 * @return the coreStyles_bold option
	 */
	public FormatOption getCoreStylesBold() {
		return (FormatOption) options.getComplexOption("coreStyles_bold");
	}
	
	/**
	 * Method setting the style definition to be used to apply the strike bold in the text
	 * @param coreStyles_bold
	 * @return the current instance
	 */
	public CKeditorBehavior setCoreStylesBold(FormatOption coreStyles_bold) {
		options.put("coreStyles_bold", coreStyles_bold);
		return this;
	}
	
	/**
	 * @return the coreStyles_italic option
	 */
	public FormatOption getCoreStylesItalic() {
		return (FormatOption) options.getComplexOption("coreStyles_italic");
	}
	
	/**
	 * Method setting the style definition to be used to apply the strike italic in the text
	 * @param coreStyles_italic
	 * @return the current instance
	 */
	public CKeditorBehavior setCoreStylesItalic(FormatOption coreStyles_italic) {
		options.put("coreStyles_italic", coreStyles_italic);
		return this;
	}
	
	/**
	 * @return the coreStyles_strike option
	 */
	public FormatOption getCoreStylesStrike() {
		return (FormatOption) options.getComplexOption("coreStyles_strike");
	}
	
	/**
	 * Method setting the style definition to be used to apply the strike style in the text
	 * @param coreStyles_strike
	 * @return the current instance
	 */
	public CKeditorBehavior setCoreStylesStrike(FormatOption coreStyles_strike) {
		options.put("coreStyles_strike", coreStyles_strike);
		return this;
	}
	
	/**
	 * @return the coreStyles_subscript option
	 */
	public FormatOption getCoreStylesSubscript() {
		return (FormatOption) options.getComplexOption("coreStyles_subscript");
	}
	
	/**
	 * Method setting the style definition to be used to apply the subscript style in the text
	 * @param coreStyles_subscript
	 * @return the current instance
	 */
	public CKeditorBehavior setCoreStylesSubscript(FormatOption coreStyles_subscript) {
		options.put("coreStyles_subscript", coreStyles_subscript);
		return this;
	}
	
	/**
	 * @return the coreStyles_superscript option
	 */
	public FormatOption getCoreStylesSuperscript() {
		return (FormatOption) options.getComplexOption("coreStyles_superscript");
	}
	
	/**
	 * Method setting the style definition to be used to apply the superscript style in the text
	 * @param coreStyles_superscript
	 * @return the current instance
	 */
	public CKeditorBehavior setCoreStylesSuperscript(FormatOption coreStyles_superscript) {
		options.put("coreStyles_superscript", coreStyles_superscript);
		return this;
	}
	
	/**
	 * @return the coreStyles_underline option
	 */
	public FormatOption getCoreStylesUnderline() {
		return (FormatOption) options.getComplexOption("coreStyles_underline");
	}
	
	/**
	 * Method setting the style definition to be used to apply the underline style in the text
	 * in the text. 
	 * @param coreStyles_underline
	 * @return the current instance
	 */
	public CKeditorBehavior setCoreStylesUnderline(FormatOption coreStyles_underline) {
		options.put("coreStyles_underline", coreStyles_underline);
		return this;
	}
	
	/**
	 * @return the customConfig option
	 */
	public String getCustomConfig() {
		String text = options.getLiteral("customConfig");
		return text == null ? "<CKEditor folder>/config.js" : text;
	}
	
	/**
	 * Method setting the language to be used if CKEDITOR.config.language  is 
	 * left empty and it's not possible to localize the editor to the user language. 
	 * @param customConfig
	 * @return the current instance
	 */
	public CKeditorBehavior setCustomConfig(String customConfig) {
		options.putLiteral("customConfig", customConfig);
		return this;
	}
	
	/**
	 * @return the defaultLanguage option
	 */
	public String getDefaultLanguage() {
		String text = options.getLiteral("defaultLanguage");
		return text == null ? "en" : text;
	}
	
	/**
	 * Method setting the language to be used if CKEDITOR.config.language  is 
	 * left empty and it's not possible to localize the editor to the user language. 
	 * @param defaultLanguage
	 * @return the current instance
	 */
	public CKeditorBehavior setDefaultLanguage(String defaultLanguage) {
		options.putLiteral("defaultLanguage", defaultLanguage);
		return this;
	}
	
	/**
	 * @return the dialog_backgroundCoverColor option
	 */
	public String getDialogBackgroundCoverColor() {
		String text = options.getLiteral("dialog_backgroundCoverColor");
		return text == null ? "white" : text;
	}
	
	/**
	 * Method setting the color of the dialog background cover. It should be 
	 * a valid CSS color string 
	 * @param dialog_backgroundCoverColor
	 * @return the current instance
	 */
	public CKeditorBehavior setDialogBackgroundCoverColor(String dialog_backgroundCoverColor) {
		options.putLiteral("dialog_backgroundCoverColor", dialog_backgroundCoverColor);
		return this;
	}
	
	/**
	 * @return the dialog_magnetDistance option
	 */
	public float getDialogBackgroundCoverOpacity() {
		if(options.get("dialog_backgroundCoverOpacity") == null){
			return 0.5F;
		}
		
		return options.getFloat("dialog_backgroundCoverOpacity");
	}
	
	/**
	 * Method setting theopacity of the dialog background cover. It should be 
	 * a number within the range [0.0, 1.0]. 
	 * @param dialog_backgroundCoverOpacity
	 * @return the current instance
	 */
	public CKeditorBehavior setDialogBackgroundCoverOpacity(float dialog_backgroundCoverOpacity) {
		options.put("dialog_backgroundCoverOpacity", dialog_backgroundCoverOpacity);
		return this;
	}
	
	/**
	 * @return the dialog_magnetDistance option
	 */
	public int getDialogMagnetDistance() {
		if(options.get("dialog_magnetDistance") == null){
			return 20;
		}
		
		return options.getInt("dialog_magnetDistance");
	}
	
	/**
	 * Method setting the distance of magnetic borders used in moving and 
	 * resizing dialogs, measured in pixels. 
	 * @param dialog_magnetDistance
	 * @return the current instance
	 */
	public CKeditorBehavior setDialogMagnetDistance(int dialog_magnetDistance) {
		options.put("dialog_magnetDistance", dialog_magnetDistance);
		return this;
	}
	
	/**
	 * @return the dialog_startupFocusTab option
	 */
	public boolean isDialogStartupFocusTab() {
		if(options.get("dialog_startupFocusTab") == null){
			return false;
		}
		
		return options.getBoolean("dialog_startupFocusTab");
	}
	
	/**
	 * Method to put focus into the first tab as soon as dialog is opened. 
	 * @param dialog_startupFocusTab
	 * @return the current instance
	 */
	public CKeditorBehavior setDialogStartupFocusTab(boolean dialog_startupFocusTab) {
		options.put("dialog_startupFocusTab", dialog_startupFocusTab);
		return this;
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
	 * Method to disable the built-in spell checker while typing natively available 
	 * in the browser (currently Firefox and Safari only).
	 * @param disableNativeSpellChecker
	 * @return the current instance
	 */
	public CKeditorBehavior setDisableNativeSpellChecker(boolean disableNativeSpellChecker) {
		options.put("disableNativeSpellChecker", disableNativeSpellChecker);
		return this;
	}
	
	/**
	 * @return the disableNativeTableHandles option
	 */
	public boolean isDisableNativeTableHandles() {
		if(options.get("disableNativeTableHandles") == null){
			return true;
		}
		
		return options.getBoolean("disableNativeTableHandles");
	}
	
	/**
	 * Method to disable the "table tools" offered natively by the browser 
	 * (currently Firefox only) to make quick table editing operations, like 
	 * adding or deleting rows and columns. 
	 * @param disableNativeTableHandles
	 * @return the current instance
	 */
	public CKeditorBehavior setDisableNativeTableHandles(boolean disableNativeTableHandles) {
		options.put("disableNativeTableHandles", disableNativeTableHandles);
		return this;
	}
	
	/**
	 * @return the disableObjectResizing option
	 */
	public boolean isDisableObjectResizing() {
		if(options.get("disableObjectResizing") == null){
			return false;
		}
		
		return options.getBoolean("disableObjectResizing");
	}
	
	/**
	 * Method to disable the ability of resize objects (image and tables) in the 
	 * editing area 
	 * @param disableObjectResizing
	 * @return the current instance
	 */
	public CKeditorBehavior setDisableObjectResizing(boolean disableObjectResizing) {
		options.put("disableObjectResizing", disableObjectResizing);
		return this;
	}
	
	/**
	 * @return the docType option
	 */
	public String getDocType() {
		String text = options.getLiteral("docType");
		return text == null ? "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" : text;
	}
	
	/**
	 * Method setting the doctype to be used when loading the editor content as HTML 
	 * @param docType
	 * @return the current instance
	 */
	public CKeditorBehavior setDocType(String docType) {
		options.putLiteral("docType", docType);
		return this;
	}
	
	/**
	 * @return the editingBlock option
	 */
	public boolean isEditingBlock() {
		if(options.get("editingBlock") == null){
			return true;
		}
		
		return options.getBoolean("editingBlock");
	}
	
	/**
	 * Whether to render or not the editing block area in the editor interface. 
	 * @param editingBlock
	 * @return the current instance
	 */
	public CKeditorBehavior setEditingBlock(boolean editingBlock) {
		options.put("editingBlock", editingBlock);
		return this;
	}
	
	/**
	 * @return the elementsPath_filters option
	 */
	public ElementPathListOptions getElementsPathFilters() {
		Object object = options.get("elementsPath_filters");
		return object == null ? new ElementPathListOptions() : (ElementPathListOptions) object;
	}
	
	/**
	 * Method setting the list of filter functions to determinate whether an 
	 * element should display in elements path bar. 
	 * @param elementsPath_filters
	 * @return the current instance
	 */
	public CKeditorBehavior setElementsPathFilters(ElementPathListOptions elementsPath_filters) {
		options.put("elementsPath_filters", elementsPath_filters);
		return this;
	}
	
	/**
	 * @return the emailProtection option
	 */
	public String getEmailProtection() {
		String text = options.getLiteral("emailProtection");
		return text == null ? "" : text;
	}
	
	/**
	 * Method setting the e-mail address anti-spam protection option. The 
	 * protection will be applied when creating or modifying e-mail links 
	 * through the editor interface. 
	 * @param emailProtection
	 * @return the current instance
	 */
	public CKeditorBehavior setEmailProtection(String emailProtection) {
		options.putLiteral("emailProtection", emailProtection);
		return this;
	}
	
	/**
	 * @return the enterMode option
	 */
	public EnterMode getEnterMode() {
		if(options.get("enterMode") == null){
			return EnterMode.P;
		}
		
		return EnterMode.valueOf(options.get("enterMode").toUpperCase());
	}
	
	/**Method to define the enter mode
	 * @param enterMode
	 * @return the current instance
	 */
	public CKeditorBehavior setEnterMode(EnterMode enterMode) {
		options.put("enterMode", enterMode.toString().toLowerCase());
		return this;
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
	 * Whether to convert some symbols, mathematical symbols, and Greek letters 
	 * to HTML entities.
	 * @param entities_greek
	 * @return the current instance
	 */
	public CKeditorBehavior setEntitiesGreek(boolean entities_greek) {
		options.put("entities_greek", entities_greek);
		return this;
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
	 * Whether to convert some Latin characters (Latin alphabet No. 1, ISO 8859-1) 
	 * to HTML entities.
	 * @param entities_latin
	 * @return the current instance
	 */
	public CKeditorBehavior setEntitiesLatin(boolean entities_latin) {
		options.put("entities_latin", entities_latin);
		return this;
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
	 * Whether to convert all remaining characters, not comprised in the ASCII 
	 * character table, to their relative numeric representation of HTML entity.
	 * @param entities_processNumerical
	 * @return the current instance
	 */
	public CKeditorBehavior setEntitiesProcessNumerical(boolean entities_processNumerical) {
		options.put("entities_processNumerical", entities_processNumerical);
		return this;
	}
	
	/**
	 * @return the extraPlugins option
	 */
	public String getExtraPlugins() {
		String text = options.getLiteral("extraPlugins");
		return text == null ? "myplugin,anotherplugin" : text;
	}
	
	/**
	 * Method setting the list of additional plugins to be loaded.. 
	 * @param extraPlugins
	 * @return the current instance
	 */
	public CKeditorBehavior setExtraPlugins(String extraPlugins) {
		options.putLiteral("extraPlugins", extraPlugins);
		return this;
	}
	
	/**
	 * @return the filebrowserBrowseUrl option
	 */
	public String getFilebrowserBrowseUrl() {
		String text = options.getLiteral("filebrowserBrowseUrl");
		return text == null ? "" : text;
	}
	
	/**
	 * Method setting the location of an external file browser, that should be 
	 * launched when "Browse Server" button is pressed. If configured, the 
	 * "Browse Server" button will appear in Link, Image and Flash dialogs. 
	 * @param filebrowserBrowseUrl
	 * @return the current instance
	 */
	public CKeditorBehavior setFilebrowserBrowseUrl(String filebrowserBrowseUrl) {
		options.putLiteral("filebrowserBrowseUrl", filebrowserBrowseUrl);
		return this;
	}
	
	/**
	 * @return the filebrowserFlashBrowseUrl option
	 */
	public String getFilebrowserFlashBrowseUrl() {
		String text = options.getLiteral("filebrowserFlashBrowseUrl");
		return text == null ? "" : text;
	}
	
	/**
	 * Method setting the location of an external file browser, that should be 
	 * launched when "Browse Server" button is pressed in the Flash dialog.
	 * @param filebrowserFlashBrowseUrl
	 * @return the current instance
	 */
	public CKeditorBehavior setFilebrowserFlashBrowseUrl(String filebrowserFlashBrowseUrl) {
		options.putLiteral("filebrowserFlashBrowseUrl", filebrowserFlashBrowseUrl);
		return this;
	}
	
	/**
	 * @return the filebrowserFlashUploadUrl option
	 */
	public String getFilebrowserFlashUploadUrl() {
		String text = options.getLiteral("filebrowserFlashUploadUrl");
		return text == null ? "" : text;
	}
	
	/**
	 * Method setting the location of a script that handles file uploads in the 
	 * Flash dialog.
	 * @param filebrowserFlashUploadUrl
	 * @return the current instance
	 */
	public CKeditorBehavior setFilebrowserFlashUploadUrl(String filebrowserFlashUploadUrl) {
		options.putLiteral("filebrowserFlashUploadUrl", filebrowserFlashUploadUrl);
		return this;
	}
	
	/**
	 * @return the filebrowserImageBrowseLinkUrl option
	 */
	public String getFilebrowserImageBrowseLinkUrl() {
		String text = options.getLiteral("filebrowserImageBrowseLinkUrl");
		return text == null ? "" : text;
	}
	
	/**
	 * Method setting the location of an external file browser, that should be 
	 * launched when "Browse Server" button is pressed in the Link tab of Image dialog.
	 * @param filebrowserImageBrowseLinkUrl
	 * @return the current instance
	 */
	public CKeditorBehavior setFilebrowserBrowseLinkUrl(String filebrowserImageBrowseLinkUrl) {
		options.putLiteral("filebrowserImageBrowseLinkUrl", filebrowserImageBrowseLinkUrl);
		return this;
	}
	
	/**
	 * @return the filebrowserImageBrowseUrl option
	 */
	public String getFilebrowserImageBrowseUrl() {
		String text = options.getLiteral("filebrowserImageBrowseUrl");
		return text == null ? "" : text;
	}
	
	/**
	 * Method setting the location of an external file browser, that should be 
	 * launched when "Browse Server" button is pressed in the Image dialog.
	 * @param filebrowserImageBrowseUrl
	 * @return the current instance
	 */
	public CKeditorBehavior setFilebrowserImageBrowsedUrl(String filebrowserImageBrowseUrl) {
		options.putLiteral("filebrowserImageBrowseUrl", filebrowserImageBrowseUrl);
		return this;
	}
	
	/**
	 * @return the filebrowserImageUploadUrl option
	 */
	public String getFilebrowserImageUploadUrl() {
		String text = options.getLiteral("filebrowserImageUploadUrl");
		return text == null ? "" : text;
	}
	
	/**
	 * Method setting the location of a script that handles file uploads in the 
	 * Image dialog. 
	 * @param filebrowserImageUploadUrl
	 * @return the current instance
	 */
	public CKeditorBehavior setFilebrowserImageUploadUrl(String filebrowserImageUploadUrl) {
		options.putLiteral("filebrowserImageUploadUrl", filebrowserImageUploadUrl);
		return this;
	}
	
	/**
	 * @return the filebrowserUploadUrl option
	 */
	public String getFilebrowserUploadUrl() {
		String text = options.getLiteral("filebrowserUploadUrl");
		return text == null ? "" : text;
	}
	
	/**
	 * Method setting the location of a script that handles file uploads. If set, 
	 * the "Upload" tab will appear in "Link", "Image" and "Flash" dialogs. 
	 * @param filebrowserUploadUrl
	 * @return the current instance
	 */
	public CKeditorBehavior setFilebrowserUploadUrl(String filebrowserUploadUrl) {
		options.putLiteral("filebrowserUploadUrl", filebrowserUploadUrl);
		return this;
	}
	
	/**
	 * @return the find_highlight option
	 */
	public StyleOption getFindHighLight() {
		return (StyleOption) options.getComplexOption("find_highlight");
	}
	
	/**
	 * Method setting the style to be used to highlight results with the find dialog 
	 * in the text. 
	 * @param find_highlight
	 * @return the current instance
	 */
	public CKeditorBehavior setFindHighLight(StyleOption find_highlight) {
		options.put("find_highlight", find_highlight);
		return this;
	}
	
	/**
	 * @return the font_defaultLabel option
	 */
	public String getFontDefaultLabel() {
		String text = options.getLiteral("font_defaultLabel");
		return text == null ? "Arial" : text;
	}
	
	/**
	 * Method setting the text to be displayed in the Font combo is none of the 
	 * available values matches the current cursor position or text selection. 
	 * @param font_defaultLabel
	 * @return the current instance
	 */
	public CKeditorBehavior setFontDefaultLabel(String font_defaultLabel) {
		options.putLiteral("font_defaultLabel", font_defaultLabel);
		return this;
	}
	
	/**
	 * @return the font_names option
	 */
	public String getFontNames() {
		String text = options.getLiteral("font_names");
		return text == null ? "Arial;Times New Roman;Verdana" : text;
	}
	
	/**
	 * Method setting the list of fonts names to be displayed in the Font combo 
	 * in the toolbar 
	 * @param font_names
	 * @return the current instance
	 */
	public CKeditorBehavior setFontNames(String font_names) {
		options.putLiteral("font_names", font_names);
		return this;
	}
	
	/**
	 * @return the font_style option
	 */
	public StyleOption getFontStyle() {
		return (StyleOption) options.getComplexOption("font_style");
	}
	
	/**
	 * Method setting the style definition to be used to apply the font in the text. 
	 * in the text. 
	 * @param font_style
	 * @return the current instance
	 */
	public CKeditorBehavior setFontStyle(StyleOption font_style) {
		options.put("font_style", font_style);
		return this;
	}
	
	/**
	 * @return the fontSize_defaultLabel option
	 */
	public String getFontSizeDefaultLabel() {
		String text = options.getLiteral("fontSize_defaultLabel");
		return text == null ? "12px" : text;
	}
	
	/**
	 * Method setting a text to be displayed in the Font Size combo is none of the 
	 * available values matches the current cursor position or text selection. 
	 * @param fontSize_defaultLabel
	 * @return the current instance
	 */
	public CKeditorBehavior setFontSizeDefaultLabel(String fontSize_defaultLabel) {
		options.putLiteral("fontSize_defaultLabel", fontSize_defaultLabel);
		return this;
	}
	
	/**
	 * @return the fontSize_sizes option
	 */
	public String getFontSizeSizes() {
		String text = options.getLiteral("fontSize_sizes");
		return text == null ? "8/8px;9/9px;10/10px;11/11px;12/12px;14/14px;16/16px;18/18px;20/20px;22/22px;24/24px;26/26px;28/28px;36/36px;48/48px;72/72px" : text;
	}
	
	/**
	 * Method setting a list of fonts size to be displayed in the Font Size combo 
	 * in the toolbar. 
	 * @param fontSize_sizes
	 * @return the current instance
	 */
	public CKeditorBehavior setFontSizeSizes(String fontSize_sizes) {
		options.putLiteral("fontSize_sizes", fontSize_sizes);
		return this;
	}
	
	/**
	 * @return the fontSize_style option
	 */
	public StyleOption getFontSizeStyle() {
		return (StyleOption) options.getComplexOption("fontSize_style");
	}
	
	/**
	 * Method setting the style definition to be used to apply the font size 
	 * in the text. 
	 * @param fontSize_style
	 * @return the current instance
	 */
	public CKeditorBehavior setFontSizeStyle(StyleOption fontSize_style) {
		options.put("fontSize_style", fontSize_style);
		return this;
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
	 * Method to to force all pasting operations to insert on plain text into the 
	 * editor, loosing any formatting information possibly available in the source text. 
	 * @param forcePasteAsPlainText
	 * @return the current instance
	 */
	public CKeditorBehavior setForcePasteAsPlainText(boolean forcePasteAsPlainText) {
		options.put("forcePasteAsPlainText", forcePasteAsPlainText);
		return this;
	}
	
	/**
	 * @return the format_address
	 */
	public FormatOption getFormatAddress() {
		return (FormatOption) options.getComplexOption("format_address");
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Address" format. 
	 * @param format_address
	 * @return the current instance
	 */
	public CKeditorBehavior setFormatAddress(FormatOption format_address) {
		options.put("format_address", format_address);
		return this;
	}
	
	/**
	 * @return the format_div
	 */
	public FormatOption getFormatDiv() {
		return (FormatOption) options.getComplexOption("format_div");
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Normal (DIV)" format. 
	 * @param format_div
	 * @return the current instance
	 */
	public CKeditorBehavior setFormatDiv(FormatOption format_div) {
		options.put("format_div", format_div);
		return this;
	}
	
	/**
	 * @return the format_h1
	 */
	public FormatOption getFormatH1() {
		return (FormatOption) options.getComplexOption("format_h1");
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Heading 1" format. 
	 * @param format_h1
	 * @return the current instance
	 */
	public CKeditorBehavior setFormatH1(FormatOption format_h1) {
		options.put("format_h1", format_h1);
		return this;
	}
	
	/**
	 * @return the format_h2
	 */
	public FormatOption getFormatH2() {
		return (FormatOption) options.getComplexOption("format_h2");
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Heading 2" format. 
	 * @param format_h2
	 * @return the current instance
	 */
	public CKeditorBehavior setFormatH2(FormatOption format_h2) {
		options.put("format_h2", format_h2);
		return this;
	}
	
	/**
	 * @return the format_h3
	 */
	public FormatOption getFormatH3() {
		return (FormatOption) options.getComplexOption("format_h3");
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Heading 3" format. 
	 * @param format_h3
	 * @return the current instance
	 */
	public CKeditorBehavior setFormatH3(FormatOption format_h3) {
		options.put("format_h3", format_h3);
		return this;
	}
	
	/**
	 * @return the format_h4
	 */
	public FormatOption getFormatH4() {
		return (FormatOption) options.getComplexOption("format_h4");
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Heading 4" format. 
	 * @param format_h4
	 * @return the current instance
	 */
	public CKeditorBehavior setFormatH4(FormatOption format_h4) {
		options.put("format_h4", format_h4);
		return this;
	}
	
	/**
	 * @return the format_h5
	 */
	public FormatOption getFormatH5() {
		return (FormatOption) options.getComplexOption("format_h5");
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Heading 5" format. 
	 * @param format_h5
	 * @return the current instance
	 */
	public CKeditorBehavior setFormatH5(FormatOption format_h5) {
		options.put("format_h5", format_h5);
		return this;
	}
	
	/**
	 * @return the format_h6
	 */
	public FormatOption getFormatH6() {
		return (FormatOption) options.getComplexOption("format_h6");
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Heading 6" format. 
	 * @param format_h6
	 * @return the current instance
	 */
	public CKeditorBehavior setFormatH6(FormatOption format_h6) {
		options.put("format_h6", format_h6);
		return this;
	}
	
	/**
	 * @return the format_p
	 */
	public FormatOption getFormatP() {
		return (FormatOption) options.getComplexOption("format_p");
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Normal" format. 
	 * @param format_p
	 * @return the current instance
	 */
	public CKeditorBehavior setFormatP(FormatOption format_p) {
		options.put("format_p", format_p);
		return this;
	}
	
	/**
	 * @return the format_pre
	 */
	public FormatOption getFormatPre() {
		return (FormatOption) options.getComplexOption("format_pre");
	}
	
	/**
	 * Method setting the style definition to be used to apply the "Formatted" format. 
	 * @param format_pre
	 * @return the current instance
	 */
	public CKeditorBehavior setFormatPre(FormatOption format_pre) {
		options.put("format_pre", format_pre);
		return this;
	}
	
	/**
	 * @return the format_tags option
	 */
	public String getFormatTags() {
		String text = options.getLiteral("format_tags");
		return text == null ? "p;h1;h2;h3;h4;h5;h6;pre;address;div" : text;
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
	public CKeditorBehavior setFormatTags(String format_tags) {
		options.putLiteral("format_tags", format_tags);
		return this;
	}
	
	/**
	 * @return the fullPage option
	 */
	public boolean isFullPage() {
		if(options.get("fullPage") == null){
			return false;
		}
		
		return options.getBoolean("fullPage");
	}
	
	/**
	 * Method Indicating whether the contents to be edited are being inputted as 
	 * a full HTML page. A full page includes the <html>, <head> and <body> tags. 
	 * The final output will also reflect this setting, including the <body> 
	 * contents only if this setting is disabled. 
	 * @param fullPage
	 * @return the current instance
	 */
	public CKeditorBehavior setFullPage(boolean fullPage) {
		options.put("fullPage", fullPage);
		return this;
	}
	
	/**
	 * @return the htmlEncodeOutput option
	 */
	public boolean isHtmlEncodeOutput() {
		if(options.get("htmlEncodeOutput") == null){
			return false;
		}
		
		return options.getBoolean("htmlEncodeOutput");
	}
	
	/**
	 * Whether escape HTML when editor update original input element.
	 * @param htmlEncodeOutput
	 * @return the current instance
	 */
	public CKeditorBehavior setHtmlEncodeOutput(boolean htmlEncodeOutput) {
		options.put("htmlEncodeOutput", htmlEncodeOutput);
		return this;
	}
	
	/**
	 * @return the ignoreEmptyParagraph option
	 */
	public boolean isIgnoreEmptyParagraph() {
		if(options.get("ignoreEmptyParagraph") == null){
			return true;
		}
		
		return options.getBoolean("ignoreEmptyParagraph");
	}
	
	/**
	 * Method setting the the editor must output an empty value ("") if it's 
	 * contents is made by an empty paragraph only.  
	 * @param ignoreEmptyParagraph
	 * @return the current instance
	 */
	public CKeditorBehavior setIgnoreEmptyParagraph(boolean ignoreEmptyParagraph) {
		options.put("ignoreEmptyParagraph", ignoreEmptyParagraph);
		return this;
	}
	
	/**
	 * @return the height option
	 */
	public SizeOption getHeight() {
		SizeOption height = (SizeOption) options.getComplexOption("height");
		return height == null ? new SizeOption(200, SizeMetric.PIXEL) : height;
	}
	
	/**
	 * Method setting the height in CSS size format or pixel integer
	 * @param height
	 * @return the current instance
	 */
	public CKeditorBehavior setHeight(SizeOption height) {
		options.put("height", height);
		return this;
	}
	
	/**
	 * @return the image_previewText option
	 */
	public String getImagePreviewText() {
		String text = options.getLiteral("image_previewText");
		return text == null ? "Lorem ipsum dolor..." : text;
	}
	
	/**
	 * Method setting the padding text to set off the image in preview area
	 * @param image_previewText
	 * @return the current instance
	 */
	public CKeditorBehavior setImagePreviewText(String image_previewText) {
		options.putLiteral("image_previewText", image_previewText);
		return this;
	}
	
	/**
	 * @return the image_removeLinkByEmptyURL option
	 */
	public boolean isImageRemoveLinkByEmptyURL() {
		if(options.get("image_removeLinkByEmptyURL") == null){
			return true;
		}
		
		return options.getBoolean("image_removeLinkByEmptyURL");
	}
	
	/**
	 * Whether to remove links when emptying the link URL field in the image dialog. 
	 * @param image_removeLinkByEmptyURL
	 * @return the current instance
	 */
	public CKeditorBehavior setImageRemoveLinkByEmptyURL(boolean image_removeLinkByEmptyURL) {
		options.put("image_removeLinkByEmptyURL", image_removeLinkByEmptyURL);
		return this;
	}
	
	/**
	 * @return the language option
	 */
	public String getLanguage() {
		String text = options.getLiteral("language");
		return text == null ? "" : text;
	}
	
	/**
	 * @return the keystrokes option
	 */
	@SuppressWarnings("unchecked")
	public ArrayItemOptions<CommandKeyStrokeOption> getKeyStrokes() {
		ICollectionItemOptions coll = options.getListItemOptions("keystrokes");
		
		if(coll == null){
			ArrayItemOptions<CommandKeyStrokeOption> arr = new ArrayItemOptions<CommandKeyStrokeOption>();
			arr.add(new CommandKeyStrokeOption("toolbarFocus", 121, KeyStrokeOption.KeyStrokeMask.ALT));
			arr.add(new CommandKeyStrokeOption("elementsPathFocus", 122, KeyStrokeOption.KeyStrokeMask.ALT));
			arr.add(new CommandKeyStrokeOption("contextMenu", 121, KeyStrokeOption.KeyStrokeMask.SHIFT));
			arr.add(new CommandKeyStrokeOption("undo", 90, KeyStrokeOption.KeyStrokeMask.CTRL));
			arr.add(new CommandKeyStrokeOption("redo", 89, KeyStrokeOption.KeyStrokeMask.CTRL));
			arr.add(new CommandKeyStrokeOption("redo", 121, KeyStrokeOption.KeyStrokeMask.CTRL, KeyStrokeOption.KeyStrokeMask.SHIFT));
			arr.add(new CommandKeyStrokeOption("link", 76, KeyStrokeOption.KeyStrokeMask.CTRL));
			arr.add(new CommandKeyStrokeOption("bold", 66, KeyStrokeOption.KeyStrokeMask.CTRL));
			arr.add(new CommandKeyStrokeOption("italic", 73, KeyStrokeOption.KeyStrokeMask.CTRL));
			arr.add(new CommandKeyStrokeOption("underline", 85, KeyStrokeOption.KeyStrokeMask.CTRL));
			arr.add(new CommandKeyStrokeOption("toolbarCollapse", 109, KeyStrokeOption.KeyStrokeMask.ALT));
			return arr;
		}
		
		return (ArrayItemOptions<CommandKeyStrokeOption>) coll;
	}
	
	/**
	 * Method setting the list associating keystrokes to editor commands.
	 * @param keystrokes
	 * @return the current instance
	 */
	public CKeditorBehavior setKeyStrokes(ArrayItemOptions<CommandKeyStrokeOption> keystrokes) {
		options.put("keystrokes", keystrokes);
		return this;
	}
	
	/**
	 * Method setting the user interface language localization to use. If empty, 
	 * the editor automatically localize the editor to the user language, if supported
	 * @param language
	 * @return the current instance
	 */
	public CKeditorBehavior setLanguage(String language) {
		options.putLiteral("language", language);
		return this;
	}
	
	/**
	 * @return the menu_groups option
	 */
	public String getMenuGroups() {
		String text = options.getLiteral("menu_groups");
		return text == null ? "clipboard,form,tablecell,tablecellproperties,tablerow,tablecolumn,table,anchor,link,image,flash,checkbox,radio,textfield,hiddenfield,imagebutton,button,select,textarea" : text;
	}
	
	/**
	 * Method setting the list of items group names to be displayed in the 
	 * context menu. The items order will reflect the order in this list if no 
	 * priority has been definted in the groups. 
	 * @param menu_groups
	 * @return the current instance
	 */
	public CKeditorBehavior setMenuGroups(String menu_groups) {
		options.putLiteral("menu_groups", menu_groups);
		return this;
	}
	
	/**
	 * @return the menu_subMenuDelay option
	 */
	public int getMenuSubMenuDelay() {
		if(options.get("menu_subMenuDelay") == null){
			return 400;
		}
		
		return options.getInt("menu_subMenuDelay");
	}
	
	/**
	 * Method setting the amount of time, in milliseconds, the editor waits 
	 * before showing submenu options when moving the mouse over options that 
	 * contains submenus, like the "Cell Properties" entry for tables. 
	 * @param menu_subMenuDelay
	 * @return the current instance
	 */
	public CKeditorBehavior setMenuSubMenuDelay(int menu_subMenuDelay) {
		options.put("menu_subMenuDelay", menu_subMenuDelay);
		return this;
	}
	
	/**
	 * @return the newpage_html option
	 */
	public String getNewPageHtml() {
		String text = options.getLiteral("newpage_html");
		return text == null ? "" : text;
	}
	
	/**
	 * Method setting the default content of the editor 
	 * It must end with a slash
	 * @param newpage_html
	 * @return the current instance
	 */
	public CKeditorBehavior setNewPageHtml(String newpage_html) {
		options.putLiteral("newpage_html", newpage_html);
		return this;
	}
	
	/**
	 * @return the pasteFromWordCleanupFile option
	 */
	public String getPasteFromWordCleanupFile() {
		String text = options.getLiteral("pasteFromWordCleanupFile");
		return text == null ? "default" : text;
	}
	
	/**
	 * Method setting the file that provides the MS Word cleanup function for 
	 * pasting operations.  
	 * @param pasteFromWordCleanupFile
	 * @return the current instance
	 */
	public CKeditorBehavior setPasteFromWordCleanupFile(String pasteFromWordCleanupFile) {
		options.putLiteral("pasteFromWordCleanupFile", pasteFromWordCleanupFile);
		return this;
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
	 * Method to define if text pasted from Word must be cleaned
	 * @param pasteFromWordPromptCleanup
	 * @return the current instance
	 */
	public CKeditorBehavior setPasteFromWordPromptCleanup(boolean pasteFromWordPromptCleanup) {
		options.put("pasteFromWordPromptCleanup", pasteFromWordPromptCleanup);
		return this;
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
	 * Method to define if text pasted from Word must be cleaned
	 * @param pasteFromWordRemoveFontStyles
	 * @return the current instance
	 */
	public CKeditorBehavior setPasteFromWordRemoveFontStyles(boolean pasteFromWordRemoveFontStyles) {
		options.put("pasteFromWordRemoveFontStyles", pasteFromWordRemoveFontStyles);
		return this;
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
	 * Method to define if text pasted from Word must be cleaned
	 * @param pasteFromWordRemoveStyles
	 * @return the current instance
	 */
	public CKeditorBehavior setPasteFromWordRemoveStyles(boolean pasteFromWordRemoveStyles) {
		options.put("pasteFromWordRemoveStyles", pasteFromWordRemoveStyles);
		return this;
	}
	
	/**
	 * @return the protectedSource option
	 */
	@SuppressWarnings("unchecked")
	public ArrayItemOptions<RegExpOption> getProtectedSource() {
		ICollectionItemOptions coll = options.getListItemOptions("protectedSource");
		
		return coll == null ? new ArrayItemOptions<RegExpOption>() : 
			(ArrayItemOptions<RegExpOption>) coll;
	}
	
	/**
	 * Method setting the description to be used for each of the smileys
	 * @param protectedSource
	 * @return the current instance
	 */
	public CKeditorBehavior setProtectedSource(ArrayItemOptions<RegExpOption> protectedSource) {
		options.put("protectedSource", protectedSource);
		return this;
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
	 * Method setting a comma separated list of elements to be removed when 
	 * executing the "remove " format" command. 
	 * @param removeFormatTags
	 * @return the current instance
	 */
	public CKeditorBehavior setRemoveFormatTags(ListOptions removeFormatTags) {
		options.put("removeFormatTags", removeFormatTags);
		return this;
	}
	
	/**
	 * @return the removePlugins option
	 */
	public ListOptions getRemovePlugins() {
		Object object = options.getComplexOption("removePlugins");
		
		if(object == null){
			return ListOptions.asList("elementspath", "save", "font");
		}
		
		return (ListOptions) object;
	}
	
	/**
	 * Method setting the list of plugins that must not be loaded.
	 * @param removePlugins
	 * @return the current instance
	 */
	public CKeditorBehavior setRemovePlugins(ListOptions removePlugins) {
		options.put("removePlugins", removePlugins);
		return this;
	}
	
	/**
	 * @return the resize_enabled option
	 */
	public boolean isResizeEnabled() {
		if(options.get("resize_enabled") == null){
			return true;
		}
		
		return options.getBoolean("resize_enabled");
	}
	
	/**
	 * Method to define if the editor an be resized
	 * @param resize_enabled
	 * @return the current instance
	 */
	public CKeditorBehavior setResizeEnabled(boolean resize_enabled) {
		options.put("resize_enabled", resize_enabled);
		return this;
	}
	
	/**
	 * @return the resize_maxHeight option
	 */
	public int getResizeMaxHeight() {
		if(options.get("resize_maxHeight") == null){
			return 3000;
		}
		
		return options.getInt("resize_maxHeight");
	}
	
	/**
	 * Method setting the max height of the editor
	 * @param resize_maxHeight
	 * @return the current instance
	 */
	public CKeditorBehavior setResizeMaxHeight(int resize_maxHeight) {
		options.put("resize_maxHeight", resize_maxHeight);
		return this;
	}
	
	/**
	 * @return the resize_maxWidth option
	 */
	public int getResizeMaxWidth() {
		if(options.get("resize_maxWidth") == null){
			return 3000;
		}
		
		return options.getInt("resize_maxWidth");
	}
	
	/**
	 * Method setting the max width of the editor
	 * @param resize_maxWidth
	 * @return the current instance
	 */
	public CKeditorBehavior setResizeMaxWidth(int resize_maxWidth) {
		options.put("resize_maxWidth", resize_maxWidth);
		return this;
	}
	
	/**
	 * @return the shiftEnterMode option
	 */
	public EnterMode getShiftEnterMode() {
		if(options.get("shiftEnterMode") == null){
			return EnterMode.P;
		}
		
		return EnterMode.valueOf(options.get("shiftEnterMode").toUpperCase());
	}
	
	/**Method to define the enter mode
	 * @param shiftEnterMode
	 * @return the current instance
	 */
	public CKeditorBehavior setShiftEnterMode(EnterMode shiftEnterMode) {
		options.put("shiftEnterMode", shiftEnterMode.toString().toLowerCase());
		return this;
	}
	
	/**
	 * @return the skin option
	 */
	public String getSkin() {
		String skin = options.getLiteral("skin");
		return skin == null ? "myskin,/customstuff/myskin/" : skin;
	}
	
	/**
	 * Method setting the base path used to build the URL for the smiley images. 
	 * It must end with a slash
	 * @param skin
	 * @return the current instance
	 */
	public CKeditorBehavior setSkin(String skin) {
		options.putLiteral("skin", skin);
		return this;
	}
	
	/**
	 * @return the smiley_descriptions option
	 */
	@SuppressWarnings("unchecked")
	public ArrayItemOptions<LiteralOption> getSmileyDescriptions() {
		ICollectionItemOptions coll = options.getListItemOptions("smiley_descriptions");
		
		if(coll == null){
			ArrayItemOptions<LiteralOption> arr = new ArrayItemOptions<LiteralOption>();
			arr.add(new LiteralOption("smiley"));
			arr.add(new LiteralOption("sad"));
			arr.add(new LiteralOption("wink"));
			arr.add(new LiteralOption("laugh"));
			arr.add(new LiteralOption("frown"));
			arr.add(new LiteralOption("cheeky"));
			arr.add(new LiteralOption("blush"));
			arr.add(new LiteralOption("surprise"));
			arr.add(new LiteralOption("indecision"));
			arr.add(new LiteralOption("angry"));
			arr.add(new LiteralOption("angle"));
			arr.add(new LiteralOption("cool"));
			arr.add(new LiteralOption("devil"));
			arr.add(new LiteralOption("crying"));
			arr.add(new LiteralOption("enlightened"));
			arr.add(new LiteralOption("no"));
			arr.add(new LiteralOption("yes"));
			arr.add(new LiteralOption("heart"));
			arr.add(new LiteralOption("broken heart"));
			arr.add(new LiteralOption("kiss"));
			arr.add(new LiteralOption("mail"));
			return arr;
		}
		
		return (ArrayItemOptions<LiteralOption>) coll;
	}
	
	/**
	 * Method setting the description to be used for each of the smileys
	 * @param smiley_descriptions
	 * @return the current instance
	 */
	public CKeditorBehavior setSmileyDescriptions(ArrayItemOptions<LiteralOption> smiley_descriptions) {
		options.put("smiley_descriptions", smiley_descriptions);
		return this;
	}
	
	/**
	 * @return the smiley_path option
	 */
	@SuppressWarnings("unchecked")
	public ArrayItemOptions<LiteralOption> getSmileyImages() {
		ICollectionItemOptions coll = options.getListItemOptions("smiley_images");
		
		if(coll == null){
			ArrayItemOptions<LiteralOption> arr = new ArrayItemOptions<LiteralOption>();
			arr.add(new LiteralOption("regular_smile.gif"));
			arr.add(new LiteralOption("sad_smile.gif"));
			arr.add(new LiteralOption("wink_smile.gif"));
			arr.add(new LiteralOption("teeth_smile.gif"));
			arr.add(new LiteralOption("confused_smile.gif"));
			arr.add(new LiteralOption("tounge_smile.gif"));
			arr.add(new LiteralOption("embaressed_smile.gif"));
			arr.add(new LiteralOption("omg_smile.gif"));
			arr.add(new LiteralOption("whatchutalkingabout_smile.gif"));
			arr.add(new LiteralOption("angry_smile.gif"));
			arr.add(new LiteralOption("angel_smile.gif"));
			arr.add(new LiteralOption("shades_smile.gif"));
			arr.add(new LiteralOption("devil_smile.gif"));
			arr.add(new LiteralOption("cry_smile.gif"));
			arr.add(new LiteralOption("lightbulb.gif"));
			arr.add(new LiteralOption("thumbs_down.gif"));
			arr.add(new LiteralOption("thumbs_up.gif"));
			arr.add(new LiteralOption("heart.gif"));
			arr.add(new LiteralOption("broken_heart.gif"));
			arr.add(new LiteralOption("kiss.gif"));
			arr.add(new LiteralOption("envelope.gif"));
			return arr;
		}
		
		return (ArrayItemOptions<LiteralOption>) coll;
	}
	
	/**
	 * Method setting the file names for the smileys to be displayed.
	 * @param smiley_images
	 * @return the current instance
	 */
	public CKeditorBehavior setSmileyPath(ArrayItemOptions<LiteralOption> smiley_images) {
		options.put("smiley_images", smiley_images);
		return this;
	}
	
	/**
	 * @return the smiley_path option
	 */
	public String getSmileyPath() {
		String path = options.getLiteral("smiley_path");
		return path == null ? "/images/smileys/" : path;
	}
	
	/**
	 * Method setting the base path used to build the URL for the smiley images. 
	 * It must end with a slash
	 * @param smiley_path
	 * @return the current instance
	 */
	public CKeditorBehavior setSmileyPath(String smiley_path) {
		options.putLiteral("smiley_path", smiley_path);
		return this;
	}
	
	/**
	 * @return the startupFocus option
	 */
	public boolean isStartupFocus() {
		if(options.get("startupFocus") == null){
			return false;
		}
		
		return options.getBoolean("startupFocus");
	}
	
	/**
	 * Method to define if the editor shall have the focus on the page loads
	 * @param startupFocus
	 * @return the current instance
	 */
	public CKeditorBehavior setStartupFocus(boolean startupFocus) {
		options.put("startupFocus", startupFocus);
		return this;
	}
	
	/**
	 * @return the toolbarLocation option
	 */
	public StartupMode getStartupMode() {
		if(options.get("startupMode") == null){
			return StartupMode.WYSIWYG;
		}
		
		return StartupMode.valueOf(options.get("startupMode").toUpperCase());
	}
	
	/**Method to define the mode to load at the editor startup
	 * @param startupMode
	 * @return the current instance
	 */
	public CKeditorBehavior setStartupMode(StartupMode startupMode) {
		options.put("startupMode", startupMode.toString().toLowerCase());
		return this;
	}
	
	/**
	 * @return the startupOutlineBlocks option
	 */
	public boolean isStartupOutlineBlocks() {
		if(options.get("startupOutlineBlocks") == null){
			return true;
		}
		
		return options.getBoolean("startupOutlineBlocks");
	}
	
	/**
	 * Method to enable the "show block" command when the editor loads
	 * @param startupOutlineBlocks
	 * @return the current instance
	 */
	public CKeditorBehavior setStartupOutlineBlocks(boolean startupOutlineBlocks) {
		options.put("startupOutlineBlocks", startupOutlineBlocks);
		return this;
	}
	
	/**
	 * @return the stylesSet option
	 */
	public String getStylesSet() {
		String stylesSet = options.getLiteral("stylesSet");
		return stylesSet == null ? "default" : stylesSet;
	}
	
	/**
	 * Method setting the style to use in the editor
	 * @param stylesSet
	 * @return the current instance
	 */
	public CKeditorBehavior setStylesSet(String stylesSet) {
		options.putLiteral("stylesSet", stylesSet);
		return this;
	}
	
	/**
	 * @return the tabIndex option
	 */
	public int getTabIndex() {
		if(options.get("tabIndex") == null){
			return 0;
		}
		
		return options.getInt("tabIndex");
	}
	
	/**
	 * Method setting the tab index 
	 * @param tabIndex
	 * @return the current instance
	 */
	public CKeditorBehavior setTabIndex(int tabIndex) {
		options.put("tabIndex", tabIndex);
		return this;
	}
	
	/**
	 * @return the tabSpaces option
	 */
	public int getTabSpaces() {
		if(options.get("tabSpaces") == null){
			return 0;
		}
		
		return options.getInt("tabSpaces");
	}
	
	/**
	 * Intructs the editor to add a number of spaces (&nbsp;) to the text when 
	 * hitting the TAB key. If set to zero, the TAB key will be used to move the 
	 * cursor focus to the next element in the page, out of the editor focus.  
	 * @param tabSpaces
	 * @return the current instance
	 */
	public CKeditorBehavior setTabSpaces(int tabSpaces) {
		options.put("tabSpaces", tabSpaces);
		return this;
	}
	
	/**
	 * @return the templates option
	 */
	public String getTemplates() {
		String templates = options.getLiteral("templates");
		return templates == null ? "default" : templates;
	}
	
	/**
	 * Method setting the templates to use
	 * @param templates
	 * @return the current instance
	 */
	public CKeditorBehavior setTemplates(String templates) {
		options.putLiteral("templates", templates);
		return this;
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
	 * Methode setting the list of templates definition files to load
	 * @param templates_files
	 * @return the current instance
	 */
	public CKeditorBehavior setTemplatesFiles(ArrayItemOptions<LiteralOption> templates_files) {
		options.put("templates_files", templates_files);
		return this;
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
	 * Whether the "Replace actual contents" checkbox is checked by default in 
	 * the Templates dialog. 
	 * @param templates_replaceContent
	 * @return the current instance
	 */
	public CKeditorBehavior setTemplatesReplaceContent(boolean templates_replaceContent) {
		options.put("templates_replaceContent", templates_replaceContent);
		return this;
	}
	
	/**
	 * @return the theme option
	 */
	public String getTheme() {
		String theme = options.getLiteral("theme");
		return theme == null ? "default" : theme;
	}
	
	/**
	 * Method setting the theme to use
	 * @param theme
	 * @return the current instance
	 */
	public CKeditorBehavior setTheme(String theme) {
		options.putLiteral("theme", theme);
		return this;
	}
	
	/**
	 * @return the toolbar option
	 */
	public CKeditorToolbar getToolbar() {
		Object toolbar = options.getComplexOption("toolbar");
		return toolbar == null ? 
				new CKeditorToolbar(CKeditorToolbar.ToolbarType.FULL) : (CKeditorToolbar) toolbar;
	}
	
	/**
	 * Method setting the elements in the toolbar
	 * @param toolbar
	 * @return the current instance
	 */
	public CKeditorBehavior setToolbar(CKeditorToolbar toolbar) {
		options.put("toolbar", toolbar);
		return this;
	}
	
	/**
	 * @return the toolbarCanCollapse option
	 */
	public boolean isToolbarCanCollapse() {
		if(options.get("toolbarCanCollapse") == null){
			return true;
		}
		
		return options.getBoolean("toolbarCanCollapse");
	}
	
	/**
	 * Whether the toolbar can be collapsed by the user. If disabled, 
	 * the collapser button will not be displayed. 
	 * @param toolbarCanCollapse
	 * @return the current instance
	 */
	public CKeditorBehavior setToolbarCanCollapse(boolean toolbarCanCollapse) {
		options.put("toolbarCanCollapse", toolbarCanCollapse);
		return this;
	}
	
	/**
	 * @return the toolbarLocation option
	 */
	public ToolbarLocation getToolbarLocation() {
		if(options.get("toolbarLocation") == null){
			return ToolbarLocation.TOP;
		}
		
		return ToolbarLocation.valueOf(options.get("toolbarLocation").toUpperCase());
	}
	
	/**Method to define the position of the toolbar
	 * @param toolbarLocation
	 * @return the current instance
	 */
	public CKeditorBehavior setToolbarLocation(ToolbarLocation toolbarLocation) {
		options.put("toolbarLocation", toolbarLocation.toString().toLowerCase());
		return this;
	}
	
	/**
	 * @return the undoStackSize option
	 */
	public int getUndoStackSize() {
		if(options.get("undoStackSize") == null){
			return 20;
		}
		
		return options.getInt("undoStackSize");
	}
	
	/**
	 * Method setting the number of undo steps to be saved. The higher this 
	 * setting value the more memory is used for it. 
	 * @param undoStackSize
	 * @return the current instance
	 */
	public CKeditorBehavior setUndoStackSize(int undoStackSize) {
		options.put("undoStackSize", undoStackSize);
		return this;
	}
	
	/**
	 * @return the width option
	 */
	public SizeOption getWidth() {
		return (SizeOption) options.getComplexOption("width");
	}
	
	/**
	 * Method setting the width in CSS size format or pixel integer
	 * @param width
	 * @return the current instance
	 */
	public CKeditorBehavior setWidth(SizeOption width) {
		options.put("width", width);
		return this;
	}
	
	/*---- Methods section ---*/
	
	/**
	 * Method to test if the content was modified
	 * @return the associated {@link JsStatement}
	 */
	public JsStatement checkDirty() {
		return new JsQuery(getComponent()).$().chain("ckeditorGet").chain("checkDirty");
	}
	
	/**
	 * Method to destroy the ckeditor
	 * This will return the element back to its pre-init state.
	 * @return the associated {@link JsStatement}
	 */
	public JsStatement destroy() {
		return new JsQuery(getComponent()).$().chain("ckeditorGet").chain("destroy");
	}
	
	/**
	 * Method to focus the ditor
	 * @return the associated {@link JsStatement}
	 */
	public JsStatement focus() {
		return new JsQuery(getComponent()).$().chain("ckeditorGet").chain("focus");
	}
	
	/**
	 * Method to get the content of the editor
	 * @return the associated {@link JsStatement}
	 */
	public JsStatement getData() {
		return new JsQuery(getComponent()).$().chain("ckeditorGet").chain("getData");
	}
	
	/**
	 * Method to get the text selection
	 * @return the associated {@link JsStatement}
	 */
	public JsStatement getSelection() {
		return new JsQuery(getComponent()).$().chain("ckeditorGet").chain("getSelection");
	}
	
	/**
	 * Method to get a snapshot of the editor
	 * @return the associated {@link JsStatement}
	 */
	public JsStatement getSnapshot() {
		return new JsQuery(getComponent()).$().chain("ckeditorGet").chain("getSnapshot");
	}
	
	/**
	 * Method inserting into the editor a DOM element
	 * @param element DOM element
	 * @return the associated {@link JsStatement}
	 */
	public JsStatement insertElement(CharSequence element) {
		return new JsQuery(getComponent()).$().chain("ckeditorGet").chain(
				"insertElement", element);
	}
	
	/**
	 * Method inserting into the editor some HTML content
	 * @param innerHTML HTML content
	 * @return the associated {@link JsStatement}
	 */
	public JsStatement insertHtml(CharSequence innerHTML) {
		return new JsQuery(getComponent()).$().chain("ckeditorGet").chain(
				"insertHtml", JsUtils.quotes(innerHTML));
	}
	
	/**
	 * Method inserting into the editor some text content
	 * @param innerText Text content
	 * @return the associated {@link JsStatement}
	 */
	public JsStatement insertText(CharSequence innerText) {
		return new JsQuery(getComponent()).$().chain("ckeditorGet").chain(
				"insertText", JsUtils.quotes(innerText));
	}
	
	/**
	 * Method setting the editor content
	 * @param innerHTML HTML content
	 * @return the associated {@link JsStatement}
	 */
	public JsStatement setData(CharSequence innerHTML) {
		return new JsQuery(getComponent()).$().chain("ckeditorGet").chain(
				"setData", JsUtils.quotes(innerHTML));
	}
}
