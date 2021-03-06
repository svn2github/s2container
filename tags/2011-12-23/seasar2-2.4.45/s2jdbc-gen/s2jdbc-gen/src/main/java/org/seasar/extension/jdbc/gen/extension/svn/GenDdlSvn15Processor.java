/*
 * Copyright 2004-2011 the Seasar Foundation and the Others.
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
package org.seasar.extension.jdbc.gen.extension.svn;

import org.tmatesoft.svn.core.internal.wc.admin.SVNAdminAreaFactory;

/**
 * Gen-Ddlタスクによって生成されるディレクトリやファイルをSubversion1.5形式のワーキングコピーで管理するためのクラスです。
 * 
 * @author koichik
 */
public class GenDdlSvn15Processor extends GenDdlSvnProcessor {

    /**
     * インスタンスを構築します。
     */
    public GenDdlSvn15Processor() {
        super(SVNAdminAreaFactory.WC_FORMAT_15);
    }

}
