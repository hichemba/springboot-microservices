package home.hichemba.webservices.currencyconversionservice;

import java.math.BigDecimal;

public class CurrencyConversion {

	private Long id;
	private String fromC;
	private String toC;
	private BigDecimal conversionMultiple;
	private BigDecimal quantity;
	private BigDecimal totalCalculatedAmount;
	private String env;

	public CurrencyConversion() {
		super();
	}

	public CurrencyConversion(Long id, String fromC, String toC, BigDecimal conversionMultiple, BigDecimal quantity,
			BigDecimal totalCalculatedAmount, String env) {
		super();
		this.id = id;
		this.fromC = fromC;
		this.toC = toC;
		this.conversionMultiple = conversionMultiple;
		this.quantity = quantity;
		this.totalCalculatedAmount = totalCalculatedAmount;
		this.env = env;
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

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalCalculatedAmount() {
		return totalCalculatedAmount;
	}

	public void setTotalCalculatedAmount(BigDecimal totalCalculatedAmount) {
		this.totalCalculatedAmount = totalCalculatedAmount;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}
}
