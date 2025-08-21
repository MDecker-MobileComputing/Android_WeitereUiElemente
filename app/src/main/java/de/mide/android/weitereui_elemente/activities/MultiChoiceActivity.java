package de.mide.android.weitereui_elemente.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import de.mide.android.weitereui_elemente.R;


/**
 * Activity zur Demonstration von CheckBoxen mit Multiple-Choice-Fragen mit Checkboxen.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class MultiChoiceActivity extends Activity
                                 implements OnCheckedChangeListener, OnClickListener {

    /** Button zum Überprüfen, ob die gerade gewählten Checkboxen alle richtig & vollständig sind. */
    private Button _pruefButton = null;

    private CheckBox _checkBox1 = null;
    private CheckBox _checkBox2 = null;
    private CheckBox _checkBox3 = null;
    private CheckBox _checkBox4 = null;
    private CheckBox _checkBox5 = null;

    /**
     * Lifecycle-Methode zur Initialisierung des Activity-Objekts.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_choice_activity);

        setTitle(R.string.title_multi_choice);

        _checkBox1 = findViewById(R.id.antwort1aCheckBox);
        _checkBox2 = findViewById(R.id.antwort1bCheckBox);
        _checkBox3 = findViewById(R.id.antwort1cCheckBox);
        _checkBox4 = findViewById(R.id.antwort1dCheckBox);
        _checkBox5 = findViewById(R.id.antwort1eCheckBox);

        _pruefButton = findViewById(R.id.musikAntwortenPruefButton);


        _checkBox1.setOnCheckedChangeListener(this);
        _checkBox2.setOnCheckedChangeListener(this);
        _checkBox3.setOnCheckedChangeListener(this);
        _checkBox4.setOnCheckedChangeListener(this);
        _checkBox5.setOnCheckedChangeListener(this);

        _pruefButton.setOnClickListener(this);
    }


    /**
     * Event-Handler-Methode für Zustandsänderungen der Checkboxen. Wenn eine falsche
     * Antwort-Option ausgewählt wurde, dann wird ein Toast mit einem Hinweis angezeigt.
     *
     * @param buttonView   Checkbox, der Zustand (ausgewählt oder nicht) geändert wurde.
     *
     * @param isChecked  {@code true} wenn {@code buttonView} jetzt ausgewählt ist, sonst
     *                   {@code false}.
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        // Wurde der Zustand einer der CheckBoxen für eine falsche Antwort-Option geändert?
        if (buttonView == _checkBox1 || buttonView == _checkBox2 || buttonView == _checkBox5) {

            if (isChecked == true) {

                Toast.makeText(this, R.string.toast_nochmal_nachdenken, Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * Event-Handler-Methode für Button; überprüft, ob die richtigen Antworten vollständig
     * ausgewählt sind.
     *
     * @param view  Button, der Event ausgelöst hat, also betätigt wurde.
     */
    @Override
    public void onClick(View view) {

        if ( _checkBox1.isChecked() == false &&
             _checkBox2.isChecked() == false &&
             _checkBox3.isChecked() == true  &&
             _checkBox4.isChecked() == true  &&
             _checkBox5.isChecked() == false
           ) {

            Toast.makeText(this, R.string.toast_richtige_auswahl, Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(this, R.string.toast_falsche_auswahl, Toast.LENGTH_LONG).show();
        }
    }

}
