package com.share.common.core.utils;

import java.util.regex.Pattern;

/**
 * SQL 安全校验工具
 *
 * @author share
 */
public class SqlUtils
{
    /** 允许执行的 SQL 最大长度 */
    private static final int MAX_SQL_LENGTH = 4000;

    /** 写操作及危险函数关键字 */
    private static final Pattern FORBIDDEN_KEYWORDS = Pattern.compile(
            "(?i)\\b(insert|update|delete|drop|alter|truncate|create|replace|grant|revoke|execute|exec|call|merge|rename|lock|unlock|load_file|outfile|dumpfile|sleep|benchmark|information_schema)\\b");

    /** SQL 注释 */
    private static final Pattern COMMENT = Pattern.compile("(--|/\\*|\\*/|#)");

    /**
     * 校验是否为安全的单条查询语句
     *
     * @param sql 待校验 SQL
     * @return true 表示可以执行
     */
    public static boolean isSafeSelect(String sql)
    {
        if (StringUtils.isEmpty(sql))
        {
            return false;
        }
        String trimmed = sql.trim();
        if (trimmed.length() > MAX_SQL_LENGTH)
        {
            return false;
        }
        //只允许 select 开头
        if (!trimmed.toLowerCase().startsWith("select"))
        {
            return false;
        }
        //禁止多语句
        if (trimmed.contains(";"))
        {
            return false;
        }
        //禁止注释绕过
        if (COMMENT.matcher(trimmed).find())
        {
            return false;
        }
        //禁止写操作与危险函数
        if (FORBIDDEN_KEYWORDS.matcher(trimmed).find())
        {
            return false;
        }
        return true;
    }
}
