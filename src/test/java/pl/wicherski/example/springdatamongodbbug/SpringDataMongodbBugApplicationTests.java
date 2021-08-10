package pl.wicherski.example.springdatamongodbbug;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoJsonSchemaCreator;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;

@SpringBootTest
class SpringDataMongodbBugApplicationTests {

    private static final String EXPECTED_SCHEMA = """
                                                  {
                                                    "$jsonSchema": {
                                                      "type": "object",
                                                      "properties": {
                                                        "_id": {
                                                          "type": "string"
                                                        },
                                                        "simpleField": {
                                                          "type": "string"
                                                        },
                                                        "enumField": {
                                                          "type": "string",
                                                          "enum": [
                                                            "VALUE_1",
                                                            "VALUE_2"
                                                          ]
                                                        },
                                                        "embeddedObject": {
                                                          "type": "object",
                                                          "properties": {
                                                            "embeddedField": {
                                                              "type": "string"
                                                            }
                                                          }
                                                        },
                                                        "simpleList": {
                                                          "type": "array",
                                                          "items": {
                                                            "type": "string"
                                                          }
                                                        },
                                                        "complexList": {
                                                          "type": "array",
                                                          "items": {
                                                            "type": "object",
                                                            "properties": {
                                                              "field": {
                                                                "type": "string"
                                                              }
                                                            }
                                                          }
                                                        },
                                                        "enumList": {
                                                          "type": "array",
                                                          "items": {
                                                            "type": "string",
                                                            "enum": [
                                                              "VALUE_1",
                                                              "VALUE_2"
                                                            ]
                                                          }
                                                        }
                                                      }
                                                    }
                                                  }
                                                  """;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void shouldProduceJsonSchemaBasedOnDocumentObject() throws Exception {
        MongoJsonSchemaCreator schemaCreator = MongoJsonSchemaCreator.create(mongoTemplate.getConverter());

        MongoJsonSchema schema = schemaCreator.createSchemaFor(SampleDocument.class);
        String schemaJson = schema.toDocument()
                                  .toJson();

        JSONAssert.assertEquals(EXPECTED_SCHEMA, schemaJson, true);
    }

}
