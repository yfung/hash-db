
*In-Memory Database*
=======

## Description
An in-memory database that provides common CRUD functionality and transactions.

## How to Develop
### Build a new artifact to run
`./gradlew clean build`

Will generate the output jar in /build/libs for any additional code changes.

To run the existing jar implementation, simply use the `./run.sh`.
The script specifies a `database.jar` file in the root directory to run - newly generated JARs can be moved to the root
directory to run or adjust the path in the bash script to the newly generated JAR.

You can also run the source code using gradle `./gradlew run`

### Available commands
The database provides the following commands:

| Command  | Arguments                    | Type           | Description                                                                               |
|----------|------------------------------|----------------|-------------------------------------------------------------------------------------------|
| SET      | Database entry key and value | String, String | Inserts a database entry for a given key value, will overwrite values for a existing key. |
| GET      | Database entry key           | String         | Returns a value for a given entry queried by the key value.                               |
| DELETE   | Database entry key           | String         | Removes a database entry for a given entry key value.                                     |
| COUNT    | Database entry key           | String         | Returns the count of all entries with a given value.                                      |
| END      | N/A                          | N/A            | Exits the database program                                                                |
| BEGIN    | N/A                          | N/A            | Begins a database transaction. Nested transactions are valid.                             |
| ROLLBACK | N/A                          | N/A            | Reverts all operations in the current open transaction.                                   |
| COMMIT   | N/A                          | N/A            | Commits all operations for all open transactions.                                         |
