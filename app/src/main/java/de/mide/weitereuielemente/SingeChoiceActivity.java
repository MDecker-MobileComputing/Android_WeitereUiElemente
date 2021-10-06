package de.mide.weitereuielemente;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;


/**
 * Activity zur Demonstration von RadioButtons mit SingleChoice-Fragen.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class SingeChoiceActivity extends Activity
                                 implements OnCheckedChangeListener,
                                            OnClickListener {

    /** RadioGroup mit Antwortmöglichkeiten für erste Frage (welche Stadt am weitesten nördlich?). */
    private RadioGroup _frage1RadioGroup = null;

    /** RadioGroup mit Antwortmöglichkeiten für erste Frage (Hauptstadt von Kanada?). */
    private RadioGroup _frage2RadioGroup = null;

    /** Button, der betätigt wird, wenn alle eingegebenen Antworten überprüft werden sollen. */
    private Button _antwortenPruefenButton = null;


    /**
     * Lifecycle-Methode zur Initialisierung des Activity-Objekts.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_choice_activity);

        setTitle(R.string.title_single_choice);

        _frage1RadioGroup = findViewById(R.id.geoFrage1RadioGroup);
        _frage2RadioGroup = findViewById(R.id.geoFrage2RadioGroup);

        _antwortenPruefenButton = findViewById(R.id.geoAntwortenPruefButton);

        _frage1RadioGroup.setOnCheckedChangeListener(this);
        _frage2RadioGroup.setOnCheckedChangeListener(this);

        _antwortenPruefenButton.setOnClickListener(this);
    }


    /**
     * Einzige Methode aus Interface {@link RadioGroup.OnCheckedChangeListener} überschreiben.
     * Wenn in einer RadioGroup die falsche Antwort-Option gewählt wurde, dann wird ein Toast
     * angezeigt.
     *
     * @param group  RadioGroup, die das Event ausgelöst hat (in der also gerade ein anderer
     *               RadioButton ausgewählt wurde).
     *
     * @param checkedId ID des RadioButtons, der jetzt ausgewählt ist.
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (group == _frage1RadioGroup) {

            if (checkedId != R.id.antwort1bRadioButton) {

                Toast.makeText(this, R.string.toast_nochmal_nachdenken, Toast.LENGTH_SHORT).show();
            }

        } else if (group == _frage2RadioGroup) {

            if (checkedId != R.id.antwort2bRadioButton) {

                Toast.makeText(this, R.string.toast_nochmal_nachdenken, Toast.LENGTH_SHORT).show();
            }

        } else {

            Toast.makeText( this, R.string.toast_unerwartete_radiogroup,
                    Toast.LENGTH_LONG).show();
        }
    }

    
    /**
     * Event-Handler-Methode für Button. Es gibt nur einen Button auf der zugehörigen Activity,
     * nämlich den zum Überprüfen der eingegebenen Ergebnisse.
     *
     * @param view   Button, der Event ausgelöst hat.
     */
    @Override
    public void onClick(View view) {

        // IDs der jeweils ausgewählten RadioButtons abfragen
        int checkedId1 = _frage1RadioGroup.getCheckedRadioButtonId();
        int checkedId2 = _frage2RadioGroup.getCheckedRadioButtonId();

        // Wenn in einer RadioGroup kein RadioButton ausgewählt ist, dann wird -1 für die ID des
        // gewählten RadioButton zurückgegeben.
        if (checkedId1 == -1 || checkedId2 == -1) {

            Toast.makeText( this, R.string.toast_fehlende_antwort, Toast.LENGTH_LONG).show();
            return;
        }

        if (checkedId1 == R.id.antwort1bRadioButton && checkedId2 == R.id.antwort2bRadioButton) {

            Toast.makeText( this, R.string.toast_alles_richtig, Toast.LENGTH_LONG)
                 .show();

        } else {

            Toast.makeText( this, R.string.toast_nicht_richtig, Toast.LENGTH_LONG).show();
        }
    }

}
