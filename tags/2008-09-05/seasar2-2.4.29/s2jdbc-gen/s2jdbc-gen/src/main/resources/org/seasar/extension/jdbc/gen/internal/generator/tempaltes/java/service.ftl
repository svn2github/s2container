<#include "/copyright.ftl">
<#if packageName??>
package ${packageName};
</#if>

<#list importNameSet as importName>
import ${importName};
</#list>

/**
 * {@link ${shortEntityClassName}}のサービスクラスです。
 * 
 * @author S2JDBC-Gen
 */
public class ${shortClassName} extends AbstractService<${shortEntityClassName}><#if namesModel??> implements ${namesModel.shortClassName}</#if> {
<#if idPropertyMetaList?size gt 0>

    /**
     * 識別子でエンティティを検索します。
     * 
  <#list idPropertyMetaList as prop>
     * @param ${prop.name}
     *            識別子
  </#list>
     * @return エンティティ
     */
    public ${shortEntityClassName} findById(<#list idPropertyMetaList as prop>${prop.propertyClass.simpleName} ${prop.name}<#if prop_has_next>, </#if></#list>) {
        return select().id(<#list idPropertyMetaList as prop>${prop.name}<#if prop_has_next>, </#if></#list>).getSingleResult();
    }
</#if>
<#if idPropertyMetaList?size gt 0 && versionPropertyMeta??>

    /**
     * 識別子とバージョン番号でエンティティを検索します。
     * 
  <#list idPropertyMetaList as prop>
     * @param ${prop.name}
     *            識別子
  </#list>
     * @param ${versionPropertyMeta.name}
     *            バージョン番号
     * @return エンティティ
     */
    public ${shortEntityClassName} findByIdVersion(<#list idPropertyMetaList as prop>${prop.propertyClass.simpleName} ${prop.name}, </#list>${versionPropertyMeta.propertyClass.simpleName} ${versionPropertyMeta.name}) {
        return select().id(<#list idPropertyMetaList as prop>${prop.name}<#if prop_has_next>, </#if></#list>).version(${versionPropertyMeta.name}).getSingleResult();
    }
</#if>
}