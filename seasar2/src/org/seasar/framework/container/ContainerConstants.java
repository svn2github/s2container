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
package org.seasar.framework.container;

/**
 * @author higa
 *
 */
public interface ContainerConstants {

	public String INSTANCE_SINGLETON = "singleton";
	
	public String INSTANCE_PROTOTYPE = "prototype";
	
	public String INSTANCE_REQUEST = "request";
	
	public String INSTANCE_SESSION = "session";
	
	public String INSTANCE_OUTER = "outer";
	
	public String AUTO_BINDING_AUTO = "auto";
	
	public String AUTO_BINDING_CONSTRUCTOR = "constructor";
	
	public String AUTO_BINDING_PROPERTY = "property";
	
	public String AUTO_BINDING_NONE = "none";
	
	public char NS_SEP = '.';
	
	public String CONTAINER_NAME = "container";
	
	public String REQUEST_NAME = "request";
	
	public String RESPONSE_NAME = "response";
	
	public String SESSION_NAME = "session";
	
	public String SERVLET_CONTEXT_NAME = "servletContext";
	
	public String COMPONENT_DEF_NAME = "componentDef";
}
