package ro.pub.cs.systems.eim.practicaltest02.views.model;

public class CurrencyInformation {
    private String rateUSD;
    private String rateEUR;
    private String updated;

    public CurrencyInformation() {
        this.rateUSD = null;
        this.rateEUR = null;
        this.updated = null;
    }

    public CurrencyInformation(String rateUSD, String rateEUR, String updated) {
        this.rateUSD = rateUSD;
        this.rateEUR = rateEUR;
        this.updated = updated;
    }

    public String getRateUSD() {
        return rateUSD;
    }

    public void setRateUSD(String rateUSD) {
        this.rateUSD = rateUSD;
    }

    public String getRateEUR() {
        return rateEUR;
    }

    public void setRateEUR(String rateEUR) {
        this.rateEUR = rateEUR;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
