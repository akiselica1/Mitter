package infobip.hackathon.mitter;

import android.view.inputmethod.EditorInfo;
import android.widget.EditText;





/**
 * Created by Mustafa Pc on 16.10.2015..
 */
public class LocationActivity {
    private EditText;
    Location location= new Location();

    location.name=(EditText)  findViewById(R.id.search);
    location.
    locationInput.setOnEditorActionListener(new OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendMessage();
                handled = true;
            }
            return handled;
        }

    });
    Location location=new location;
    System.out.print("Enter location name");
    location.name=locationScanner.next();
}
