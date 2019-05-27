package com.sige.util;

public class TramiteTupaEstadistica {
	private Long total;
	private Long diasTotal;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Double getPromedioDias() {
		return Math.round((diasTotal / (double) total) * Math.pow(10, 2))
				/ Math.pow(10, 2);
	}

	public Long getDiasTotal() {
		return diasTotal;
	}

	public void setDiasTotal(Long diasTotal) {
		this.diasTotal = diasTotal;
	}

}
