package services;

import models.Installment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//Tests based on https://www.calculatestuff.com/financial/loan-amortization-calculator
class PlanServiceTest {

    PlanService planService =  new PlanService();


    @Test
    void ThousandOnePercentOneYearPlanTest() {
        List<Installment> installmentList = planService.generatePlan(new BigDecimal(1000),new BigDecimal(1),12, Calendar.getInstance().getTime());

        assertEquals(12,installmentList.size());

        Installment i = installmentList.get(0);
        assertEquals(new BigDecimal("82.96"),i.getPrincipal());
        assertEquals(new BigDecimal("0.83"),i.getInterest());
        assertEquals(new BigDecimal("83.79"),i.getBorrowerPaymentAmount());
        assertEquals(new BigDecimal("917.04"),i.getRemainingOutstandingPrincipal());


        i = installmentList.get(11);
        assertEquals(new BigDecimal("83.68"),i.getPrincipal());
        assertEquals(new BigDecimal("0.07"),i.getInterest());
        assertEquals(new BigDecimal("83.75"),i.getBorrowerPaymentAmount());
        assertEquals(new BigDecimal("0"),i.getRemainingOutstandingPrincipal());
    }


    @Test
    void TwoThousandTenPercentThreeYearPlanTest() {
        List<Installment> installmentList = planService.generatePlan(new BigDecimal(2000),new BigDecimal(10),36, Calendar.getInstance().getTime());

        assertEquals(36,installmentList.size());

        Installment i = installmentList.get(0);
        assertEquals(new BigDecimal("47.86"),i.getPrincipal());
        assertEquals(new BigDecimal("16.67"),i.getInterest());
        assertEquals(new BigDecimal("64.53"),i.getBorrowerPaymentAmount());
        assertEquals(new BigDecimal("1952.14"),i.getRemainingOutstandingPrincipal());


        i = installmentList.get(35);
        assertEquals(new BigDecimal("64.17"),i.getPrincipal());
        assertEquals(new BigDecimal("0.53"),i.getInterest());
        assertEquals(new BigDecimal("64.70"),i.getBorrowerPaymentAmount());
        assertEquals(new BigDecimal("0"),i.getRemainingOutstandingPrincipal());
    }
}