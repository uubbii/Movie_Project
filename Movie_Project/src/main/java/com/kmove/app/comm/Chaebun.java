package com.kmove.app.comm;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Chaebun {
	
	// ä�� Ȱ�� �⺻Ű ����/����/���
   public static final String USER = "U";
   public static final String REVIEW = "R";
   public static final String COMMENT = "C";
   public static final String BLOCKED = "B";
   
   
   
   // ��¥ ��: 20240705
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
   
   // ��ȣ ��: 001
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

// ����� ��ȣ ���� (����: "U.R.C.YYYYMMDD" - ��: U.R.C.20240110)
   public static String getUno(String maxno) {
      String uno = Chaebun.USER + Chaebun.getDate() + Chaebun.getNo(maxno);
      return uno;
   }
      
   public static String getRno(String maxno) {
      String rvo = Chaebun.REVIEW + Chaebun.getDate() + Chaebun.getNo(maxno);
      return rvo;
   }
   
   public static String getCno(String maxno) {
	      String cvo = Chaebun.COMMENT + Chaebun.getDate() + Chaebun.getNo(maxno);
	      return cvo;
	   }
   
   public static String getBno(String maxno) {
	      String bvo = Chaebun.BLOCKED + Chaebun.getDate() + Chaebun.getNo(maxno);
	      return bvo;
	   }
   
     
   // ������ ���ϸ� ���� (���ϸ�: YYYYMMDD_HHMMSS.jpg ����)
	public static String getFileName(String originalFilename) {
		
		// extenstion�� Ȯ���ڸ� �ǹ���.
	    String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
	
	    // ���� ��¥�� �ð��� ������� ���Ӱ� ������ ���ϸ� ����
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	    String time = dateFormat.format(cal.getTime());
	
	    // ���Ӱ� ������� �� return
	    return time + "." + extension;
}   
   
}
