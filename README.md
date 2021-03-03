# email service
> Design, test and send email from one application

Email service is a java based application. With this application 
you can design and test your email template locally. After finalize your email template locally,
you can send email using AWS SES.  

I have added google cloud pubsub and aws sqs to queue the email request.
Also, added an email client to use easily from the other application.

## Technology
- Java 11
- Spring boot (https://spring.io/projects/spring-boot)
- aws ses
- aws sqs
- pub-sub from GCP(google cloud platform)
- Apache FreeMarker for templating (https://freemarker.apache.org/)
- Simple Java Mail (http://www.simplejavamail.org/)
- okHttp



## Installation

From root directory run the following:

```sh
mvn clean install
cd email-service
mvn clean spring-boot:run 
```

Brouse: 
> http://localhost:6060
