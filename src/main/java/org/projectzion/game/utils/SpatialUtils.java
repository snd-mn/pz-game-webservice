package org.projectzion.game.utils;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;
import org.springframework.stereotype.Component;

@Component
public class SpatialUtils {

    //move to SpatialUtils
    public static Geometry wktToGeometry(String wellKnownText)
            throws Exception {
        return new WKTReader().read(wellKnownText);
    }
}
