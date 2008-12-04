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
package org.seasar.diigu.eclipse.util;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.seasar.diigu.eclipse.Constants;

public class StatusUtil {

    public static IStatus create(int severity, int code, String message,
            Throwable throwable) {
        return new Status(severity, Constants.PLUGIN_ID, code, message,
                throwable);
    }

    public static IStatus createError(int code, Throwable throwable) {
        String message = throwable.getMessage();
        if (message == null) {
            message = throwable.getClass().getName();
        }
        return create(IStatus.ERROR, code, message, throwable);
    }

    public static IStatus createError(int code, String message,
            Throwable throwable) {
        return create(IStatus.ERROR, code, message, throwable);
    }

    public static IStatus createWarning(int code, String message,
            Throwable throwable) {
        return create(IStatus.WARNING, code, message, throwable);
    }

    public static IStatus createInfo(int code, String message,
            Throwable throwable) {
        return create(IStatus.INFO, code, message, throwable);
    }
}
