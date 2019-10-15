package models;

import java.math.BigDecimal;
import java.util.Date;

public class GeneratePlanRequest {

    String loanAmount;
    String nominalRate;
    int duration;
    Date startDate;

    public String getLoanAmount() {
        return loanAmount;
    }

    public String getNominalRate() {
        return nominalRate;
    }

    public int getDuration() {
        return duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public GeneratePlanRequest(String loanAmount, String nominalRate, int duration, Date startDate) {
        this.loanAmount = loanAmount;
        this.nominalRate = nominalRate;
        this.duration = duration;
        this.startDate = startDate;
    }

    public BigDecimal getBigDecimalLoanAmount() {
        return new BigDecimal(loanAmount);
    }

    public BigDecimal getBigDecimalNominalRate() {
        return new BigDecimal(nominalRate);
    }


}
