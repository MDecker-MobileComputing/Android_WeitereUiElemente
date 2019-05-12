package de.mide.weitereuielemente;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;


/**
 * App zur Einführung einer Reihe weiterer UI-Elemente.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class MainActivity extends Activity
                          implements OnClickListener,
                                     RadioGroup.OnCheckedChangeListener,
                                     CompoundButton.OnCheckedChangeListener,
                                     SeekBar.OnSeekBarChangeListener {

    /** Referenz auf Schalter mit Status. */
    protected ToggleButton _toggleButton = null;

    /** Referenz auf "Fortschrittsanzeige". */
    protected ProgressBar  _progressBar  = null;


    /**
     * Lifecycle-Methode; setzt Event-Handler für die UI-Elemente.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);

        CheckBox checkbox1 = findViewById(R.id.checkbox_1);
        checkbox1.setOnCheckedChangeListener(this);

        CheckBox checkbox2 = findViewById(R.id.checkbox_2);
        checkbox2.setOnCheckedChangeListener(this);

        RadioGroup radioGroup = findViewById(R.id.radio_group_mit_drei_buttons);
        radioGroup.setOnCheckedChangeListener(this);

        _toggleButton = findViewById(R.id.toggleButton);
        _toggleButton.setOnClickListener(this);

        _progressBar = findViewById(R.id.progressBar);


        if ( _toggleButton.isChecked() ) {

            _progressBar.setVisibility( View.VISIBLE );

        } else {

            _progressBar.setVisibility( View.INVISIBLE );
        }
    }


    /**
     * Event-Handler-Methode für den ToggleButton.<br>
     * Einzige Methode aus dem Interface {@link android.view.View.OnClickListener}.
     *
     * @param view UI-Element, welches das Event ausgelöst hat.
     */
    @Override
    public void onClick(View view) {

        if ( _toggleButton.isChecked() ) {
            _progressBar.setVisibility(View.VISIBLE);
        } else {
            _progressBar.setVisibility(View.INVISIBLE);
        }
    }


    /**
     * Event-Handler-Methode für RadioGroup
     * (Überschreibung der einzigen Methode aus dem Interface
     * {@link RadioGroup.OnCheckedChangeListener}).
     * Gibt Text des gewählten RadioButtons in einem Toast aus.
     *
     * @param group      RadioGroup-Instanz, in der ein RadioButton das Event ausgelöst hat.
     *
     * @param checkedId  ID des UI-Elements (RadioButton), welches das Event ausgelöst hat.
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        RadioButton radioButton = findViewById(checkedId);

        String radioButtonText = radioButton.getText().toString();
        String toastText       = "RadioButton \"" + radioButtonText + "\" gewählt";

        Toast brot = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
        brot.show();
    }


    /**
     * Event-Handler-Methode für CheckBoxen.<br>
     * Einzige Methode aus dem Interface {@link CompoundButton.OnCheckedChangeListener}.
     *
     * @param buttonView CheckBox die Event ausgelöst hat.
     *
     * @param isChecked  Ist die CheckBox jetzt aktiviert oder nicht?
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        String checkboxText = buttonView.getText().toString();
        String toastText    = "Neuer Zustand CheckBox \"" + checkboxText +
                              "\": " + isChecked;

        Toast brot = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
        brot.show();
    }


    /**
     * Methode aus Interface {@link android.widget.SeekBar.OnSeekBarChangeListener}.
     * Absichtlich leer überschrieben.
     *
     * @param seekBar UI-Element, das Event ausgelöst hast.
     *
     * @param progress Neuer Wert
     *
     * @param fromUser <i>true</i> wenn die Änderung von einem Nutzer verursacht
     *                 wurde.
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

      // absichtlich leer gelassen
    }


    /**
     * Methode aus Interface {@link android.widget.SeekBar.OnSeekBarChangeListener}.
     * Absichtlich leer überschrieben.
     *
     * @param seekBar UI-Element, das Event ausgelöst hast.
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

        // absichtlich leer gelassen
    }


    /**
     * Methode aus Interface {@link android.widget.SeekBar.OnSeekBarChangeListener}.
     * Wird aufgerufen, wenn das Einstellen eines neuen Wertes beendet ist, der Finger
     * also vom SeekBar-Element wieder abhoben wurde.
     * Einzige Event-Handler-Methode für das SeekBar-Element, die wirklich überschrieben
     * wurde.
     *
     * @param seekBar UI-Element, das Event ausgelöst hast.
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        int neuerWert = seekBar.getProgress();

        Toast.makeText(
                this,
                "Neuer Wert eingestellt: " + neuerWert,
                Toast.LENGTH_SHORT).show();
    }

}
