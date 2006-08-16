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
package org.seasar.framework.container.factory;

import javax.ejb.EJB;

import org.seasar.framework.container.annotation.tiger.AutoBindingType;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

@Component(name = "aaa", instance = InstanceType.PROTOTYPE, autoBinding = AutoBindingType.PROPERTY, externalBinding=true)
public class Hoge2 {

    @EJB
    private String fff;

    @EJB(name = "ggg2")
    private String ggg;

    @EJB(name = "ejb/hhh")
    private String hhh;

    @EJB(beanName = "lll2")
    private String lll;

    @EJB(beanName = "mmm2", name = "ejb/mmm2")
    private String mmm;

    @Binding("aaa2")
    public void setAaa(String aaa) {
    }

    @Binding(bindingType = BindingType.NONE)
    public void setBbb(String bbb) {
    }

    @Binding
    public void setCcc(String ccc) {
    }

    @EJB
    public void setDdd(String ddd) {
    }

    @EJB(name = "eee2")
    public void setEee(String eee) {
    }

    @EJB(name = "ejb/iii")
    public void setIii(String iii) {
    }

    @EJB(beanName = "jjj2")
    public void setJjj(String jjj) {
    }

    @EJB(beanName = "kkk2", name = "ejb/kkk2")
    public void setKkk(String kkk) {
    }
}
