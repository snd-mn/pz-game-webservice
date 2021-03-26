package com.example.restservice.utils;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;

public class SpatialUtils {

    //move to SpatialUtils
    public static Geometry wktToGeometry(String wellKnownText)
            throws Exception {
        return new WKTReader().read(wellKnownText);
    }
}
