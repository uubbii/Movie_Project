package com.kmove.app.review.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ReviewVo {
	    
	  	    
	    private String rtitle; // 글 제목
	    private String rcontent; // 글 내용
	  	
	    
//      아래부분들은 유저가 입력하지 않습니다.
	    
	    
	    private String uidx; // 유저의 기본키
	    private String ridx; // 리뷰글번호	    
	    @DateTimeFormat(pattern = "yyyy-MM-dd")	
	 	private Date rinsertdate; // 작성일
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private String usaveprofile;
	 	private Date rupdatedate; // 최근 수정일
	    private int rgood; // 공감 수
	    private int rreport; // 신고 수
	    private int rview; // 조회 수
	    

	    // mid는 API에서 불러온 ID만을 저장합니다.	 
	    private int mid; // 영화ID
	    
	    public ReviewVo() {
	    	
	    }

	    
	    
		public String getUsaveprofile() {
			return usaveprofile;
		}



		public void setUsaveprofile(String usaveprofile) {
			this.usaveprofile = "./resources/UPLOAD/" +usaveprofile;
		}



		public String getRtitle() {
			return rtitle;
		}

		public void setRtitle(String rtitle) {
			this.rtitle = rtitle;
		}

		public String getRcontent() {
			return rcontent;
		}

		public void setRcontent(String rcontent) {
			this.rcontent = rcontent;
		}

		public String getUidx() {
			return uidx;
		}

		public void setUidx(String uidx) {
			this.uidx = uidx;
		}

		public String getRidx() {
			return ridx;
		}

		public void setRidx(String ridx) {
			this.ridx = ridx;
		}

	    public String getRinsertdate() {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm");
	        return sdf.format(rinsertdate);
	    }

	    public String getRupdatedate() {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm");
	        return sdf.format(rupdatedate);
	    }

		public void setRinsertdate(Date rinsertdate) {
			this.rinsertdate = rinsertdate;
		}

		public void setRupdatedate(Date rupdatedate) {
			this.rupdatedate = rupdatedate;
		}

		public int getRgood() {
			return rgood;
		}

		public void setRgood(int rgood) {
			this.rgood = rgood;
		}

		public int getRreport() {
			return rreport;
		}

		public void setRreport(int rreport) {
			this.rreport = rreport;
		}

		public int getRview() {
			return rview;
		}

		public void setRview(int rview) {
			this.rview = rview;
		}

		public int getMid() {
			return mid;
		}

		public void setMid(int mid) {
			this.mid = mid;
		}



		public ReviewVo(String rtitle, String rcontent, String uidx, String ridx, Date rinsertdate, String usaveprofile,
				Date rupdatedate, int rgood, int rreport, int rview, int mid) {
			super();
			this.rtitle = rtitle;
			this.rcontent = rcontent;
			this.uidx = uidx;
			this.ridx = ridx;
			this.rinsertdate = rinsertdate;
			this.usaveprofile = usaveprofile;
			this.rupdatedate = rupdatedate;
			this.rgood = rgood;
			this.rreport = rreport;
			this.rview = rview;
			this.mid = mid;
		}



		@Override
		public String toString() {
		    return "ReviewVo {" + 
		           "\n  rtitle='" + rtitle + '\'' +
		           ",\n  rcontent='" + rcontent + '\'' +
		           ",\n  uidx=" + uidx +
		           ",\n  ridx=" + ridx +
		           ",\n  rinsertdate=" + rinsertdate +
		           ",\n  rupdatedate=" + rupdatedate +
		           ",\n  rgood=" + rgood +
		           ",\n  rreport=" + rreport +
		           ",\n  rview=" + rview +
		           ",\n  mid=" + mid +
		           ",\n  usaveprofile =" +usaveprofile +
		           "\n}";
		}

	    
	    
	    
}