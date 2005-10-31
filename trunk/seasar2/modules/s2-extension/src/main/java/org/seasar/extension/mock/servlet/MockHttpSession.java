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
package org.seasar.extension.mock.servlet;

import javax.servlet.http.HttpSession;

import org.seasar.framework.util.ClassUtil;
/**
 * @author Satoshi Kimura
 */
public interface MockHttpSession extends HttpSession {
    String METHOD_NAME_IS_VALID = ClassUtil.getMethod(MockHttpSession.class, "isValid", null).getName();
    String METHOD_NAME_SET_VALID = ClassUtil.getMethod(MockHttpSession.class, "setValid", new Class[]{Boolean.TYPE})
            .getName();

    /**
     * {@link MockHttpSession#invalidate()}���Ă΂ꂽ���m�F���܂��B
     * 
     * @return �Ă΂ꂽ�ꍇ�Ftrue�A�Ă΂�Ă��Ȃ��ꍇ�Ffalse
     */
    boolean isValid();

    /**
     * {@link MockHttpSession#invalidate()}���Ă΂ꂽ�Ƃ��ɁA�����I�ɁA���̃��\�b�h���Ăт܂��B
     * 
     * @param valid {@link MockHttpSession#invalidate()}���Ă΂ꂽ�Ƃ��ɁAtrue
     */
    void setValid(boolean valid);
    
    void access();
}