package com.jsfcourse.calc;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CalcBB {
	private String kwota;
	private String lata;
	private String oprocentowanie;
	private Double result;

	@Inject
	FacesContext ctx;
	
	public String getkwota() {
		return kwota;
	}

	public void setkwota(String kwota) {
		this.kwota = kwota;
	}

	public String getlata() {
		return lata;
	}

	public void setlata(String lata) {
		this.lata = lata;
	}
	public String getoprocentowanie() {
		return oprocentowanie;
	}

	public void setoprocentowanie(String oprocentowanie) {
		this.oprocentowanie = oprocentowanie;
	}

	public Double getwynik() {
		return result;
	}

	public void setwynik(Double result) {
		this.result = result;
	}

	public boolean doTheMath() {
		try {
			double kwota = Double.parseDouble(this.kwota);
			double lata = Double.parseDouble(this.lata);
			double oprocentowanie = Double.parseDouble(this.oprocentowanie);

			result =((kwota / (12 * lata)) * (oprocentowanie / 100 )) + (kwota / (12 * lata));

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}

	

	