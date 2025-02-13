package com.kmove.app.comment.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class CommentVo {
	
	private String ccomment; // 댓글 내용
	
	//아래부분들은 유저가 입력하지 않습니다.
	
    private String cidx; // 댓글의 고유키
    private String uidx; // 유저의 고유키
    private String ridx; // 글의 고유키(리뷰/게시판 공용)
    private String usaveprofile;
    private int creport; // 신고수
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cinsertdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cupdatedate;

	// mid는 API에서 불러온 ID만을 저장합니다.
    private int mid; 
    
    
       

	public String getUsaveprofile() {
		return usaveprofile;
	}

	public void setUsaveprofile(String usaveprofile) {
		this.usaveprofile =  "./resources/UPLOAD/" +usaveprofile;
	}

	public CommentVo() {}

	public String getCcomment() {
		return ccomment;
	}

	public void setCcomment(String ccomment) {
		this.ccomment = ccomment;
	}

	public String getCidx() {
		return cidx;
	}

	public void setCidx(String cidx) {
		this.cidx = cidx;
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

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getCreport() {
		return creport;
	}

	public void setCreport(int creport) {
		this.creport = creport;
	}


	public void setCinsertdate(Date cinsertdate) {
		this.cinsertdate = cinsertdate;
	}

    public String getCinsertdate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm");
        return sdf.format(cinsertdate);
    }

    public String getCupdatedate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm");
        return sdf.format(cupdatedate);
    }
	

	public void setCupdatedate(Date cupdatedate) {
		this.cupdatedate = cupdatedate;
	}


	



	public CommentVo(String ccomment, String cidx, String uidx, String ridx, String usaveprofile, int creport,
			Date cinsertdate, Date cupdatedate, int mid) {
		super();
		this.ccomment = ccomment;
		this.cidx = cidx;
		this.uidx = uidx;
		this.ridx = ridx;
		this.usaveprofile = usaveprofile;
		this.creport = creport;
		this.cinsertdate = cinsertdate;
		this.cupdatedate = cupdatedate;
		this.mid = mid;
	}

	@Override
	public String toString() {
	    return "CommentVo {" + 
	           "\n  내용='" + ccomment + '\'' +
	           ",\n  댓글 고유키='" + cidx + '\'' +
	           ",\n  유저 고유키='" + uidx + '\'' +
	           ",\n  글의 고유키='" + ridx + '\'' +
	           ",\n  신고수=" + creport +
	           ",\n  생성일=" + cinsertdate +
	           ",\n  수정일=" + cupdatedate +
	           ",\n  영화 고유키=" + mid +
	           ".\n 프로필 =" + usaveprofile +
	           "\n}";
	}

    
    
    
    
}
