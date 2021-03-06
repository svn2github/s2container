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
package org.seasar.extension.jdbc.gen.internal.factory;

import java.io.File;
import java.util.List;

import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.seasar.extension.jdbc.EntityMetaFactory;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.gen.command.Command;
import org.seasar.extension.jdbc.gen.data.Dumper;
import org.seasar.extension.jdbc.gen.data.Loader;
import org.seasar.extension.jdbc.gen.desc.DatabaseDescFactory;
import org.seasar.extension.jdbc.gen.desc.EntitySetDescFactory;
import org.seasar.extension.jdbc.gen.dialect.GenDialect;
import org.seasar.extension.jdbc.gen.generator.GenerationContext;
import org.seasar.extension.jdbc.gen.generator.Generator;
import org.seasar.extension.jdbc.gen.meta.DbTableMetaReader;
import org.seasar.extension.jdbc.gen.meta.EntityMetaReader;
import org.seasar.extension.jdbc.gen.model.AbstServiceModelFactory;
import org.seasar.extension.jdbc.gen.model.ConditionModelFactory;
import org.seasar.extension.jdbc.gen.model.EntityModelFactory;
import org.seasar.extension.jdbc.gen.model.NamesModelFactory;
import org.seasar.extension.jdbc.gen.model.ServiceModelFactory;
import org.seasar.extension.jdbc.gen.model.SqlIdentifierCaseType;
import org.seasar.extension.jdbc.gen.model.SqlKeywordCaseType;
import org.seasar.extension.jdbc.gen.model.TableModelFactory;
import org.seasar.extension.jdbc.gen.model.TestModelFactory;
import org.seasar.extension.jdbc.gen.sql.SqlFileExecutor;
import org.seasar.extension.jdbc.gen.sql.SqlUnitExecutor;
import org.seasar.extension.jdbc.gen.version.DdlVersionDirectoryTree;
import org.seasar.extension.jdbc.gen.version.DdlVersionIncrementer;
import org.seasar.extension.jdbc.gen.version.Migrater;
import org.seasar.extension.jdbc.gen.version.SchemaInfoTable;
import org.seasar.framework.convention.PersistenceConvention;

/**
 * S2JDBC-Genのインタフェースの実装を作成するファクトリです。
 * 
 * @author taedium
 */
public interface Factory {

    /**
     * {@link EntityMetaReader}の実装を返します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param classpathDir
     *            ルートディレクトリ
     * @param packageName
     *            パッケージ名、パッケージ名を指定しない場合は{@code null}
     * @param entityMetaFactory
     *            エンティティメタデータのファクトリ
     * @param shortClassNamePattern
     *            対象とするエンティティクラス名の正規表現
     * @param ignoreShortClassNamePattern
     *            対象としないエンティティクラス名の正規表現
     * @return {@link EntityMetaReader}の実装
     */
    EntityMetaReader createEntityMetaReader(Command command, File classpathDir,
            String packageName, EntityMetaFactory entityMetaFactory,
            String shortClassNamePattern, String ignoreShortClassNamePattern);

    /**
     * {@link DatabaseDescFactory}の実装を返します。
     * 
     * @param command
     *            コマンド
     * @param entityMetaFactory
     *            エンティティメタデータのファクトリ
     * @param entityMetaReader
     *            エンティティメタデータのリーダ
     * @param dialect
     *            方言
     * @return {@link DatabaseDescFactory}の実装
     */
    DatabaseDescFactory createDatabaseDescFactory(Command command,
            EntityMetaFactory entityMetaFactory,
            EntityMetaReader entityMetaReader, GenDialect dialect);

    /**
     * {@link Dumper}の実装を返します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param dialect
     *            方言
     * @param dumpFileEncoding
     *            ダンプファイルのエンコーディング
     * @return {@link Dumper}の実装
     */
    Dumper createDumper(Command command, GenDialect dialect,
            String dumpFileEncoding);

    /**
     * {@link SqlUnitExecutor}の実装を返します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param dataSource
     *            データソース
     * @param userTransaction
     *            ユーザートランザクション、トランザクションを利用しない場合{@code false}
     * @param haltOnError
     *            エラー発生時に処理を即座に中断する場合{@code true}、中断しない場合{@code false}
     * 
     * @return {@link SqlUnitExecutor}の実装
     */
    SqlUnitExecutor createSqlUnitExecutor(Command command,
            DataSource dataSource, UserTransaction userTransaction,
            boolean haltOnError);

    /**
     * {@link DbTableMetaReader}の実装を返します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param dataSource
     *            データソース
     * @param dialect
     *            方言
     * @param schemaName
     *            スキーマ名、デフォルトのスキーマ名を表す場合は{@code null}
     * @param tableNamePattern
     *            対象とするテーブル名の正規表現
     * @param ignoreTableNamePattern
     *            対象としないテーブル名の正規表現
     * @return
     */
    DbTableMetaReader createDbTableMetaReader(Command command,
            DataSource dataSource, GenDialect dialect, String schemaName,
            String tableNamePattern, String ignoreTableNamePattern);

