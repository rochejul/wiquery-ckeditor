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
package org.odlabs.wiquery.plugins.ckeditor.options;

import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.IListItemOption;

/**
 * $Id: CommandKeyStrokeOption roche.jul $
 * 
 * <p>
 * 	Option to use a keystroke for a command
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class CommandKeyStrokeOption extends KeyStrokeOption implements IComplexOption, IListItemOption {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 2688124675103427259L;
	
	// Properties
	private String command;
	
	/**
	 * Constructeur
	 * @param command
	 * @param keyCode
	 * @param masks
	 */
	public CommandKeyStrokeOption(String command, int keyCode, KeyStrokeMask... masks) {
		super(keyCode, masks);
		this.command = command;
	}

	/**
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	@Override
	public CharSequence getJavascriptOption() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		buffer.append(super.getJavascriptOption());
		buffer.append(", " + JsUtils.quotes(command));
		buffer.append("]");
		return buffer;
	}

	/**
	 * Method setting the command
	 * @param command
	 */
	public void setCommand(String command) {
		this.command = command;
	}
}
