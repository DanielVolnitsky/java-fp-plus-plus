The project attempts to demonstrate a functional approach to writing Java code via using the Vavr library and adhering to functional principles

Domain description:   
We have a quote source with multiple quotes stored.   
We would like to have a validation service that'd decide on whether a quote is valid or not.  
The quote is valid when:

* some text is present
* author is specified
* language is within the supported languages (ukrainian, english)
* quote source is of an expected value (either book or person)

We'd like to have an API that'd allow use to fetch a random quote. In case we fetch a quote that does not satisfy our
predefined validation rules - we'd like to see the quote and its validation error details.

Covered topics:

* Preserving/Suppressing exceptions within lambdas
* Try
* Either
* Tuple
* Validation
* Lazy

Not covered in code, but important:

* Memoization
* Currying

[Vavr documentation](https://docs.vavr.io/#_introduction)