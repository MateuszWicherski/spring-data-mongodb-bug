package pl.wicherski.example.springdatamongodbbug;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmbeddedObject {

    private String embeddedField;

}
