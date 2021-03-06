/*
 * Copyright 2013, 2014 Deutsche Nationalbibliothek
 *
 * Licensed under the Apache License, Version 2.0 the "License";
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.culturegraph.mf.stream.source;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.culturegraph.mf.exceptions.MetafactureException;
import org.culturegraph.mf.framework.DefaultObjectPipe;
import org.culturegraph.mf.framework.ObjectReceiver;
import org.culturegraph.mf.framework.annotations.Description;
import org.culturegraph.mf.framework.annotations.FluxCommand;
import org.culturegraph.mf.framework.annotations.In;
import org.culturegraph.mf.framework.annotations.Out;
import org.culturegraph.mf.util.ResourceUtil;



/**
 * Opens a resource or file and passes a reader for it to the receiver.
 *
 * @author Christoph Böhme
 *
 */
@Description("Opens a resource.")
@In(String.class)
@Out(java.io.Reader.class)
@FluxCommand("open-resource")
public final class ResourceOpener
		extends DefaultObjectPipe<String, ObjectReceiver<Reader>> implements Opener{

	private String encoding = "UTF-8";

	/**
	 * Returns the encoding used to open the resource.
	 *
	 * @return current default setting
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * Sets the encoding used to open the resource.
	 *
	 * @param encoding new encoding
	 */
	public void setEncoding(final String encoding) {
		this.encoding = encoding;
	}

	@Override
	public void process(final String file) {
		try {
			getReceiver().process(ResourceUtil.getReader(file, encoding));
		} catch (FileNotFoundException e) {
			throw new MetafactureException(e);
		} catch (UnsupportedEncodingException e) {
			throw new MetafactureException(e);
		}
	}

}
