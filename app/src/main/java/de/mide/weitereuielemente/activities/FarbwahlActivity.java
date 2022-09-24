package de.mide.weitereuielemente.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import de.mide.weitereuielemente.R;


/**
 * Activity zur Auswahl einer Farbe durch Auswahl von Rot-, Grün- und Blau-Anteil (RGB).
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class FarbwahlActivity extends Activity
                              implements OnSeekBarChangeListener {

    /** Alpha-Wert (Transparenz) für Farbe; 0xff=255 entspricht voller Deckkraft. */
    private static final int ALPHA_WERT = 0xff;

    /** Schieberegler für Einstellung Rotanteil. */
    private SeekBar _rotSeekBar = null;

    /** Schieberegler für Einstellung Grünanteil. */
    private SeekBar _gruenSeekBar = null;

    /** Schieberegler für Einstellung Blau-Anteil. */
    private SeekBar _blauSeekBar = null;

    /** Textfeld zur Darstellung der aktuell ausgewählten Farbe. */
    private TextView _farbTextView = null;

    /**
     * Wenn diese CheckBox ausgewählt ist, dann wird die Farbe aktualisiert, auch
     * wenn die Touch-Geste zum verschieben des Schiebereglers noch nicht beendet
     * ist.
     */
    private CheckBox _farbeAktualisierenCheckbox = null;


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

        _farbeAktualisierenCheckbox = findViewById(R.id.farbeLiveAktualisierenCheckbox);

        _rotSeekBar.setOnSeekBarChangeListener(   this );
        _gruenSeekBar.setOnSeekBarChangeListener( this );
        _blauSeekBar.setOnSeekBarChangeListener(  this );

        farbeDarstellen();
    }


    /**
     * Methode stellt aktuell ausgewählten Farbwert dar: Das TextView-Element bekommt die
     * entsprechende Hintergrundfarbe, und der Farbcode (Hex-Zahl) im TextView-Element wird
     * entsprechend dargestellt.
     */
    private void farbeDarstellen() {

        int rot   = _rotSeekBar.getProgress();
        int gruen = _gruenSeekBar.getProgress();
        int blau  = _blauSeekBar.getProgress();

        // Formel nach https://developer.android.com/reference/android/graphics/Color
        int farbe = (ALPHA_WERT & 0xff) << 24 |
                           (rot & 0xff) << 16 |
                          (gruen & 0xff) << 8 |
                          (blau & 0xff);

        _farbTextView.setBackgroundColor(farbe);

        String hexString = String.format("0x%08X", farbe);
        _farbTextView.setText(hexString);
    }


    /**
     * Eine der drei Methoden aus dem Interface {@link OnSeekBarChangeListener}; sie wird
     * bei jeder Änderung des Schieberegler-Wertes während der Touch-Geste aufgerufen.
     * Wenn es sich um ein vom Nutzer ausgelöstes Event handelt und die Live-Aktualisierung
     * in der CheckBox eingeschaltet ist, dann wird die Farbe aktualisiert.
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

        if (fromUser == false) { return; }

        if ( _farbeAktualisierenCheckbox.isChecked() == true ) {

            farbeDarstellen();
        }
    }


    /**
     * Eine der drei Methoden aus dem Interface {@link OnSeekBarChangeListener}; wird aufgerufen,
     * wenn die Touch-Geste zur Änderung des Werts eines SeekBar-Elements beginnt.
     *
     * @param seekBar  SeekBar-Element, welches das Eventa ausgelöst hat.
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

        // absichtlich leer gelassen
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
