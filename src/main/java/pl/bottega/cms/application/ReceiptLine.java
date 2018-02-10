package pl.bottega.cms.application;

import java.math.BigDecimal;

public class ReceiptLine {

	private String kind;
	private Integer count;
	private BigDecimal unitPrice;
	private BigDecimal total;

	public ReceiptLine(String kind, Integer count, BigDecimal unitPrice, BigDecimal total) {
		this.kind = kind;
		this.count = count;
		this.unitPrice = unitPrice;
		this.total = total;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
