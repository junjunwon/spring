package co.kr.controller;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class Test {
	
	@Inject
	private DataSource ds;
	
	@Inject
	private SqlSessionFactory sqlFactory;
	
	@org.junit.Test
	public void test() throws Exception{
		try(Connection conn=ds.getConnection()){
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
