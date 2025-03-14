package mallpage;

//250314
//상품정보 DTO (setter, getter)
public class m_productDTO {
	int midx;
	String pcode, pnm, pmoney, psales, psmoney, pimg, pdate;
	
	//getter의 역할 : DTO에 저장된 값을 가져오는 역할 
	public int getMidx() {
		return midx;
	}
	
	//setter의 역할 : DTO 필드에 값을 저장하는 역할  
	public void setMidx(int midx) {
		this.midx = midx;
	}
	//게터만 있으면 VO, 게터 세터 같이 있으면 DTO
	
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPnm() {
		return pnm;
	}
	public void setPnm(String pnm) {
		this.pnm = pnm;
	}
	public String getPmoney() {
		return pmoney;
	}
	public void setPmoney(String pmoney) {
		this.pmoney = pmoney;
	}
	public String getPsales() {
		return psales;
	}
	public void setPsales(String psales) {
		this.psales = psales;
	}
	public String getPsmoney() {
		return psmoney;
	}
	public void setPsmoney(String psmoney) {
		this.psmoney = psmoney;
	}
	public String getPimg() {
		return pimg;
	}
	public void setPimg(String pimg) {
		this.pimg = pimg;
	}
	public String getPdate() {
		return pdate;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
	

}
