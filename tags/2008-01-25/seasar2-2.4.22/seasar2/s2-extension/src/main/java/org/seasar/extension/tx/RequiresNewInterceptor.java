/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.extension.tx;

import org.aopalliance.intercept.MethodInvocation;

/**
 * 新しいトランザクションを要求するメソッドのためのインターセプタです。
 * <p>
 * このインターセプタが適用されたメソッドが呼び出された際に、新しいトランザクションが開始されます。 メソッドが終了 (例外をスローした場合も)
 * した後、開始したトランザクションは完了 (コミットまたはロールバック) されます。<br>
 * メソッドが呼び出された際に、既にトランザクションが開始されていた場合、そのトランザクションは中断されます。
 * 中断されたトランザクションは、メソッドが終了した後に再開されます。
 * </p>
 * 
 * @author higa
 */
public class RequiresNewInterceptor extends AbstractTxInterceptor {

    /**
     * インスタンスを構築します。
     * 
     */
    public RequiresNewInterceptor() {
    }

    public Object invoke(final MethodInvocation invocation) throws Throwable {
        return transactionManagerAdapter.requiresNew(new DefaultTransactionCallback(
                invocation, txRules));
    }

}
