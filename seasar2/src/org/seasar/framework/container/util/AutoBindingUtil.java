/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.framework.container.util;

import org.seasar.framework.container.ContainerConstants;

/**
 * @author higa
 *
 */
public final class AutoBindingUtil implements ContainerConstants {

	private AutoBindingUtil() {
	}

	public static final boolean isSuitable(Class clazz) {
		return clazz.isInterface();
	}

	public static final boolean isSuitable(Class[] classes) {
		for (int i = 0; i < classes.length; ++i) {
			if (!isSuitable(classes[i])) {
				return false;
			}
		}
		return true;
	}
	
	public static final boolean isAuto(String mode) {
		return AUTO_BINDING_AUTO.equalsIgnoreCase(mode);
	}
	
	public static final boolean isConstructor(String mode) {
		return AUTO_BINDING_CONSTRUCTOR.equalsIgnoreCase(mode);
	}
	
	public static final boolean isProperty(String mode) {
		return AUTO_BINDING_PROPERTY.equalsIgnoreCase(mode);
	}
	
	public static final boolean isNone(String mode) {
		return AUTO_BINDING_NONE.equalsIgnoreCase(mode);
	}
}
