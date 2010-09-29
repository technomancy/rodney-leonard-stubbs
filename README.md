# Rodney Leonard Stubbs

> "He was the Thomas Edison of handing a dude his ass."

[Rodney Leonard Stubbs](http://achewood.com/index.php?date=01272006)
may very well be the simplest stubbing system ever.

## Usage

Add <tt>rodney.leonard.stubbs</tt> to your <tt>:hooks</tt> list in
project.clj. Add <tt>:mock</tt> metadata to functions to have them
replaced at test-time with their mocks.

    (defn ^{:mock (constantly [:jeep1])} release-jeeps
      (network-intensive-operation))

Now when tests are run (via <tt>clojure.test/run-tests</tt> in either
the <tt>lein test</tt> task or manually in a repl/swank session), the
<tt>release-jeeps</tt> function will be replaced with a function that
returns <tt>[:jeep1]</tt> no matter what args it's called with.

Note that this replacement happens using <tt>alter-var-root</tt>, not
<tt>binding</tt>, so it will affect all threads, and it will not be
reverted at the end of the test run. This may be useful to help with
repl exploration.

Also note that the var's original value will be kept on the
<tt>:stubbs/original</tt> key in the metadata of the replacement in
case it's needed.

## License

Copyright (C) 2010 Phil Hagelberg

Distributed under the Eclipse Public License, the same as Clojure.
