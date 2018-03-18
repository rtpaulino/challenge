# challenge
Rest Java App Challenge

## Database

I've chosen MySQL to be used.

```
> docker run --name skip_challenge -p 3306:3306 -e MYSQL_ROOT_PASSWORD=mysql -d mysql:5.7
```

## RabbitMQ

```
> docker run -d --hostname mq --name mq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

Access `http://localhost:15672` and create the following queues:

1. new_order_queue
2. cancel_order_queue
3. update_status_queue
 
> **INFO:** Default user/password: guest/guest


## Initializing Application

```
> ./gradlew bootRun
```

## Some considerations

1. I have opted to do the basic stuff for later get it more optimized;

2. Some optimizations that could be done:
   - Separated this projects into different services:
     - Authentication
     - Products
     - Order
   - Cache (using Redis for example)
   - ElasticSearch (use elasticsearch for product search)
     
3. I've created some events which later send messages to a broker. It was
   a simple implementation and need to be carefully handled (like errors for example).
   
4. We could make some opt

5. Order "Locking" - I've used database level locking (pessimistic write) to avoid
   concurrent updates. I'm not pretty sure that this is the best approach, we should
   check to see if it can handle the incoming load. Another option could be using @Version
   or a different architecture or event database for that kind of transaction.
   
6. Would be interesting to create a docker and/or docker compose. This make easier
   deploying, maintaining and scaling the service;
   
7. We should also have created Spring Boot Profiles to have different properties on different
   environments. We could also use ENVIRONMENT VARIABLES in configuration files
   to handle, for example, database connection, rabbitmq connection, etc.
   
8. I have decided to use `JWT Tokens` instead of sessions. This might be of better use
   when dealing with microservices. Also, it would be easier to integrate with third-party
   authentication (google, faceboot, etc.). That way, all requests are `Stateless` which
   make the service easier to **scale**.

## Test

There was not enough time during the day to work on tests, so I have provided just one simple example.

## Postman

The file `Skip Challenge.postman_collection.json` can be imported to easy manual test the REST
service.

Be aware to create an `Environment` and define 'authorization' variable.

Then, after registering an user, you should login and, after that, the calls will be using
the Bearer authentication.
