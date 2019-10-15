package services;

import models.Installment;
import utils.DateUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PlanService {

    final static BigDecimal DAYS_IN_MONTH = new BigDecimal(30);
    final static BigDecimal DAYS_IN_YEAR = new BigDecimal(360);


    public List<Installment> generatePlan(BigDecimal pv, BigDecimal r, int n, Date date) {

        List<Installment> list = new LinkedList<>();

        BigDecimal r2 = r.divide(new BigDecimal(100), MathContext.DECIMAL32);

        BigDecimal initial = pv;
        BigDecimal annuity = getAnnuity(initial, r, n);

        while (pv.compareTo(BigDecimal.ZERO) > 0) {

            date = DateUtils.addMonth(date, 1);

            BigDecimal interest = getInterest(initial, r2).setScale(2, RoundingMode.HALF_UP);

            //Principal = Annuity - Interest
            BigDecimal principal = annuity.subtract(interest).setScale(2, RoundingMode.HALF_UP);

            BigDecimal borrowerPaymentAmount = principal.add(interest).setScale(2, RoundingMode.HALF_UP);
            pv = pv.subtract(principal).setScale(2, RoundingMode.HALF_UP);

            //if last installment
            if (pv.compareTo(BigDecimal.ONE) < 0) {
                principal = initial;
                borrowerPaymentAmount = principal.add(interest).setScale(2, RoundingMode.HALF_UP);
                pv = BigDecimal.ZERO;
            }


            Installment i = new Installment(borrowerPaymentAmount, date, initial, interest, principal, pv);
            list.add(i);

            initial = pv;

        }

        return list;
    }


    //r(PV) / (1 - (1+r)^-n) where r = rate, pv = present value and n = installments
    //Note: The nominal interest rate is an annual rate and must be converted to monthly before using in the annuity formula
    private BigDecimal getAnnuity(BigDecimal pv, BigDecimal rate, int n) {

        BigDecimal monthlyRate = rate.divide(new BigDecimal(1200), MathContext.DECIMAL32);

        //r(PV)
        BigDecimal numerator = pv.multiply(monthlyRate);

        //(1+r)^-n = pow
        BigDecimal pow = (BigDecimal.ONE.add(monthlyRate)).pow(n);
        pow = BigDecimal.ONE.divide(pow, MathContext.DECIMAL32);

        //1 - pow
        BigDecimal denominator = BigDecimal.ONE.subtract(pow);

        //r(PV) / (1 - pow)
        return numerator.divide(denominator, MathContext.DECIMAL32);
    }

    //Interest = (Rate * Days in Month * Initial Outstanding Principal) / Days in Year
    private BigDecimal getInterest(BigDecimal pv, BigDecimal rate) {
        return (rate.multiply(DAYS_IN_MONTH).multiply(pv)).divide(DAYS_IN_YEAR, MathContext.DECIMAL32);

    }

}
