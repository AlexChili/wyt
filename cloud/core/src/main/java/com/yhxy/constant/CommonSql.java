package com.yhxy.constant;

/**
 * @ClassName CommonSql
 * @Description: 全局sql
 * @Author hegc
 * @DateTime 2023/3/16 14:31
 * @updateDateTime 2023/3/16 14:31
 * @Version 1.0.0
 **/
public interface CommonSql {

    String ldsjListSql = "SELECT\n" +
            "l.id id,\n" +
            "l.ldmb,\n" +
            "l.title title,\n" +
            "l.type type,\n" +
            "l.img img,\n" +
            "l.sjtype1,\n" +
            "l.sjtype2,\n" +
            "l.sjtypename1,\n" +
            "l.sjtypename2,\n" +
            "u.`NAME`,\n" +
            "u.ICON icon,\n" +
            "(select sum(pf.score) from y_ldsj_pfbz pf  where pf.ldsj_id = l.id GROUP BY pf.ldsj_id) score \n" +
            "FROM y_ldsj l LEFT JOIN sys_user u on l.create_user = u.ID\n" +
            "where l.school_id=#{schoolId}\n" +
            "<if test=\"seachText != null and seachText != ''\">\n" +
            "and l.title like '%${seachText}%'\n" +
            "</if>"+
            "<if test=\"type != null and type != ''\">\n" +
            "and l.type like '${type}'\n" +
            "</if>"
            ;

}