package jmybatis;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DbUtil {
	SqlSessionFactory sqlSessionFactory;

	public void init() {

		try {

			String resource = "jmybatis/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		} catch (Exception e) {
			System.out.println("MyBatis load err");
			e.printStackTrace();

		} // trycatch end

	}// init end

	public ArrayList<UserDTO> getUser(){
			SqlSession session = sqlSessionFactory.openSession();
			UserMapper mapper = session.getMapper(UserMapper.class);
			ArrayList<UserDTO> userlist = mapper.getUser();
			
			return userlist;
	}
	
	public void insertUser(String user_id,String user_pw,String name,
							String phone,String grade,int age) {
		
		
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		UserDTO userDTO = new UserDTO(user_id, user_pw, name, phone, grade, age);
		mapper.insertUser(userDTO);
		session.commit(); // insert, update, delete 에만 해당 ( 필수 ) 저장
	}
	public void updateUser(String name, String user_id) {
	
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.updateUser(name, user_id);
		session.commit();
	}
	
	public void deleteUser(String user_id) {
		
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.deleteUser(user_id);
		session.commit();
	}
	
	public String login(String user_id, String user_pw) {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		String login = "";
		login = mapper.login(user_id, user_pw);
		session.commit();
		
		if(login != null) {
			System.out.println("로그인성공");
			
		}else {
			System.out.println("로그인 실패");
		}

		return login;
		
		
	}
	
}// class end
