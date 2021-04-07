package org.projectzion.game.utils.json.serializer;

import org.projectzion.game.utils.Gps;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.vividsolutions.jts.geom.Point;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;

@JsonComponent
public class PointSerializer extends JsonSerializer<Point> {

    public static ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Override
    public void serialize(Point point, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Gps gps = new Gps();
        gps.setLon(point.getCoordinate().x);
        gps.setLat(point.getCoordinate().y);
        jsonGenerator.writeObject(gps);
    }

}
