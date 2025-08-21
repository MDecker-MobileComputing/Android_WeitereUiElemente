package de.mide.android.weitereui_elemente.activities.staedte;

import android.location.Location;


/**
 * Für diese Record-Klasse musste in der Datei {@code app/build.gradle} die Java-Version auf 17
 * hochgedreht werden.
 *
 * @param stadtName Anzeigename der Stadt, z.B. "Hamburg"
 *
 * @param location Geo-Koordinaten der Stadt
 */
public record StadtRecord ( String   stadtName,
                            Location location ){
}

