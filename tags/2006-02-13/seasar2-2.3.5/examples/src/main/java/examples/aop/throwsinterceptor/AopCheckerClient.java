/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package examples.aop.throwsinterceptor;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;

public class AopCheckerClient {
	private static String PATH = "examples/aop/throwsinterceptor/Checker.dicon";

	public static void main(String[] args) {
		S2Container container = S2ContainerFactory.create(PATH);
		Checker checker = (Checker) container.getComponent(Checker.class);
		checker.check("foo");
		checker.check(null);
		checker.check("hoge");
	}
}