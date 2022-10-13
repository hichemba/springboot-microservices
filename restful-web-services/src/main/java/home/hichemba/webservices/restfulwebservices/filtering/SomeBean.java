package home.hichemba.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

//@JsonIgnoreProperties("v1")
@JsonFilter("SomeFilter")
public class SomeBean {

	private String v1;
	
	//@JsonIgnore
	private String v2;
	
	private String v3;

	public SomeBean(String v1, String v2, String v3) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}

	public String getV1() {
		return v1;
	}

	public void setV1(String v1) {
		this.v1 = v1;
	}

	public String getV2() {
		return v2;
	}

	public void setV2(String v2) {
		this.v2 = v2;
	}

	public String getV3() {
		return v3;
	}

	public void setV3(String v3) {
		this.v3 = v3;
	}

	@Override
	public String toString() {
		return "SomeBean [v1=" + v1 + ", v2=" + v2 + ", v3=" + v3 + "]";
	}
}
