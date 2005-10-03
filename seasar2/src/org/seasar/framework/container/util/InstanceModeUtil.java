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
