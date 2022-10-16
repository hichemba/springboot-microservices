package home.hichemba.webservices.currencyexchangeservice;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class CurrencyExchange {

	@Id
	@GeneratedValue
	private Long id;
	private String fromC;
	private String toC;
	private BigDecimal conversionMultiple;
	
	@Transient
	private String env;
	
	public CurrencyExchange() {
		super();
	}

	public CurrencyExchange(Long id, String fromC, String toC, BigDecimal conversionMultiple) {
		super();
		this.id = id;
		this.fromC = fromC;
		this.toC = toC;
		this.conversionMultiple = conversionMultiple;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromC() {
		return fromC;
	}

	public void setFromC(String fromC) {
		this.fromC = fromC;
	}

	public String getToC() {
		return toC;
	}

	public void setToC(String toC) {
		this.toC = toC;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}
}
