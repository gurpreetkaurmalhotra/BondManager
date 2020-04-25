# BondManager
BondManager

Bond Portfolio Management –
Types of users (UserType enum)–
1. Administrator
2. Sales person
3. Customer
Refer http://localhost:4480/swagger-ui.html for the list of apis
Steps to run the project –
1. mvn clean install
2. java -Dserver.port=4480 -jar target/bond-manager-1.0-SNAPSHOT.jar --
spring.profiles.active=dev

Download csv by admin
curl --location --request GET &#39;http://localhost:4480/download/csv&#39;
This api fetches records in chunks, each time I am fetching some part of the data and
writing that much part to the csv, so I never have to load the whole data in memory.

Steps -

1. Used setFetchSize(500) to limit the result set to 500 rows at a time. This will
reduce the number of rows in the memory.
2. Sorting the data according to the date in asc order so that the records entered
during this are also fetched.
3. Used ScrollableResults to query the DB.
4. Did this in read only mode so that the DB is not affected.

Create User Api –

curl --location --request POST &#39;localhost:4480/user/create&#39; \
--header &#39;X-quikr-client: monetization&#39; \
--header &#39;Content-Type: application/json&#39; \
--data-raw &#39;{
&quot;name&quot; : &quot;abcUser2&quot;,
&quot;userType&quot; : &quot;SALESPERSON&quot;
}&#39;
Add Bond Api –

Validation added for userType. If userType = “ADMIN” only then a bond will be added.

curl --location --request POST &#39;localhost:4480/bond/add&#39; \
--header &#39;X-quikr-client: monetization&#39; \
--header &#39;Content-Type: application/json&#39; \
--data-raw &#39;{
&quot;userId&quot; : 2,
&quot;bondName&quot; : &quot;bond1&quot;,
&quot;bondDescription&quot;: &quot;bond first&quot;,
&quot;price&quot;: 100,
&quot;returnPercentage&quot;: 10,
&quot;profitPredictionPercentage&quot; : 20
}&#39;
Delete Bond Api –
Only Admin is allowed to delete the bond. And check before deleting if it is held by any
customer or not.

curl --location --request POST &#39;http://localhost:4480/bond/delete&#39; \
--data-raw &#39;&#39;

Sell Bond Api –
Sales type is recorded as {SalesPersonToCustomer, CustomerToCustomer}
curl --location --request POST &#39;localhost:4480/bond/sell&#39; \
--header &#39;Content-Type: application/json&#39; \
--data-raw &#39;{
&quot;bondId&quot;: 2,
&quot;buyerId&quot;:1,
&quot;sellerId&quot;: 2
}&#39;
Api for admin to check all the customers for a specific bond –
curl --location --request GET
&#39;localhost:4480/bond/customerDetailsByBondId?bondId=1%0A&#39;
Api for admin to check all the bonds held by a customer –
curl --location --request GET
&#39;localhost:4480/bond/getBondsForCustomer?userId=1%0A&#39;
