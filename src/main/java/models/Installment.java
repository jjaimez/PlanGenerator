package models;

import java.math.BigDecimal;
import java.util.Date;

public class Installment {

    BigDecimal borrowerPaymentAmount;
    Date date;
    BigDecimal initialOutstandingPrincipal;
    BigDecimal interest;
    BigDecimal principal;
    BigDecimal remainingOutstandingPrincipal;


    public Installment(BigDecimal borrowerPaymentAmount, Date date, BigDecimal initialOutstandingPrincipal, BigDecimal interest, BigDecimal principal, BigDecimal remainingOutstandingPrincipal) {
        this.borrowerPaymentAmount = borrowerPaymentAmount;
        this.date = date;
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
        this.interest = interest;
        this.principal = principal;
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }


    public BigDecimal getBorrowerPaymentAmount() {
        return borrowerPaymentAmount;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getInitialOutstandingPrincipal() {
        return initialOutstandingPrincipal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public BigDecimal getRemainingOutstandingPrincipal() {
        return remainingOutstandingPrincipal;
    }
}
