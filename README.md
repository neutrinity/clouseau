# clouseau

Clouseau uses Scalang to expose Lucene functionality via erlang-like nodes.

## Run Application

`mvn scala:run`

The following arguments can be passed, in this order:
* `clouseau.name` (default = "clouseau@127.0.0.1")
* `clouseau.cookie` (default = "monster")
* `clouseau.dir` (default = "target/indexes")
* `clouseau.dir_class` (default = "org.apache.lucene.store.NIOFSDirectory")
* `clouseau.lock_class` (default = "org.apache.lucene.store.NativeFSLockFactory")
* `clouseau.max_indexes_open` (default = 100)

Example: `mvn scala:run -DaddArgs=clouseau@127.0.0.1|monster|target/indexes"`

## Running a local dev cluster

Pass at least different values for: `clouseau.name`, `clouseau.cookie`, `clouseau.dir` 
 
You could also use the launchers, defined in the pom.xml:
- `mvn scala:run -Dlauncher=clouseau1`
- `mvn scala:run -Dlauncher=clouseau2`
- `mvn scala:run -Dlauncher=clouseau3`
