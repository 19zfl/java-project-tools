<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${cfg.moduleName}.${table.mapperName}">
    <#if enableCache>
        <!-- 开启二级缓存 -->
        <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>
    </#if>

    <select id="selectMySqlPage" resultType="${cfg.basePath}.domain.${cfg.moduleName}.${entity}">
        select t1.*
        from ${table.name} t1
        <where>
            <if test="query.keyword != null and query.keyword != '' ">
                and t1.id like concat('%', ${r"#{query.keyword}"}, '%')
            </if>
        </where>
    </select>
</mapper>