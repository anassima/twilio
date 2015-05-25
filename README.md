# About

Coding test for Mast Mobile. Took approximately 12 hours. 

Screenshot: http://www.awesomescreenshot.com/image/260720/1ad30533de36d8eb38971c508aa56ec1

# Rules

1. Use any language
2. Public repository on GitHub
3. Attach MIT license
4. Project should take 1 day

# Problem

At Mast Mobile we’re operating a mobile phone network for businesses and their employees.  
As part of running this service, we must ensure that phone calls work as well as – or better than – any of our 
competitors. This is important because part of our offering is software and services that modify call processing and as 
we improve those services, we must ensure that calls continue to function reliably.

**Basics**

* Use the Twilio API
* Application that repeatedly calls a phone using the API
* Analysis on data returned from API (reliability, quality, display data, test calling)
* Log and display results

# Overview

* TDD using JUnit, Hamcrest and Mockito. Unit and integration tests.
* Tools: IDEA 14 (static code analysis) and Git
* Play! Framework
* Deployed to Heroku and Amazon RDS (varying success)
* System components:
 * /builders: Builder pattern for constructing Twilio outbound call parameter map
 * /controllers: web view and associated function mapped to URL routes
 * /factories: CallService factory for returning a concrete implementation
 * /jobs: Job for repeatedly calling numbers. Randomly calls from a predefined list.
 * /models: PlacedOutgoingCall extends OutgoingCall and provides additional non-null properties
 * /servies: Concrete Twilio implementation of an interface 
 * /translators: Converts from a Twilio call object to local OutgoingCall object
* Features:
 1. Dial a number via Twilio API
 2. Retrieve all calls from Twilio API and persist locally
 3. Start/stop background job that repeatedly call from a list of numbers

# Design and Assumptions

* Web application using Play! framework for rapid web application development
* Use of factory, builder and translator patterns
* 'Clean Code' principles (e.g. naming, only necessary code comments, DRY, SRP, small functions, avoid magic numbers...)
* Use of CallService interface to decouple Twilio implementation and allow future flexibility and extensibility (i.e. swap out Twilio)
* Integration test found at TwilioIntegrationTest
* Persists data to a local H2 file system database (attempted AWS RDS)

# Issues

Most issues revolved around deployment to Heroku from Play! framework, which can be fixed with more time.

* Attempted to use an AWS RDS instance as the database, but Heroku throws a boot timeout exception
* Web application previously running on Heroku, but change to model table name throws an exception (table not found). 
Difficult to debug, runs locally, can't inspect database, can't use RDS. Extremely frustrating with limited time.
* Attempted WAR deployment to Elastic Beanstalk, but throws a permission exception

# Enhancements

* Deploy to Heroku
* Extract sensitive information (Twilio API token, RDS credentials) to local environment variables (or outside of Git)
* Display the current job status (started, stopped) on the home page
* Implement a callback web service for receiving notification from Twilio
* Improved HTML5 presentation
* Reports on reliability and quality

# Analysis

We want to measure call reliability and quality at a granular level with thorough reporting options. 
All data should be persisted, reportable (both API and visually) with the ability to drill-down as needed. 

### Reliability

**Twilio API Uptime**

API failures (e.g. connectivity) should be logged (server log, database) and raise critical alerts (e.g. email, SMS). 
If possible, additional redundancy through a fallback call provider could be considered (hence the decoupled architecture approach). 

**Call Success**

Poll API or consume callbacks to retrieve the status of all placed calls for a given time period. 
Data should be presented with many time period filters and display the percentage of completed (i.e. successful) calls. 
Failure patterns need to be identified for patterns (e.g. time, to/from numbers, configuration) for potential fixes. 

**Callbacks**

Provide a FallbackURL on a different server (e.g. different AWS AZ) with additional alerts to indicate a server outage. 

### Quality

**Dropouts**

Report on placed calls with very low durations and repeated calls within a short timeframe (i.e. callback), which but 
may indicate dropouts. These would need to be monitored and followed-up separately (e.g. contact customer for feedback).

**Latency**

Capture and persist the lifecyle of each call through callbacks. It is then possible to track the actual time between
a call is queued and the call is queued, initiated and ringing (https://www.twilio.com/resources/images/docs/status-callback-events.png). 
Reports on latency of each status and alerts if thresholds are exceeded.

**Line Quality**

Due to the subjective nature, implement the Feedback URL post-call to survey a user on quality. This could be integrated
in a mobile or browser environment used to initiate calls. Data from the surveys should be persisted and reported against
(quality score 1-5 and issues). 

Additionally (privacy nonwithstanding) a batch job could be created to retrieve random sample call transcripts and recordings -- 
analysing them for quality and assigning a score to be reported on. Transcripts could be checked for spelling, recordings
could be checked for noise.

### Testing

Adjustable parameters that may affect calls include timeout, recording TRUE/FALSE and answering machine recognition. 
Testing failures should be accomplished through invalid numbers and stress testing.

# How to Run

1. Install Play! 1.3.0 (http://downloads.typesafe.com/play/1.3.0/play-1.3.0.zip)
2. Clone the GitHub repository
3. Update the constant AUTH_TOKEN in TwilioCallService.java with your API token
4. In Terminal, navigate to the project directory and enter the command 'play run'
5. Open the browser and navigate to 'http://localhost:9000'