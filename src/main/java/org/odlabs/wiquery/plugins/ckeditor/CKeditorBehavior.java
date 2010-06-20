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
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.plugins.ckeditor.options.CommandKeyStrokeOption;
import org.odlabs.wiquery.plugins.ckeditor.options.KeyStrokeOption;
import org.odlabs.wiquery.plugins.ckeditor.options.RegExpOption;
import org.odlabs.wiquery.plugins.ckeditor.options.StyleOption;
import org.odlabs.wiquery.plugins.ckeditor.options.list.ElementPathListOptions;
import org.odlabs.wiquery.plugins.ckeditor.options.list.ListOptions;
import org.odlabs.wiquery.plugins.ckeditor.options.toolbar.CKeditorToolbar;

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
	private Dialog dialog;
	private FileBrowser fileBrowser;
	private Font font;
	private Format format;
	private Locale locale;
	private Options options;
	private Picture picture;
	private Paste paste;
	private StyleSheet stylesheet;
	
	public CKeditorBehavior() {
		super();
		options = new Options();
		
		dialog = new Dialog(options);
		format = new Format(options);
		fileBrowser = new FileBrowser(options);
		font = new Font(options);
		locale = new Locale(options);
		paste = new Paste(options);
		picture = new Picture(options);
		stylesheet = new StyleSheet(options);
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
	 * Method retreiving options for the dialog
	 * @return the options
	 */
	public Dialog getDialog() {
		return dialog;
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
	 * Method retrieving options for the file browser
	 * @return the options
	 */
	public FileBrowser getFileBrowser() {
		return fileBrowser;
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
	 * Method retrieving options for the font
	 * @return the options
	 */
	public Font getFont() {
		return font;
	}
	
	/**
	 * Method retrieving options for the format
	 * @return the options
	 */
	public Format getFormat() {
		return format;
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
	 * Method retrieving options for the localization
	 * @return the options
	 */
	public Locale getLocale() {
		return locale;
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
	 * Method retrieving the options for paste
	 * @return the options
	 */
	public Paste getPaste() {
		return paste;
	}
	
	/**
	 * Method retrieving the options for pictures
	 * @return the options
	 */
	public Picture getPicture() {
		return picture;
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
	 * Method retrieving options for the stylesheet
	 * @return the options
	 */
	public StyleSheet getStyleSheet() {
		return stylesheet;
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
