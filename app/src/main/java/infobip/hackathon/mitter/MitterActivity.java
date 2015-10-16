package infobip.hackathon.mitter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.regex.Matcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;




public class MitterActivity extends AppCompatActivity {
    private EditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Login", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                private EditText ;
                User makeUser=new User;
                System.out.print("Name:");
                makeUser.name=(EditText) ;
                System.out.print("Last name:");
                makeUser.surrname=(EditText) findViewById(R.id.surrname);
                System.out.print("Passwod:");
                makeUser.password=(EditText) findViewById(R.id.search);
                System.out.print("Email:");
                makeUser.email=(EditText) findViewById(R.id.search);
                JSONObject newUser= new JSONObject();
                try {
                    newUser.put("id", makeUser.id);
                    newUser.put("name", makeUser.name);
                    newUser.put("surrname", makeUser.surrname);
                    newUser.put("email", makeUser.email);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                @SuppressWarnings("deprecation")
                /*HttpClient httpclient = new DefaultHttpClient();
                //  make POST request to the given URL
                String url=String.format("http://...", IPaddress);
                System.out.println(url);
                HttpPost httpPost = new HttpPost(url);
                // convert JSONObject to JSON to String
                newUser = object.toString();
                //  set json to StringEntity
                StringEntity se = new StringEntity(json);
                //  set httpPost Entity
                httpPost.setEntity(se);
                //  set some headers to inform server about the type of the content
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                //  Execute POST request to the given URL
                HttpResponse httpResponse = httpclient.execute(httpPost);
                //  receive response as inputStream
                inputStream = httpResponse.getEntity().getContent();

                //  convert inputstream to string
                if(inputStream != null)
                {
                    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
                    String line = "";
                    result = "";
                    while((line = bufferedReader.readLine()) != null)
                        result += line;

                    inputStream.close();

                }
                else
                    result = "Did not work!";


                // return result
                System.out.println(result);
*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mitter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public boolean isNameValid(String name){
        String regx = "[a-zA-Z]+\\.?";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
}
