package mallpage;

import java.util.ArrayList;

//250312
public abstract class ab_footer {
	m_copylight cl = new m_copylight();  //copylight모델 정보 
	ArrayList<String> cpdata = null;
	
	public void fts() {
		this.cpdata = this.cl.copyright_info();

	}
}
