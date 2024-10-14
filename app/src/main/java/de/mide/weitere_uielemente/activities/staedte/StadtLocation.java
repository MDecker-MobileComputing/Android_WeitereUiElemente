package de.mide.weitere_uielemente.activities.staedte;

import android.location.Location;


/**
 * Eigene Unterklasse von {@code Location}, um einen Konstruktor zu definieren,
 * der nur die Koordinaten übergeben bekommt.
 * <br><br>
 *
 * Die Klasse {@code Location} verfügt über die Methode {@code distanceTo()},
 * die die Entfernung zu einem anderen als Argument übergebenen
 * {@code Location}-Objekt berechnet.
 * <br><br>
 *
 * Beispiel:
 * <pre>
 * {@code
 * Location location1 = new StadtLocation( 13.404954, 52.520008 );
 * Location location2 = new StadtLocation(  9.993682, 53.551086 );
 * int distanceInMeters = (int) location1.distanceTo( location2 );
 * int distanceInKilometers = distanceInMeters / 1000;
 * }
 * </pre>
 */
public class StadtLocation extends Location {

    /**
     * Konstruktor, der nur die Geo-Koordinaten für eine übergeben bekommt.
     *
     * @param geoLaenge Längengrad in Dezimalkoordinaten (für "Westlich" negative Werte,
     *                  für "Östlich" positive Werte)
     *
     * @param geoBreite Breitengrad in Dezimalkoordinaten (für "Nördlich" negative Werte,
     *                  für "Südlich" positive Werte)
     */
    public StadtLocation(double geoLaenge, double geoBreite) {

        super("Dummy-Provider");

        setLongitude( geoLaenge );
        setLatitude(  geoBreite );
    }
}
