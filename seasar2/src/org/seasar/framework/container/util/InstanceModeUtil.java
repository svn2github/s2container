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
public final class InstanceModeUtil implements ContainerConstants {

	private InstanceModeUtil() {
	}

	public static final boolean isSingleton(String mode) {
		return INSTANCE_SINGLETON.equalsIgnoreCase(mode);
	}
	
	public static final boolean isPrototype(String mode) {
		return INSTANCE_PROTOTYPE.equalsIgnoreCase(mode);
	}
	
	public static final boolean isRequest(String mode) {
		return INSTANCE_REQUEST.equalsIgnoreCase(mode);
	}
	
	public static final boolean isSession(String mode) {
		return INSTANCE_SESSION.equalsIgnoreCase(mode);
	}
	
	public static final boolean isOuter(String mode) {
		return INSTANCE_OUTER.equalsIgnoreCase(mode);
	}
}
