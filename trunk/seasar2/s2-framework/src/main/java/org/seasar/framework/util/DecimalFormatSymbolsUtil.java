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
package org.seasar.framework.util;

import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author higa
 * 
 */
public final class DecimalFormatSymbolsUtil {

    private static Map cache = Collections.synchronizedMap(new HashMap());

    private DecimalFormatSymbolsUtil() {
    }

    public static DecimalFormatSymbols getDecimalFormatSymbols() {
        return getDecimalFormatSymbols(Locale.getDefault());
    }

    public static DecimalFormatSymbols getDecimalFormatSymbols(Locale locale) {
        DecimalFormatSymbols symbols = (DecimalFormatSymbols) cache.get(locale);
        if (symbols == null) {
            symbols = new DecimalFormatSymbols(locale);
            cache.put(locale, symbols);
        }
        return symbols;
    }
}
