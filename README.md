# tenant-payment-history
REST API to fetch / update / create / delete tenant payment history.

This API can be used landlords to manage their apartments. Via the app, landlords are able to see details about their rental contracts, check which tenants have paid the rent, and send reminders to tenants when rent is overdue. 

Various APIs to create / update / get / delete a payment history of a contract are provided.

## To build the application :
mvn clean install

## To run the application :
mvn spring-boot:run

## APIs and their usages :
| API Path | HTTP Method | Description |
| -- | -- | -- |
| /payments | POST | Creates a payment history for a specific contract |
| /payments/{paymentId} | GET | Gets the payment by id |
| /payments/{paymentId} | PUT | Updates the payment by id |
| /payments/{paymentId} | DELETE | Deletes a payment by id |
| /contracts/{contractId}/payments | GET | Search payment history by contractId whose due date lies between startDate and endDate |
