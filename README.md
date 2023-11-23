# Retro_product-management
For Java code I named as "demo"
I Used Postman to Create a New Retrospective
Method: POST
URL: http://localhost:8083/api/retrospectives
Headers: Content-Type: application/json
Body (raw, JSON):

{
    "name": "EndOfYearRetrospective",
    "summary": "Summary of the retrospective...",
    "date": "2023-11-30",
    "participants": ["Ganes", "Harshu", "sweety"]
}

We Can Add Feedback to a Retrospective
Method: POST
URL: http://localhost:8083/api/retrospectives/EndOfYearRetrospective/feedback
Headers: Content-Type: application/json
Body (raw, JSON):

{
    "name": "Alice",
    "body": "Great collaboration in the last sprint.",
    "feedbackType": "Positive"
}

Update Feedback for a Retrospective
Method: PUT
URL: http://localhost:8083/api/retrospectives/EndOfYearRetrospective/feedback/0 (assuming 0 is a valid feedback ID)
Headers: Content-Type: application/json
Body (raw, JSON):
{
    "name": "Alice",
    "body": "Updated feedback about the last sprint.",
    "feedbackType": "Positive"
}

Search for Retrospectives by Date
Method: GET
URL: http://localhost:8083/api/retrospectives/searchByDate?date=2023-11-30
No Body or Headers needed.



Retrieve a Paginated List of Retrospectives
Method: GET
URL: http://localhost:8083/api/retrospectives?page=2&size=2 (we can use whatever we want and depends on how many retros we create)
Here, page=2 and size=2 indicate that you're requesting the third page (as the page count starts from 0) of retrospectives with two retrospectives per page.
Headers: None needed unless your API requires them.
Body: None needed for a GET request.

Run the Test, 
I have used Eclipse for the coding
In the Eclipse Package Explorer, expand  the project tree to find the RetrospectiveServiceTest class.
This class is  located in the src/test/java directory under the appropriate package, which in your case seems to be com.srum.ceremony.demo.service.
Right-click anywhere in the RetrospectiveServiceTest code in the editor or in the Package Explorer.
Select "Run As" > "JUnit Test".


For Frontend product We have to create a project by using npx create-react-app product-management
and just the main code is in two parts one is app.css and one more is app.js then you can run it by npm start it will connect to local server 3000 
and I implemented a button called "add new product" so you can see fields to add the new item below the button
