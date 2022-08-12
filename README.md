# Talentql-backend-project
# This backend project was an assessment to create an Endpoint to that accepts date of birth as a query parameter and returns the current age
# with a rate limiter that allows client/caller to send 3 request in 1 second.
# Implementatin /Design
# This application is a springboot application which is a stand alone application.
# This application is sectioned into 5 packages:
# 1. The controller : Which is the entry point for all api request into this application.
# 2. The service: This is where I have the business logic that calculates the age using the date of birth passed as a query parameter and also where the rate limiter is being enforced.
# 3. The Configuration : This package has a class RedisConfig that has the configuration for redis in other to manage cache since this is a distributed system.
# 4. The Util : This package has a class with a method to instantiate the bucket and ensure tokens are refilled after every request has gone pass their limit.
# 5. The DTO: This has a data transfer object class that helps to handle responses based on the parameter passed to the method in the service class.

# In my design I used bucket4j dependency which applies the token bucket algorithm to handle request congestions. The token bucket algorithm is not the best but it is quite better than the leaky bucket algorithm, because it handles request faster and also in a more distributed way compared to the leaky bucket algorithm.

# Brief Explanation on how the token bucket algorithm works using this task as a use case
# 1.In the implementation a limit was set in this case the limit was 3, and this sets the number of token in the bucket which can be used within a second
# 2.When user sends the first request, the bucket checks if there are enough tokens, if yes it removes the token and then sends a packet in this case allows the request pull through without throwing any error at this point we are left with two tokens
# 3.When user sends the second token with the same 1 second, it takes out the next token and the sends packets.
# 4.On the third request within the 1 second it still sends the packets but by this time it takes out the last token.
# 5.On the fourth request within the 1 second at this points there are no tokens left in the bucket so it won't send any packet and then it'd throw an error
# 6.At the end of every 1 second it refills the bucket with another set of tokens and then the circle continues.


# HOW DOES IT WORK
# Client/ Caller sends a get request to this end point "" with a request parameter date of birth
# The date birth is passed to a method calculate age.
# The following checks happen in method calculate age:
# 1. It takes the date of birth, and check if it is a valid date, if no it throws a 404 error Bad request with a message "Invalid date format"
# 2. If date passes check in step 1, then we have bucket object that checks if there is enough token for the request to go through within the specified duration, it calaculates the age by using a library to check the difference between the date of birth passed and the current date and then returns the age.
# 3. If user sends request beyond the limit, in this case 3 it returns error code 429 "TOO_MANY_REQUEST" with a message that says "Rate limit exceeded, Please try again in 1 second."

