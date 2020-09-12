package de.mide.weitereuielemente;

import android.app.Activity;
import android.os.Bundle;
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
 * Activity zur Demonstration des UI-Elements "ToggleButton" (zustandsbehafter Button,
 * "Kippschalter").
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class ToggleButtonActivity extends Activity
                                  implements OnClickListener {

    /** UI-Element "Kippschalter". */
    protected ToggleButton _toggleButton = null;

    /** UI-Element "Fortschrittsanzeige". */
    protected ProgressBar  _progressBar  = null;


    /**
     * Lifecycle-Methode zur Initialisierung des Activity-Objekts.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.toggle_button_activity);

        setTitle( R.string.title_togglebutton_demo );

        _toggleButton = findViewById(R.id.meinToggleButton);
        _progressBar  = findViewById(R.id.meinProgressBar);

        _toggleButton.setOnClickListener(this);

        if ( _toggleButton.isChecked() ) {

            _progressBar.setVisibility( View.VISIBLE );

        } else {

            _progressBar.setVisibility( View.INVISIBLE );
        }
    }


    /**
     * Event-Handler-Methode für den ToggleButton.<br>
     * Einzige Methode aus dem Interface {@link OnClickListener}.
     *
     * @param view  UI-Element, welches das Event ausgelöst hat (im vorliegenden
     *              Fall wird dies immer der ToggleButton sein).
     */
    @Override
    public void onClick(View view) {

        if ( _toggleButton.isChecked() ) {

            _progressBar.setVisibility(View.VISIBLE);

        } else {

            _progressBar.setVisibility(View.INVISIBLE);
        }
    }

}
