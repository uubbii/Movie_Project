package com.kmove.app.user.vo;

import java.time.LocalDateTime;

public class BlockUserVo {
	   	private String bidx;             // BIDX (���� ���� ���� ID)
	   	
	   	
	    private String uidx;             // UIDX (���� ID)
	    private String targetUidx;       // TARGET_UIDX (������ ���� ID)
	    private String breason;          // BREASON (���� ����)
	    private LocalDateTime bblockedDate; // BBLOCKEDDATE (���� �Ͻ�, ���� �ð��� �����ؾ��ҵ�.)
	    private Integer mid; 
	    
	    
	    
	    
	    @Override
	    public String toString() {
	        return "BlockUserVo { \n" +
	                "  bidx = '" + bidx + "', \n" +
	                "  uidx = '" + uidx + "', \n" +
	                "  targetUidx = '" + targetUidx + "', \n" +
	                "  breason = '" + breason + "', \n" +
	                "  bblockedDate = " + bblockedDate + " \n" +
	                "}";
	    }

	   
	    
	    
		public Integer getMid() {
			return mid;
		}

		public void setMid(Integer mid) {
			this.mid = mid;
		}

		public BlockUserVo() {}

		public String getBidx() {
			return bidx;
		}

		public void setBidx(String bidx) {
			this.bidx = bidx;
		}

		public String getUidx() {
			return uidx;
		}

		public void setUidx(String uidx) {
			this.uidx = uidx;
		}

		public String getTargetUidx() {
			return targetUidx;
		}

		public void setTargetUidx(String targetUidx) {
			this.targetUidx = targetUidx;
		}

		public String getBreason() {
			return breason;
		}

		public void setBreason(String breason) {
			this.breason = breason;
		}

		public LocalDateTime getBblockedDate() {
			return bblockedDate;
		}

		public void setBblockedDate(LocalDateTime bblockedDate) {
			this.bblockedDate = bblockedDate;
		}

		public BlockUserVo(String bidx, String uidx, String targetUidx, String breason, LocalDateTime bblockedDate) {
			super();
			this.bidx = bidx;
			this.uidx = uidx;
			this.targetUidx = targetUidx;
			this.breason = breason;
			this.bblockedDate = bblockedDate;
		};
	    
	    
	    
}
