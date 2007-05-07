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
public class ExtendedValueTypesTest extends TestCase {

	/**
	 * Test method for
	 * {@link org.seasar.extension.jdbc.types.ValueTypes#getValueType(java.lang.Class)}.
	 */
	public void testGetValueTypeClass() {
		assertTrue(ExtendedValueTypes.getValueType(InstanceType.class) instanceof EnumType);
	}

}
