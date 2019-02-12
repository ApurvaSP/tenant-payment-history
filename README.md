# tenant-payment-history

APIs to create / update / get / delete / search a payment for a contract.

## To build the application
mvn clean install

## To run the application
mvn spring-boot:run

## APIs and their usages
| API Path | HTTP Method | Description |
| -- | -- | -- |
| /payments | POST | Creates a payment for a specific contract |
| /payments/{paymentId} | GET | Gets the payment by id |
| /payments/{paymentId} | PUT | Updates the payment by id |
| /payments/{paymentId} | DELETE | Deletes a payment by id |
| /contracts/{contractId}/payments | GET | Search payment history by contractId whose due date lies between startDate and endDate |
