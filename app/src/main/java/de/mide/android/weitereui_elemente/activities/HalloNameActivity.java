package de.mide.android.weitereui_elemente.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import de.mide.android.weitereui_elemente.R;


/**
 * Diese Activity bietet dieselbe Funktionalität wie die App "Hallo Name"
 * (<a href="https://github.com/MDecker-MobileComputing/Android_HalloName">siehe hier</a>),
 * aber mit {@code AutoCompleteTextView}.
 */
public class HalloNameActivity extends Activity implements View.OnClickListener  {

    /** UI-Elemente für Eingabe Vorname mit AutoCompletion. */
    private AutoCompleteTextView _autoCompleteTextView = null;

    /** Button für die Ausgabe des Namens in einem Dialog. */
    private Button _halloNameButton = null;


    /**
     * Lifecycle-Methode, initialisiert die Activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.halloname_activity);

        _halloNameButton = findViewById(R.id.halloNameButton);
        _halloNameButton.setOnClickListener(this);

        initAutoCompleteTextView();
    }


    /**
     * Initialisiert das AutoCompleteTextView-Elements.
     */
    private void initAutoCompleteTextView() {

        _autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        _autoCompleteTextView.setThreshold(2); // erst ab 2 Zeichen gibt es Vorschläge

        String[] vornamenArray = getResources().getStringArray(R.array.array_vornamen);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>( this,
                        android.R.layout.select_dialog_item,
                        vornamenArray );
        _autoCompleteTextView.setAdapter(adapter);

        _autoCompleteTextView.setHint(R.string.hint_vornamen);
    }


    /**
     * Event-Handler für den Button.
     *
     * @param view Button, der Event ausgelöst hat.
     */
    @Override
    public void onClick(View view) {

        String vorname = _autoCompleteTextView.getText().toString().trim();
        if ( vorname.isEmpty() ) {

            Toast.makeText(this, R.string.toast_vorname_leer, Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        String nachricht = getResources().getString(R.string.hallo_plus_Platzhalter, vorname);
        dialogBuilder.setMessage(nachricht);
        dialogBuilder.setPositiveButton(R.string.button_weiter, null);

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        keyboardEinklappen(_autoCompleteTextView);
        _autoCompleteTextView.setText("");
    }


    /**
     * Virtuelles Keyboard wieder "einklappen". Lösung nach
     * <a href="https://stackoverflow.com/a/17789187/1364368">dieser Antwort</a>
     * auf <i>stackoverflow.com</i>.
     *
     * @param view  UI-Element, von dem Keyboard eingeblendet wurde.
     */
    private void keyboardEinklappen(View view) {

        InputMethodManager imm = (InputMethodManager)
                    getSystemService(Activity.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}