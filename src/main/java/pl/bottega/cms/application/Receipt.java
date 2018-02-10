package pl.bottega.cms.application;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Receipt {
	private List<ReceiptLine> receiptLines = new LinkedList<>();
	private BigDecimal totalPrice = BigDecimal.ZERO;

	public List<ReceiptLine> getReceiptLines() {
		return receiptLines;
	}

	public void setReceiptLines(List<ReceiptLine> receiptLines) {
		this.receiptLines = receiptLines;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void addReceiptLine(ReceiptLine receiptLine) {
		receiptLines.add(receiptLine);
	}
}
