# Example to reproduce bug with JSON schema generation

## Description

There is a bug when generating JSON schema with `MongoJsonSchemaCreator` based on `@Document` class - `collection like`
fields are not resolved correctly. For example `List<ObjectType>` is resolved as an `object` in the resulting schema,
not an `array` of `objects`. Additionally, `List<String>` and `List<Enum>` are resolved as just `array` without the
typing of the `array` items, which would be expected as it is supported by MongoDB.

## Reproduction

Test for showing the problem can be found
in `src/test/java/pl/wicherski/example/springdatamongodbbug/SpringDataMongodbBugApplicationTests`.

Computing the schema for `List<ObjectType>` ends on the line `118` in `MappingMongoJsonSchemaCreator`, while it should
probably be computed by line `124` (it can be caught with debugger while running the provided test).
