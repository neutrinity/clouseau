# clouseau

Clouseau uses Scalang to expose Lucene functionality via erlang-like nodes.

## Run Application

`mvn scala:run`

The following optional arguments can be passed:
* `<configFile>` (default = "clouseau.ini", needs always to be the first argument)
* `clouseau.name=value` (default = "clouseau@127.0.0.1")
* `clouseau.cookie=value` (default = "monster", this should be more secure!)
* `clouseau.dir=value` (default = "target/indexes")
* `clouseau.dir_class=value` (default = "org.apache.lucene.store.NIOFSDirectory")
* `clouseau.lock_class=value` (default = "org.apache.lucene.store.NativeFSLockFactory")
* `clouseau.max_indexes_open=value` (default = 100)

Example: `mvn scala:run -DaddArgs=myConfig.ini|clouseau.name=clouseau@127.0.0.1|clouseau.cookie=monster|clouseau.dir=target/indexes`

The configurations in the configFile are less in priority than the arguments which are following.
If you don't want to define an extra configFile, pass a blank value like this:

Example: `mvn scala:run -DaddArgs=""|clouseau.name=clouseau@127.0.0.1|clouseau.cookie=monster|clouseau.dir=target/indexes`

## Running a local dev cluster

Pass at least different values for: `clouseau.name`, `clouseau.cookie`, `clouseau.dir` 
 
You could also use the launchers, defined in the pom.xml:
* `mvn scala:run -Dlauncher=clouseau1`
* `mvn scala:run -Dlauncher=clouseau2`
* `mvn scala:run -Dlauncher=clouseau3`