    /**
     * {@link SqlFileExecutor}の実装を返します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param dialect
     *            方言
     * @param sqlFileEncoding
     *            SQLファイルのエンコーディング
     * @param statementDelimiter
     *            SQLステートメントの区切り文字
     * @param blockDelimiter
     *            SQLブロックの区切り文字
     * @return {@link SqlFileExecutor}の実装
     */
    SqlFileExecutor createSqlFileExecutor(Command command, GenDialect dialect,
            String sqlFileEncoding, char statementDelimiter,
            String blockDelimiter);

    /**
     * {@link ConditionModelFactory}の実装を作成します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param packageName
     *            パッケージ名、パッケージ名を指定しない場合は{@code null}
     * @param conditionClassNameSuffix
     *            条件クラス名のサフィックス
     * @return {@link ConditionModelFactory}の実装
     */
    ConditionModelFactory createConditionModelFactory(Command command,
            String packageName, String conditionClassNameSuffix);

    /**
     * {@link Generator}の実装を作成します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param templateFileEncoding
     *            テンプレートファイルのエンコーディング
     * @param templateFilePrimaryDir
     *            テンプレートファイルを格納したディレクトリ
     * @return {@link Generator}の実装
     */
    Generator createGenerator(Command command, String templateFileEncoding,
            File templateFilePrimaryDir);

    /**
     * {@link DdlVersionDirectoryTree}の実装を作成します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param baseDir
     *            バージョン管理のベースディレクトリ
     * @param versionFile
     *            バージョンファイル
     * @param versionNoPattern
     *            バージョン番号のパターン
     * @param env
     *            環境名
     * @param applyEnvToVersion
     *            環境名をバージョンに適用する場合 {@code true}
     * @return {@link DdlVersionDirectoryTree}の実装
     */
    DdlVersionDirectoryTree createDdlVersionDirectoryTree(Command command,
            File baseDir, File versionFile, String versionNoPattern,
            String env, boolean applyEnvToVersion);

    /**
     * {@link DdlVersionIncrementer}の実装を作成します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param ddlVersionDirectoryTree
     *            DDLのバージョンを管理するディレクトリ
     * @param dialect
     *            方言
     * @param dataSource
     *            データソース
     * @param createDirNameList
     *            コピー非対象のcreateディレクトリ名のリスト
     * @param dropDirNameList
     *            コピー非対象のdropディレクトリ名のリスト
     * @return {@link DdlVersionIncrementer}の実装
     */
    DdlVersionIncrementer createDdlVersionIncrementer(Command command,
            DdlVersionDirectoryTree ddlVersionDirectoryTree,
            GenDialect dialect, DataSource dataSource,
            List<String> createDirNameList, List<String> dropDirNameList);

    /**
     * {@link TableModelFactory}の実装を作成します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param dialect
     *            方言
     * @param dataSource
     *            データソース
     * @param sqlIdentifierCaseType
     *            SQLの識別子の大文字小文字を変換するかどうかを示す列挙型
     * @param sqlKeywordCaseType
     *            SQLのキーワードの大文字小文字を変換するかどうかを示す列挙型
     * @param statementDelimiter
     *            SQLステートメントの区切り文字
     * @param tableOption
     *            テーブルオプション、存在しない場合は{@code null}
     * @return {@link TableModelFactory}の実装
     */
    TableModelFactory createTableModelFactory(Command command,
            GenDialect dialect, DataSource dataSource,
            SqlIdentifierCaseType sqlIdentifierCaseType,
            SqlKeywordCaseType sqlKeywordCaseType, char statementDelimiter,
            String tableOption);

    /**
     * {@link EntitySetDescFactory}の実装を作成します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param dbTableMetaReader
     *            データベースのテーブルメタデータのリーダ
     * @param persistenceConvention
     *            永続化層の命名規約
     * @param dialect
     *            方言
     * @param versionColumnNamePattern
     *            バージョンカラム名のパターン
     * @param pluralFormFile
     *            単語を複数系に変換するための辞書ファイル、使用しない場合は{@code null}
     * @param generationType
     *            エンティティの識別子の生成方法を示す列挙型 、生成しない場合は{@code null}
     * @param initialValue
     *            エンティティの識別子の初期値、指定しない場合は{@code null}
     * @param allocationSize
     *            エンティティの識別子の割り当てサイズ、指定しない場合は{@code null}
     * @return {@link EntitySetDescFactory}の実装
     */
    EntitySetDescFactory createEntitySetDescFactory(Command command,
            DbTableMetaReader dbTableMetaReader,
            PersistenceConvention persistenceConvention, GenDialect dialect,
            String versionColumnNamePattern, File pluralFormFile,
            GenerationType generationType, Integer initialValue,
            Integer allocationSize);

