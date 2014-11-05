studenthack2014-bmql
====================

BMQL (Bloomberg Message Query Language) - Allow for a convinient way to parse data from results returned by the Bloomberg API

Requests or Subscriptions made to Bloomberg services exposed via the Bloomberg API return Message objects that contain the requested data. Message objects contain a lot of information in addition to the data that was requested. This creates a complex structue and makes querying the data that is of interest to the developer an non-trivial task.

BMQL is a simple query language that allows the developer to execute query strings on a Message object and retrieve the data of interest. This frees the developer form having to write multiple lines of code to access the internal contents of the Message object. BMQL borrows its syntax from XPath and has a low learning curve.

This project/hack was developed at StudentHack 2014 in Manchester, UK as part of the challenge to come up with the best use of the Bloomberg API. The frustration of haiving to write a ton of code to extract data motivated me to develop this hack. The initial commit was developed in the last 8 hours of the hackathon.
