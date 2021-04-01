package org.projectzion.game.utils.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import org.projectzion.game.utils.Gps;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;

@JsonComponent
public class PointDeSerializer extends StdDeserializer<Point> {

    public static ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public PointDeSerializer(){
        super(Point.class);
    }

    protected PointDeSerializer(Class<?> vc) {
        super(vc);
    }

    protected PointDeSerializer(JavaType valueType) {
        super(valueType);
    }

    protected PointDeSerializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public Point deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Object ox = ctxt.getAttribute("x");
        Object oy = ctxt.getAttribute("y");

        Coordinate[] coordinates = new Coordinate[1];
        int dimension = 3;

        coordinates[0].x = (Double) ox;
        coordinates[1].y = (Double) oy;

        CoordinateSequence coordinateSequence = new CoordinateArraySequence(coordinates,2);
        Point point = new Point(null,null);
        return null;
    }
}
