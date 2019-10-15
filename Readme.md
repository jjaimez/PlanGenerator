##Solution

While the application is simple. The code has been structured as if were a bigger or complex app.

The app uses BigDecimal to represent currency. Precision has been set at 2 decimals points and half up rounding.

I add tests examples, real code should have more cases derivatives including at least border cases in business logic.

### App Structure
```
├── controllers
|        Serializes requests into objects and validates it.
|
├── services
|       Business logic.
|
├── utils
|       Provides utilities that help to work with Json and Dates.
|
├── exceptions
|
├── router
|       Handles the routes of the API. Connect request with controllers.
|
├── Main
|
└──
```

### Requestss

The API runs in http://localhost:8080

**Post list of url**
----

**URL** : `/generate-plan`

**Method** : `POST`

**example**

```json
     {
     	"loanAmount": "5000",
     	"nominalRate": "5.0",
     	"duration": 24,
     	"startDate": "2018-01-01T00:00:01Z"
     }
```

**Success Response:**

**Code** : `201 CREATED`

**Body:**
```json
    [
        {
    		"borrowerPaymentAmount": 219.36,
    		"date": "2018-02-01T10:00:01.000Z",
    		"initialOutstandingPrincipal": 5000,
    		"interest": 20.83,
    		"principal": 198.53,
    		"remainingOutstandingPrincipal": 4801.47
    	},
    	{
    		"borrowerPaymentAmount": 219.36,
    		"date": "2018-03-01T10:00:01.000Z",
    		"initialOutstandingPrincipal": 4801.47,
    		"interest": 20.01,
    		"principal": 199.35,
    		"remainingOutstandingPrincipal": 4602.12
    	},

    	....

        {
    		"borrowerPaymentAmount": 219.36,
    		"date": "2019-12-01T10:00:01.000Z",
    		"initialOutstandingPrincipal": 435.91,
    		"interest": 1.82,
    		"principal": 217.54,
    		"remainingOutstandingPrincipal": 218.37
    	},
    	{
    		"borrowerPaymentAmount": 219.28,
    		"date": "2020-01-01T10:00:01.000Z",
    		"initialOutstandingPrincipal": 218.37,
    		"interest": 0.91,
    		"principal": 218.37,
    		"remainingOutstandingPrincipal": 0
    	}
    ]

```

## Instructions

### Running

Requirements:
- \>= Java 8 environment

```
mvn package

java -jar target/PlanGenerator-jar-with-dependencies.jar
```

## Development / Test

Requirements:
- \>= Java 8 environment
- Maven 3