    /**
     * {@link EntityModelFactory}の実装を作成します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param packageName
     *            パッケージ名、パッケージ名を指定しない場合は{@code null}
     * @param superclass
     *            エンティティのスーパークラス、スーパークラスを持たない場合は{@code null}
     * @param useAccessor
     *            エンティティクラスでアクセサを使用する場合 {@code true}
     * @param showCatalogName
     *            カタログ名を表示する場合{@code true}
     * @param showSchemaName
     *            スキーマ名を表示する場合{@code true}
     * @param showTableName
     *            テーブル名を表示する場合{@code true}
     * @param showColumnName
     *            カラム名を表示する場合{@code true}
     * @param showColumnDefinition
     *            カラム定義を表示する場合{@code true}
     * @param showJoinColumn
     *            {@link JoinColumn}を表示する場合{@code true}
     * 
     * @return {@link EntityModelFactory}の実装
     */
    EntityModelFactory createEntityModelFactory(Command command,
            String packageName, Class<?> superclass, boolean useAccessor,
            boolean showCatalogName, boolean showSchemaName,
            boolean showTableName, boolean showColumnName,
            boolean showColumnDefinition, boolean showJoinColumn);

    /**
     * {@link ServiceModelFactory}の実装を作成します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param packageName
     *            パッケージ名
     * @param serviceClassNameSuffix
     *            サービスクラス名のサフィックス
     * @param namesModelFactory
     *            名前モデルのファクトリ
     * @param useNamesClass
     *            名前クラスを使用する場合{@code true}
     * @param jdbcManagerName
     *            {@link JdbcManager}のコンポーネント名
     * @return {@link ServiceModelFactory}の実装
     */
    ServiceModelFactory createServiceModelFactory(Command command,
            String packageName, String serviceClassNameSuffix,
            NamesModelFactory namesModelFactory, boolean useNamesClass,
            String jdbcManagerName);

    /**
     * {@link AbstServiceModelFactory}の実装を作成します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param packageName
     *            パッケージ名
     * @param serviceClassNameSuffix
     *            サービスクラス名のサフィックス
     * @return {@link AbstServiceModelFactory}の実装
     */
    AbstServiceModelFactory createAbstServiceModelFactory(Command command,
            String packageName, String serviceClassNameSuffix);

    /**
     * {@link TestModelFactory}の実装を作成します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param configPath
     *            設定ファイルのパス
     * @param jdbcManagerName
     *            {@link JdbcManager}のコンポーネント名
     * @param testClassNameSuffix
     *            テストクラス名のサフィックス
     * @return {@link TestModelFactory}の実装
     */
    TestModelFactory createTestModelFactory(Command command, String configPath,
            String jdbcManagerName, String testClassNameSuffix);

    /**
     * {@link NamesModelFactory}の実装を作成します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param packageName
     *            パッケージ名
     * @param namesClassNameSuffix
     *            名前クラス名のサフィックス
     * @return {@link NamesModelFactory}の実装
     */
    NamesModelFactory createNamesModelFactory(Command command,
            String packageName, String namesClassNameSuffix);

    /**
     * {@link SchemaInfoTable}の実装を作成します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param dataSource
     *            データソース
     * @param dialect
     *            方言
     * @param fullTableName
     *            カタログ名やスキーマ名を含む完全なテーブル名
     * @param columnName
     *            カラム名
     * @return {@link SchemaInfoTable}の実装
     */
    SchemaInfoTable createSchemaInfoTable(Command command,
            DataSource dataSource, GenDialect dialect, String fullTableName,
            String columnName);

    /**
     * {@link Migrater}の実装を作成します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param sqlUnitExecutor
     *            SQLのひとまとまりの実行者
     * @param schemaInfoTable
     *            スキーマのバージョン
     * @param ddlVersionDirectoryTree
     *            DDLをバージョン管理するディレクトリ
     * @param version
     *            バージョン
     * @param env
     *            環境名
     * @return {@link Migrater}の実装
     */
    Migrater createMigrater(Command command, SqlUnitExecutor sqlUnitExecutor,
            SchemaInfoTable schemaInfoTable,
            DdlVersionDirectoryTree ddlVersionDirectoryTree, String version,
            String env);

    /**
     * {@link Loader}の実装を作成します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param dialect
     *            方言
     * @param dumpFileEncoding
     *            ダンプファイルのエンコーディング
     * @param batchSize
     *            バッチサイズ
     * @return {@link Loader}の実装
     */
    Loader createLoader(Command command, GenDialect dialect,
            String dumpFileEncoding, int batchSize);

    /**
     * {@link GenerationContext}の実装を作成します。
     * 
     * @param command
     *            呼び出し元のコマンド
     * @param model
     *            データモデル
     * @param file
     *            生成するファイル
     * @param templateName
     *            テンプレート名
     * @param encoding
     *            生成するファイルのエンコーディング
     * @param overwrite
     *            上書きする場合{@code true}、しない場合{@code false}
     * 
     * @return {@link GenerationContext}の実装
     */
    GenerationContext createGenerationContext(Command command, Object model,
            File file, String templateName, String encoding, boolean overwrite);

}