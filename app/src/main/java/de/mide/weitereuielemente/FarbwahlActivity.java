package de.mide.weitereuielemente;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


/**
 * Activity zur Auswahl einer Farbe durch Auswahl von Rot-, Grün- und Blau-Anteil (RGB).
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class FarbwahlActivity extends Activity
                              implements OnSeekBarChangeListener {

    /** Schieberegler für Einstellung Rotanteil. */
    private SeekBar _rotSeekBar = null;

    /** Schieberegler für Einstellung Grünanteil. */
    private SeekBar _gruenSeekBar = null;

    /** Schieberegler für Einstellung Blau-Anteil. */
    private SeekBar _blauSeekBar = null;

    /** Textfeld zur Darstellung der aktuell ausgewählten Farbe. */
    private TextView _farbTextView = null;


    /**
     * Lifecycle-Methode zur Initialisierung des Activity-Objekts.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.farbwahl_activity);

        setTitle( R.string.title_farbwahl );

        _rotSeekBar   = findViewById(R.id.rotSeekBar  );
        _gruenSeekBar = findViewById(R.id.gruenSeekBar);
        _blauSeekBar  = findViewById(R.id.blueSeekBar );

        _farbTextView = findViewById(R.id.farbTextView);

        _rotSeekBar.setOnSeekBarChangeListener(   this );
        _gruenSeekBar.setOnSeekBarChangeListener( this );
        _blauSeekBar.setOnSeekBarChangeListener(  this );

        farbeDarstellen();
    }

    
    /**
     * Methode stellt aktuell ausgewählten Farbwert dar.
     */
    private void farbeDarstellen() {

        int rot   = _rotSeekBar.getProgress();
        int gruen = _gruenSeekBar.getProgress();
        int blau  = _blauSeekBar.getProgress();

        _farbTextView.setBackgroundColor(0x80FFFF00);
    }


    /**
     * Eine der drei Methoden aus dem Interface {@link OnSeekBarChangeListener}.
     *
     * @param seekBar  SeekBar-Element, welches das Eventa ausgelöst hat.
     *
     * @param progress  Aktuell ausgewählte Wert.
     *
     * @param fromUser  {@code true} nur dann, wenn die Änderung durch einen Nutzer vorgenommen
     *                  wurde.
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }


    /**
     * Eine der drei Methoden aus dem Interface {@link OnSeekBarChangeListener}; wird aufgerufen,
     * wenn die Touch-Geste zur Änderung des Werts eines SeekBar-Elements beginnt.
     *
     * @param seekBar  SeekBar-Element, welches das Eventa ausgelöst hat.
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }


    /**
     * Eine der drei Methoden aus dem Interface {@link OnSeekBarChangeListener}; wird aufgerufen,
     * wenn die Touch-Geste zur Änderung des Werts eines SeekBar-Element endet.
     *
     * @param seekBar  SeekBar-Element, welches das Eventa ausgelöst hat.
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        farbeDarstellen();
    }

}
