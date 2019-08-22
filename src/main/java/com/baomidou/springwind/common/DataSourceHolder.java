package com.baomidou.springwind.common;

/**
 * Function:动态数据源
 *
 * @author chenjiec
 *         Date: 2017/1/2 上午12:19
 * @since JDK 1.7
 */
public class DataSourceHolder {
    private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();

    public static void setDataSources(String dataSource) {
        dataSources.set(dataSource);
    }

    public static String getDataSources() {
        return dataSources.get();
    }
    
    public static void clearDataSource() {
           dataSources.remove();
    }
}
