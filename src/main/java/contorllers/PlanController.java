package contorllers;

import exceptions.BadRequestException;
import models.GeneratePlanRequest;
import models.Installment;
import org.eclipse.jetty.http.HttpStatus;
import services.PlanService;
import spark.Request;
import spark.Response;
import utils.jsonParser.GsonJsonParser;
import utils.jsonParser.JsonParser;
import utils.validators.GeneratePlanRequestValidator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PlanController {

    static JsonParser jsonParser = new GsonJsonParser();
    static PlanService planService = new PlanService();
    static GeneratePlanRequestValidator validator = new GeneratePlanRequestValidator();

    public static Object post(Request request, Response response) {
        GeneratePlanRequest generatePlanRequest = jsonParser.fromJson(request.body(), GeneratePlanRequest.class);

        if (validator.isValid(generatePlanRequest)) {
            throw new BadRequestException();
        }


        int duration = generatePlanRequest.getDuration();
        Date date = generatePlanRequest.getStartDate();
        BigDecimal pv = generatePlanRequest.getBigDecimalLoanAmount();
        BigDecimal rate = generatePlanRequest.getBigDecimalNominalRate();

        List<Installment> installmentList = planService.generatePlan(pv, rate, duration, date);

        response.status(HttpStatus.CREATED_201);
        return jsonParser.toJson(installmentList);
    }
}
