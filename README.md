README
----------

Spacey is a project which contains some 'test the waters' code.

We are interested in these:

JOOQ (No-Hibernate, better integration with SQL server, ability to fine tune SQL statements)
- Easy to handle multi-tenet cases w/o a lot of work!
- Easy to handle multi-connection pools w/o a lot of hassle!
- Removes a large portion of class files (smaller footprint)
- Remove auto-magic from the equation, EXPLICIT FOR THE WIN!
- Compiles Java objects from SQL schema, a la SOAP. Code integration issues found easier.
- Forces Java code to be staticly typed

REST (JAX-RS -> RestEasy or Jersey, Maybe Restlet?)
- JSON bindings a must
- XML a possibility
- HTML a possibility
- Other formats???
- Probably should have a good setup to allow rendering in any format
- Light weight HTTP layer, ie most code will easily land in the business object
- Proper use of HEAD to allow clients to detect changes?

Guice, we need to have good DI which enables good testing.

Mockito, because it makes mocking in Java simple.

Server Sent Events, allow clients to connect and stay connected and receive data! A very easy to integrate with way to federate data.

Websockets, would be very useful in communication scenarios which we definitely would like to pursue.

