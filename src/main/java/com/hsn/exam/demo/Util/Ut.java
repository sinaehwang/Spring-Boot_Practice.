package com.hsn.exam.demo.Util;

public class Ut {

	public static boolean empty(Object obj) {

		if (obj == null) {

			return true; // null일때 true를 넘겨줘야 입력하라는 문장이 실행될수 있다.
		}

		if (obj instanceof String == false) {// 문자인지 아닌지 판별하기위해
			return true;
		}
		
		 String str = (String)obj;//문자화를 한번 시켜서 문자길이 유무로 입력여부를 판별한다.
		 
		  if(str.trim().length()==0) { return true; }
		  
		  return false;
		  
		  //위와 상동한 내용
			/*
			 * String str = (String) obj;
			 * 
			 * return str.trim().length() == 0; //길이 여부를 판별해서 true아니면 false를 리턴해주기 때문에 false가 생략된다.
			 */
	}

	public static String f(String format, Object... args) {//포맷을 받는 format과 여러가지 매개변수를 받을수 있는 Object...인자로 만든다.
		
		return String.format(format,args); //문장과 여러개 인자를 입력해서 그대로 리턴해준다.
	}

}
