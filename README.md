# clouseau

Clouseau uses Scalang to expose Lucene functionality via erlang-like nodes.

## Setup

define the following environment variables:
* `CLOUSEAU_NAME` (default = "clouseau@127.0.0.1")
* `CLOUSEAU_COOKIE` (default = "monster")
* `CLOUSEAU_DIR` (default = "target/indexes")
* `CLOUSEAU_DIR_CLASS` (default = "org.apache.lucene.store.NIOFSDirectory")
* `CLOUSEAU_LOCK_CLASS` (default = "org.apache.lucene.store.NativeFSLockFactory")
* `CLOUSEAU_MAX_INDEXES_OPEN` (default = 100)

## Run Application

`mvn scala:run`

## Running a local dev cluster

Use virtual environments to pass at least different `CLOUSEAU_NAME` values
