package de.mide.weitere_uielemente.activities.staedte;

import android.location.Location;


/**
 * Für diese Record-Klasse musste in app/build.gradle die Java-Version auf 17 hochgedreht werden.
 *
 * @param stadtName Anzeigename der Stadt, z.B. "Hamburg"
 *
 * @param location Geo-Koordinaten der Stadt
 */
public record StadtRecord ( String   stadtName,
                            Location location ){
}

