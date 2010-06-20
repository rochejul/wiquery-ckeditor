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

/**
 * $Id: Picture.java roche.jul $
 * 
 * <p>
 * 	Class contains options for pictures
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class Picture implements Serializable {
	// Constants
	/** Constant of serialization  */
	private static final long serialVersionUID = -4894266289699516683L;

	// Variables
	private Options options;
	
	/**
	 * Default constructor
	 * @param options Instance of the {@link options}
	 */
	protected Picture(Options options) {
		super();
		
		this.options = options;
	}
	
	/**
	 * @return the image_previewText option
	 */
	public String getImagePreviewText() {
		String text = options.getLiteral("image_previewText");
		return text == null ? "Lorem ipsum dolor..." : text;
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
	 * @return the smiley_path option
	 */
	public String getSmileyPath() {
		String path = options.getLiteral("smiley_path");
		return path == null ? "/images/smileys/" : path;
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
	 * Method setting the padding text to set off the image in preview area
	 * @param image_previewText
	 * @return the current instance
	 */
	public Picture setImagePreviewText(String image_previewText) {
		options.putLiteral("image_previewText", image_previewText);
		return this;
	}
	
	/**
	 * Whether to remove links when emptying the link URL field in the image dialog. 
	 * @param image_removeLinkByEmptyURL
	 * @return the current instance
	 */
	public Picture setImageRemoveLinkByEmptyURL(boolean image_removeLinkByEmptyURL) {
		options.put("image_removeLinkByEmptyURL", image_removeLinkByEmptyURL);
		return this;
	}
	
	/**
	 * Method setting the description to be used for each of the smileys
	 * @param smiley_descriptions
	 * @return the current instance
	 */
	public Picture setSmileyDescriptions(ArrayItemOptions<LiteralOption> smiley_descriptions) {
		options.put("smiley_descriptions", smiley_descriptions);
		return this;
	}
	
	/**
	 * Method setting the file names for the smileys to be displayed.
	 * @param smiley_images
	 * @return the current instance
	 */
	public Picture setSmileyPath(ArrayItemOptions<LiteralOption> smiley_images) {
		options.put("smiley_images", smiley_images);
		return this;
	}
	
	/**
	 * Method setting the base path used to build the URL for the smiley images. 
	 * It must end with a slash
	 * @param smiley_path
	 * @return the current instance
	 */
	public Picture setSmileyPath(String smiley_path) {
		options.putLiteral("smiley_path", smiley_path);
		return this;
	}
}
