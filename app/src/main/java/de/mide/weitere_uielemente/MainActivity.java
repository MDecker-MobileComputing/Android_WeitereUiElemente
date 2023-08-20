package de.mide.weitere_uielemente;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import de.mide.weitere_uielemente.activities.FarbwahlActivity;
import de.mide.weitere_uielemente.activities.MultiChoiceActivity;
import de.mide.weitere_uielemente.activities.SingleChoiceActivity;
import de.mide.weitere_uielemente.activities.ToggleButtonActivity;


/**
 * Activity mit Hauptmenü zum Absprung zu anderen Activities.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class MainActivity extends Activity
                          implements OnClickListener {

    /** Button für Navigation zu ToggleButtonActivity. */
    private Button _geheZuToggleActivityButton = null;

    /** Button für Navigation zu SingleChoiceActivity (Demo RadioButtons). */
    private Button _geheZuSingleChoiceActivityButton = null;

    /** Button für Navigation zu MultiChoiceActivity (Demo Checkboxes). */
    private Button _geheZuMultipleChoiceActivityButton = null;

    /** Button für Navigation zur FarbwahlActivity. */
    private Button _geheZuFarbwahlButton = null;

    /** Flag, damit Toast mit Hinweis zur Rücknavigation nur einmal pro App-Lauf angezeigt wird. */
    private boolean _hinweisWurdeGezeigt = false;


    /**
     * Lifecycle-Methode zur Initialisierung des Activity-Objekts.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _geheZuToggleActivityButton         = findViewById(R.id.gehe_zu_toggle_activity_button);
        _geheZuSingleChoiceActivityButton   = findViewById(R.id.gehe_zu_single_choice_button  );
        _geheZuMultipleChoiceActivityButton = findViewById(R.id.gehe_zu_multi_choice_button   );
        _geheZuFarbwahlButton               = findViewById(R.id.gehe_zu_farbwahl_button       );

        _geheZuToggleActivityButton.setOnClickListener(         this );
        _geheZuSingleChoiceActivityButton.setOnClickListener(   this );
        _geheZuMultipleChoiceActivityButton.setOnClickListener( this );
        _geheZuFarbwahlButton.setOnClickListener(               this );
    }


    /**
     * Event-Handler für Button, springt zu einer anderen Activity.
     * Beim ersten Aufruf pro App-Start wird in einem Toast ein Hinweis 
     * zur Rücknavigation angezeigt.
     *
     * @param view  Button, der das Event ausgelöst hat
     */
    @Override
    public void onClick(View view) {

        Intent intent = null;

        if (view == _geheZuToggleActivityButton) {

            intent = new Intent(this, ToggleButtonActivity.class);
            startActivity(intent);
 
        } else if (view == _geheZuSingleChoiceActivityButton) {
        
            intent = new Intent(this, SingleChoiceActivity.class);
            startActivity(intent);

        } else if (view == _geheZuMultipleChoiceActivityButton) {
        
            intent = new Intent(this, MultiChoiceActivity.class);
            startActivity(intent);

        } else if (view == _geheZuFarbwahlButton) {
        
            intent = new Intent(this, FarbwahlActivity.class);
            startActivity(intent);

        } else {

            Toast.makeText(this, 
                           R.string.hinweis_unerwarteter_button, 
                           Toast.LENGTH_LONG
                          ).show();
            return;
        }


        if (_hinweisWurdeGezeigt == false) {

            Toast.makeText(this, 
                           R.string.hinweis_backbutton, 
                           Toast.LENGTH_SHORT
                          ).show();
            _hinweisWurdeGezeigt  = true;
        }
    }

}
