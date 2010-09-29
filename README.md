# Rodney Leonard Stubbs

> "He was the Thomas Edison of handing a dude his ass."

Rodney Leonard Stubbs may very well be the simplest stubbing system ever.

http://achewood.com/index.php?date=01272006

## Usage

Add rodney.leonard.stubbs to your <tt>:hooks</tt> list in
project.clj. Add <tt>:mock</tt> metadata to functions to have them
replaced at test-time with their mocks.

    (defn ^{:mock (constantly [:jeep1])} release-jeeps
      (network-intensive-operation))

Now when tests are run (via <tt>clojure.test/run-tests</tt>), the
release-jeeps function will be replaced with a function that returns
<tt>[:jeep1]</tt> no matter what args it's called with.

Note that this replacement happens using <tt>alter-var-root</tt>, not
<tt>binding</tt>, so it will affect all threads, and it will not be
reverted at the end of the test run. This may be useful to help with
repl exploration.

Also note that the var's original value will be kept on the
:stubbs/original key in the metadata of the replacement in case it's
needed.

## License

Copyright (C) 2010 Phil Hagelberg

Distributed under the Eclipse Public License, the same as Clojure.
