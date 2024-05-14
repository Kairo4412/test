package jmybatis;

import java.util.ArrayList;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {

		DbUtil my = new DbUtil();
		my.init();

		Scanner sc = new Scanner(System.in);
		
		System.out.print("아이디 입력 : ");
		String user_id = sc.nextLine();
		System.out.print("비밀번호 입력 : ");
		String user_pw = sc.nextLine();
		
		
		if(my.login(user_id, user_pw) != null) {
			while (true) {
				System.out.println("[1] 전체조회 [2]글작성 [3]글수정 [4]글삭제 [5]로그인");
				int num = sc.nextInt();
				sc.nextLine();
				
				if (num == 1) {
					ArrayList<UserDTO> list = my.getUser();

					System.out.println(list);
				} else if (num == 2) {

					
					System.out.print("이름 입력 : ");
					String name = sc.nextLine();
					System.out.print("전화번호 입력 (예시:010-1111-1111) :  ");
					String phone = sc.nextLine();
					System.out.print("등급 입력 : ");
					String grade = sc.nextLine();
					System.out.print("나이 입력 : ");
					int age = sc.nextInt();

					my.insertUser(user_id, user_pw, name, phone, grade, age);

				}else if (num == 3) {
					
					
					System.out.println("바꿀 이름 입력 :");
					String name = sc.nextLine();
					System.out.println("수정할 아이디 입력 :");
					user_id = sc.nextLine();
					
					my.updateUser(name, user_id);
					
				}else if (num == 4) {
					
					System.out.println("삭제할 아이디 입력");
					user_id = sc.nextLine();
					
					my.deleteUser(user_id);
					
				}
		}
		
				
				
			

		} // while

	}// main
}// class
