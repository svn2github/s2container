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
package org.seasar.framework.container.deployer;

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.CyclicReferenceRuntimeException;

/**
 * @author higa
 *
 */
public class SingletonComponentDeployer extends AbstractComponentDeployer {

	private Object component_;
	private boolean instantiating_ = false;

	/**
	 * @param componentDef
	 */
	public SingletonComponentDeployer(ComponentDef componentDef) {
		super(componentDef);
	}

	/**
	 * @see org.seasar.framework.container.deployer.ComponentDeployer#deploy()
	 */
	public synchronized Object deploy() {
		if (component_ == null) {
			assemble();
		}
		return component_;
	}
	
	public void injectDependency(Object component) {
		throw new UnsupportedOperationException("injectDependency");
	}

	private void assemble() {
		if (instantiating_) {
			throw new CyclicReferenceRuntimeException(
				getComponentDef().getComponentClass());
		}
		instantiating_ = true;
		try {
			component_ = getConstructorAssembler().assemble();
		} finally {
			instantiating_ = false;
		}
		getPropertyAssembler().assemble(component_);
		getInitMethodAssembler().assemble(component_);
	}
	
	/**
	 * @see org.seasar.framework.container.deployer.ComponentDeployer#init()
	 */
	public void init() {
		deploy();
	}
	
	/**
	 * @see org.seasar.framework.container.deployer.ComponentDeployer#destroy()
	 */
	public void destroy() {
		if (component_ == null) {
			return;
		}
		getDestroyMethodAssembler().assemble(component_);
		component_ = null;
	}
}
