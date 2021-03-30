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
        double x = point.getCoordinate().x;
        double y = point.getCoordinate().y;
        Gps gps = new Gps();
        gps.setLat(new BigDecimal(x));
        gps.setLon(new BigDecimal(y));
//        jsonGenerator.writeString(ow.writeValueAsString(gps));
        jsonGenerator.writeObject(gps);
    }

}
