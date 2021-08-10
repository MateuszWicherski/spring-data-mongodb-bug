package pl.wicherski.example.springdatamongodbbug;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SampleDocument {

    @MongoId(targetType = FieldType.STRING)
    private String id;
    private String simpleField;
    private SampleEnum enumField;
    private EmbeddedObject embeddedObject;
    private List<String> simpleList;
    private List<EmbeddedObject> complexList;
    private List<SampleEnum> enumList;

}
