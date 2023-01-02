# News Client

An HTTP Client that fetches some random news and gives three types of reports about the fetched news.

## Running the app
You can run the app - locally with Maven, or in your IDE.

**Prerequisites**
These instructions assume you have the following installed:
- Maven
- Java
- JDK

Installation instructions for the above are readily available online.

**Running via Maven**

- Clone the repo to your development machine
- cd into the project folder once cloned
- mvn spring-boot:run

Once running, the client will connect with the local server via localhost:8080 and generate the three reports.

## Reports
There are three types of reports, each of them has a .csv format:
- **reportAll**: A report containing the Title, Description and Id of all the articles retrieved.
- **reportByCategory**: A report that gives the number of articles for the categories retrieved.
- **reportMinimal**: A report containing only the Title and Category of each article retrieved.