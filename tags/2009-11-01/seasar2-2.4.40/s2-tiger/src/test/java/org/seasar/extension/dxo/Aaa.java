/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
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
package org.seasar.extension.dxo;

import java.util.List;

/**
 * @author koichik
 */
public class Aaa {

    /** */
    public Integer[] prop1;

    private List<Integer> prop2;

    /**
     * @return
     */
    public List<Integer> getProp2() {
        return prop2;
    }

    /**
     * @param prop2
     */
    public void setProp2(List<Integer> prop2) {
        this.prop2 = prop2;
    }

}
