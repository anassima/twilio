Steps so far:

1. Brew installed Play! 1.3.1
2. New Play! project
3. Imported into IDEA 14
4. Pushed to GitHub
5. Pushed to Heroku
6. Added twilio-library dependency
7. TDD then skeleton implementations (patterns: builder, translator, factory)
8. Added hamcrest-library dependency for hasEntry()

MIS

Patterns, tests, local H2 database, model inheritance, decouple from Trillio, facade for call model, 

https://enigmatic-mesa-1388.herokuapp.com/

TODO

* Secure AUTH_TOKEN, for moment none for simplicity/time constraints (alternative ENV variables)
* Controller test
* File cleanup (e.g. generated tests)
* Catch callbacks
* Implement Mast's number
* Use collected data
* What if API is down? Error log entry for grep?