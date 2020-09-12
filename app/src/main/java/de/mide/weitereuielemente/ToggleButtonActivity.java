package de.mide.weitereuielemente;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * Activity zur Demonstration des UI-Elements "ToggleButton" (zustandsbehafter Button,
 * "Kippschalter").
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class ToggleButtonActivity extends Activity
                                  implements OnCheckedChangeListener {

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

        _toggleButton.setOnCheckedChangeListener(this);

        if ( _toggleButton.isChecked() ) {

            _progressBar.setVisibility( View.VISIBLE );

        } else {

            _progressBar.setVisibility( View.INVISIBLE );
        }
    }


    /**
     * Einzige Methode aus dem Event-Handler-Interface {@code OnCheckedChangeListener}.
     *
     * @param buttonView  Button, dessen Zustand geändert wurde.
     *
     * @param isChecked  {@code true} genau dann wenn {@code buttonView} nach der
     *                   Zustandsänderung eingeschaltet ist.
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if ( isChecked ) {

            _progressBar.setVisibility(View.VISIBLE);

        } else {

            _progressBar.setVisibility(View.INVISIBLE);
        }
    }

}
