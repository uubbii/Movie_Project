package com.kmove.app.comm;

import java.util.Calendar;

public class Chaebun {
	
	// 채번 활용 기본키 유저/리뷰/댓글
   public static final String USER = "U";
   public static final String REVIEW = "R";
   public static final String COMMENT = "C";
   
   
   
   // 날짜 예: 20240705
   public static String getDate() {
      Calendar cal = Calendar.getInstance();
      System.out.println("cal >> " + cal);
      String date = "";
      int year_ = cal.get(Calendar.YEAR);
      int month_ = cal.get(Calendar.MONTH)+1; // 0-11   
      int day_ = cal.get(Calendar.DAY_OF_MONTH);
      
      String year = String.valueOf(year_);
      String month = String.valueOf(month_);
      if(month.length() == 1) {
         month = "0" + month;
      }
      
      String day = String.valueOf(day_);
      if(day.length() == 1) {
         day = "0" + day; 
      }
      date = year + month + day;
      System.out.println("date >> " + date);
      return date;
   }
   
   // 번호 예: 001
   public static String getNo(String maxno) {
      System.out.println("maxno >> " + maxno);
      if(maxno.length() == 1) {
         maxno = "00" + maxno;
      } else if(maxno.length() == 2) {
         maxno = "0" + maxno;
      }
      System.out.println("maxno >> " + maxno);
      return maxno;
   }

   // 사용자 번호 생성
   public static String getUno(String maxno) {
      String uno = Chaebun.USER + Chaebun.getDate() + Chaebun.getNo(maxno);
      return uno;
   }
      
   public static String getBno(String maxno) {
      String bvo = Chaebun.REVIEW + Chaebun.getDate() + Chaebun.getNo(maxno);
      return bvo;
   }
   
   public static String getCno(String maxno) {
	      String cvo = Chaebun.COMMENT + Chaebun.getDate() + Chaebun.getNo(maxno);
	      return cvo;
	   }
}
