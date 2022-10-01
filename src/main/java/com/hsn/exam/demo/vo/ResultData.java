package com.hsn.exam.demo.vo;

import lombok.Getter;

public class ResultData<DT> {//제너릭
	
	@Getter
	private String resultCode; //S-1,S-2 성공, F-1,F-2 실패 여러개결과코드가능
	@Getter
	private String msg; //성공,실패에 대한 메세지를 보여주기위해서
	@Getter
	private DT data1; //성공시 해당데이터를 보여주기위해서
	
	private ResultData() {
		
	}

	public static <DT>ResultData<DT> from(String resultCode, String msg) {

		return from(resultCode, msg,null);
	}
	
	public static <DT>ResultData<DT> from(String resultCode, String msg, DT data1) {
		ResultData<DT> rd = new ResultData<DT>(); //매개변수 3개를 받아서 해당매개변수들로 새로운 객체하나를 만들어서 만들어진 객체를 리턴해준다. 
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1 = data1;
		
		return rd;
	}
	
	public static <DT>ResultData<DT> newData(ResultData joinData, DT newData) {//멤버타입외에도 다른타입이 들어올수있도록 Object형태로 만듬
		
		
		return from(joinData.getResultCode(),joinData.getMsg(),newData);//joinData에있는 결과토드랑 메세지를 보여주고,회원정보를 데이터로 보여줌
		
		
	}

	public boolean isSuccess() {//성공메소드는 참일때 
		return resultCode.startsWith("S-"); //결과코드가 S로 시작되는 결과코드를 리턴해준다.
	}
	
	public boolean isFail() { //실패메소드는 성공이 false가 되고 실패가 true가된다.
		return isSuccess()==false;
	}



}
