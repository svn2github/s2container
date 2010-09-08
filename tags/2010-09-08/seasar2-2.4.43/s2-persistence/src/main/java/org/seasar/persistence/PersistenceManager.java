/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.persistence;

import java.util.List;

import org.seasar.persistence.exception.NoEntityRuntimeException;

/**
 * 永続マネージャーのインターフェースです。
 * 
 * @author higa
 * 
 */
public interface PersistenceManager {

	/**
	 * criteriaに対応するEntityのリストを返します。 対応するEntityが無い場合、sizeが0のリストを返します。
	 * criteriaがnullの場合、全件検索になります。
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param criteria
	 * @return <T>のリスト
	 * @throws NullPointerException
	 *             entityClassがnullの場合。
	 * @throws NoEntityRuntimeException
	 *             entityClassがEntityでない場合。
	 */
	<T> List<T> findAll(Class<? extends T> entityClass, Object criteria)
			throws NullPointerException, NoEntityRuntimeException;
}