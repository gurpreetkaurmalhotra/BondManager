Bond Portfolio Management –

Types of users (UserType enum)–
1.	Administrator
2.	Sales person
3.	Customer

Refer http://localhost:4480/swagger-ui.html for the list of apis

Steps to run the project –
1.	Mvn install
2.	java -Dserver.port=4480 -jar target/bond-manager-1.0-SNAPSHOT.jar --spring.profiles.active=dev


Download csv by admin

curl --location --request GET 'http://localhost:4480/download/csv'

This api fetches records in chunks, each time I am fetching some part of the data and writing that much part to the csv, so I never have to load the whole data in memory.

Steps -

1.	Used setFetchSize(500) to limit the result set to 500 rows at a time. This will reduce the number of rows in the memory.
2.	Sorting the data according to the date in asc order so that the records entered during this are also fetched.
3.	Used ScrollableResults to query the DB.
4.	Did this in read only mode so that the DB is not affected.


Create User Api –

curl --location --request POST 'localhost:4480/user/create' \
--header 'X-quikr-client: monetization' \
--header 'Content-Type: application/json' \
--data-raw '{
	"name" : "abcUser2",
	"userType" : "SALESPERSON"
}'

Add Bond Api –

Validation added for userType. If userType = “ADMIN” only then a bond will be added.

curl --location --request POST 'localhost:4480/bond/add' \
--header 'X-quikr-client: monetization' \
--header 'Content-Type: application/json' \
--data-raw '{
	"userId" : 2,
	"bondName" : "bond1",
	"bondDescription": "bond first",
	"price": 100,
	"returnPercentage": 10,
	"profitPredictionPercentage" : 20
}'

Delete Bond Api –

Only Admin is allowed to delete the bond. And check before deleting if it is held by any customer or not.


curl --location --request POST 'http://localhost:4480/bond/delete' \
--data-raw ''


Sell Bond Api –
Sales type is recorded as {SalesPersonToCustomer, CustomerToCustomer}

curl --location --request POST 'localhost:4480/bond/sell' \
--header 'Content-Type: application/json' \
--data-raw '{
	
	"bondId": 2,
	"buyerId":1,
	"sellerId": 2
}'

Api for admin to check all the customers for a specific bond –

curl --location --request GET 'localhost:4480/bond/customerDetailsByBondId?bondId=1%0A'

Api for admin to check all the bonds held by a customer –

curl --location --request GET 'localhost:4480/bond/getBondsForCustomer?userId=1%0A'

