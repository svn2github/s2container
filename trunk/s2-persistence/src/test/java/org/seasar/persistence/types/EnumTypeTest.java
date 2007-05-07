/**
 * 
 */
package org.seasar.persistence.types;

import junit.framework.TestCase;

import org.seasar.framework.container.annotation.tiger.InstanceType;

/**
 * @author higa
 * 
 */
public class EnumTypeTest extends TestCase {

	/**
	 * Test method for
	 * {@link org.seasar.persistence.types.EnumType#toEnum(java.lang.String)}.
	 */
	public void testToEnum() {
		EnumType enumType = new EnumType(InstanceType.class);
		assertEquals(InstanceType.SINGLETON, enumType
				.toEnum(InstanceType.SINGLETON.name()));
	}
}