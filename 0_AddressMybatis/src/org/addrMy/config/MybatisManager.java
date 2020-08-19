package org.addrMy.config;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisManager {
	public static SqlSessionFactory sqlMapper;
	static {
		String resource="org/addrMy/config/Configuration.xml";
		Reader reader;
		try {
			reader=Resources.getResourceAsReader(resource);
			sqlMapper=new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static SqlSessionFactory getSqlMapper() {
		return sqlMapper;
	}
}
