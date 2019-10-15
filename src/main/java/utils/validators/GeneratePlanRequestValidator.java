package utils.validators;

import models.GeneratePlanRequest;

public class GeneratePlanRequestValidator {

    public boolean isValid(GeneratePlanRequest generatePlanRequest) {

        if (generatePlanRequest.getLoanAmount() == null || generatePlanRequest.getLoanAmount().isEmpty() || isNumeric(generatePlanRequest.getLoanAmount()))
            return false;

        if (generatePlanRequest.getNominalRate() == null || generatePlanRequest.getNominalRate().isEmpty() || isNumeric(generatePlanRequest.getNominalRate()))
            return false;

        if (generatePlanRequest.getDuration() < 1)
            return false;

        return true;
    }


    private boolean isNumeric(String strNum) {
        return strNum.matches("\\d+(\\.\\d+)?");
    }
}