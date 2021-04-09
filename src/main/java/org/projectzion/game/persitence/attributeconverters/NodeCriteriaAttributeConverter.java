package org.projectzion.game.persitence.attributeconverters;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.projectzion.game.services.overpass.turbo.NodeCriteria;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
//https://ilhicas.com/2019/04/26/Persisting-JSONObject-Using-JPA.html
@Converter
public class NodeCriteriaAttributeConverter implements AttributeConverter<NodeCriteria, String> {

    //    @Autowired
    ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(NodeCriteria attribute) {
        String ret = mapper.writeValueAsString(attribute);
        return ret;
    }

    @SneakyThrows
    @Override
    public NodeCriteria convertToEntityAttribute(String dbData) {
        NodeCriteria nodeCriteria = mapper.readValue(dbData, NodeCriteria.class);
        return nodeCriteria;
    }
}
