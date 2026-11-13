package com.krx.config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public final class MyBatisConfig {
    private static SqlSessionFactory sqlSessionFactory;

    private MyBatisConfig() {
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            synchronized (MyBatisConfig.class) {
                if (sqlSessionFactory == null) {
                    try (InputStream input = Resources.getResourceAsStream("mybatis-config.xml")) {
                        java.util.Properties properties = new java.util.Properties();
                        properties.setProperty("DB_USERNAME", env("DB_USERNAME", "root"));
                        properties.setProperty("DB_PASSWORD", env("DB_PASSWORD", ""));
                        sqlSessionFactory = new SqlSessionFactoryBuilder().build(input, properties);
                    } catch (Exception e) {
                        throw new IllegalStateException("MyBatis 초기화 실패: DB 환경변수와 설정 파일을 확인하세요.", e);
                    }
                }
            }
        }
        return sqlSessionFactory;
    }

    private static String env(String key, String defaultValue) {
        String value = System.getenv(key);
        return value == null ? defaultValue : value;
    }
}
