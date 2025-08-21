package de.mide.android.weitereui_elemente.activities;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import de.mide.android.weitereui_elemente.R;
import de.mide.android.weitereui_elemente.activities.staedte.StadtLocation;
import de.mide.android.weitereui_elemente.activities.staedte.StadtRecord;


/**
 * Activity zur Demonstration des UI-Elements "Spinner" (Dropdown-Liste zur Auswahl
 * von Einträgen): es wird die Entfernung zwischen zwei Städten berechnet.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class SpinnerActivity extends Activity
                             implements AdapterView.OnItemSelectedListener {

    /**
     * Array mit den Städten, die in den beiden Spinner-Elementen ausgewählt werden können.
     * Es handelt sich um alle Landeshauptstädte und Stadtstaaten in Deutschland.
     */
    private StadtRecord[] staedteArray = {
            new StadtRecord("Berlin"     , new StadtLocation(13.404954, 52.520008)),  // Stadtstaat
            new StadtRecord("Bremen"     , new StadtLocation( 8.807165, 53.079296)),  // Stadtstaat
            new StadtRecord("Dresden"    , new StadtLocation(13.737262, 51.050409)),  // Sachsen
            new StadtRecord("Düsseldorf" , new StadtLocation( 6.782014, 51.227741)),  // Nordrhein-Westfalen
            new StadtRecord("Erfurt"     , new StadtLocation(11.029961, 50.984766)),  // Thüringen
            new StadtRecord("Hamburg"    , new StadtLocation( 9.993682, 53.551086)),  // Stadtstaat
            new StadtRecord("Hannover"   , new StadtLocation( 9.732010, 52.375892)),  // Niedersachsen
            new StadtRecord("Kiel"       , new StadtLocation(10.137626, 54.323293)),  // Schleswig-Holstein
            new StadtRecord("Magdeburg"  , new StadtLocation(11.633766, 52.130574)),  // Sachsen-Anhalt
            new StadtRecord("Mainz"      , new StadtLocation( 8.274037, 50.000000)),  // Rheinland-Pfalz
            new StadtRecord("München"    , new StadtLocation(11.581981, 48.135124)),  // Bayern
            new StadtRecord("Potsdam"    , new StadtLocation(13.060555, 52.398862)),  // Brandenburg
            new StadtRecord("Saarbrücken", new StadtLocation( 7.000000, 49.233334)), // Saarland
            new StadtRecord("Schwerin"   , new StadtLocation(11.401250, 53.635557)),  // Mecklenburg-Vorpommern
            new StadtRecord("Stuttgart"  , new StadtLocation( 9.181332, 48.775846)),  // Baden-Württemberg
            new StadtRecord("Wiesbaden"  , new StadtLocation( 8.240000, 50.080000))   // Hessen
    };

    /** UI-Element für Auswahl der ersten Stadt. */
    private Spinner _stadt1Spinner = null;

    /* UI-Element für Auswahl der ersten Stadt. */
    private Spinner _stadt2Spinner = null;

    /** UI-Element für Anzeige der berechneten Entfernung. */
    private TextView _ergebnisTextView = null;


    /**
     * Lifecycle-Methode zur Initialisierung des Activity-Objekts.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner_activity);

        _stadt1Spinner = findViewById(R.id.stadt1spinner);
        _stadt2Spinner = findViewById(R.id.stadt2spinner);

        _ergebnisTextView = findViewById(R.id.resultTextView);

        spinnerInitialisieren();
    }


    /**
     * Die beiden Spinner-Elemente mit einem {@code ArrayAdapter} mit Städte-Namen befüllen.
     */
    private void spinnerInitialisieren() {

        String[] stadtNamenArray = new String[ staedteArray.length ];
        for (int i = 0; i < staedteArray.length; i++) {

            stadtNamenArray[i] = staedteArray[i].stadtName();
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                                    android.R.layout.simple_spinner_item,
                                    stadtNamenArray );
        _stadt1Spinner.setAdapter( adapter );
        _stadt2Spinner.setAdapter( adapter );

        // Prompt-Text ("Bitte Stadt auswählen") wird nur angezeigt,
        // wenn Spinner-Element den spinnerMode="dialog" hat.
        _stadt1Spinner.setPromptId(R.string.spinner_default_eintrag);
        _stadt2Spinner.setPromptId(R.string.spinner_default_eintrag);

        _stadt1Spinner.setOnItemSelectedListener(this);
        _stadt2Spinner.setOnItemSelectedListener(this);
    }


    /**
     * Event-Handler-Methode für Spinner-Elemente, der ausgeführt wird, wenn ein Element
     * ausgewählt wurde. Es wird dann eine neue Entfernungsberechung vorgenommen soweit
     * zwei verschiedene Städte ausgewählt wurden.
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        int pos1 = _stadt1Spinner.getSelectedItemPosition();
        int pos2 = _stadt2Spinner.getSelectedItemPosition();

        if (pos1 == pos2) {

            _ergebnisTextView.setText(R.string.bitte_zwei_verschiedene_staedte);

        } else {

            StadtRecord stadt1 = staedteArray[pos1];
            StadtRecord stadt2 = staedteArray[pos2];

            Location location1 = stadt1.location();
            Location location2 = stadt2.location();

            int distanzInMetern = (int) location1.distanceTo(location2);
            int distanzInKilometer = distanzInMetern / 1000;

            String ergebnisStr = getString( R.string.entfernung_zwischen_staedten,
                                            stadt1.stadtName(),
                                            stadt2.stadtName(),
                                            distanzInKilometer );
            _ergebnisTextView.setText(ergebnisStr);
        }
    }


    /**
     * Event-Handler-Methode für Spinner-Elemente wenn kein Element ausgewählt ist.
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        Toast.makeText(this, "Nichts ausgewählt", Toast.LENGTH_SHORT).show();
    }

}
