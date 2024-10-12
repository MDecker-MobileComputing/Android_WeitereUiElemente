package de.mide.weitere_uielemente.activities;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import de.mide.weitere_uielemente.R;

/**
 * Activity zur Demonstration des UI-Elements "Spinner" (Dropdown-Liste zur Auswahl
 * von Einträgen): es wird die Entfernung zwischen zwei Städten berechnet.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class SpinnerActivity extends Activity
                             implements AdapterView.OnItemSelectedListener {

    /** Für die Record-Klasse musste in app/build.gradle die Java-Version auf 17 hochgedreht werden. */
    public record StadtRecord( String nameStadt, double longitude, double latitude) { }

    private StadtRecord[] staedteArray = {
            new StadtRecord("Berlin"    , 13.404954, 52.520008),
            new StadtRecord("Dortmund"  ,  7.468554, 51.513992),
            new StadtRecord("Düsseldorf",  6.782014, 51.227741),
            new StadtRecord("Essen"     ,  7.011581, 51.455643),
            new StadtRecord("Frankfurt" ,  8.682127, 50.110924),
            new StadtRecord("Hamburg"   ,  9.993682, 53.551086),
            new StadtRecord("Köln"      ,  6.953101, 50.937531),
            new StadtRecord("Leipzig"   , 12.387772, 51.340632),
            new StadtRecord("München"   , 11.581981, 48.135124),
            new StadtRecord("Stuttgart" ,  9.181332, 48.775846)
    };

    private Spinner _stadt1Spinner = null;
    private Spinner _stadt2Spinner = null;

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

            stadtNamenArray[i] = staedteArray[i].nameStadt();
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

            double lat1 = stadt1.latitude();
            double lat2 = stadt2.latitude();

            double lon1 = stadt1.longitude();
            double lon2 = stadt2.longitude();

            Location location1 = new Location("Dummy-Provider");
            Location location2 = new Location("Dummy-Provider");

            location1.setLatitude(lat1);
            location1.setLongitude(lon1);

            location2.setLatitude(lat2);
            location2.setLongitude(lon2);

            int distanzInMetern = (int) location1.distanceTo(location2);
            int distanzInKilometer = distanzInMetern / 1000;

            String ergebnisStr = getString( R.string.entfernung_zwischen_staedten,
                                            stadt1.nameStadt(),
                                            stadt2.nameStadt(),
                                            distanzInKilometer );
            _ergebnisTextView.setText(ergebnisStr);
        }
    }


    /**
     * Event-Handler-Methode für Spinner-Elemente:
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        Toast.makeText(this, "Nix ausgewählt", Toast.LENGTH_SHORT).show();
    }

}
