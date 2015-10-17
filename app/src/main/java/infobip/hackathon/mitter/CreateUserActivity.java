package infobip.hackathon.mitter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "title_activity_create_user", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); private EditText ;

            }
        });

            String name,lastName,password,email;
        EditText inputName, inputLastname,inputEmail,inputPassword;

            inputName = (EditText) findViewById(R.id.user);
            inputLastname =(EditText) findViewById(R.id.user);
            inputEmail =(EditText) findViewById(R.id.user);
            inputPassword =(EditText) findViewById(R.id.user);
            name =  inputName.getText().toString().trim();
            lastName = inputLastname.getText().toString().trim();
            email = inputEmail.getText().toString().trim();
            password = inputPassword.getText().toString().trim();
        if(isNameValid(name) && isNameValid(lastName) && checkEmail(email)){
           registerUser(name,lastName,email,password);
        }

            }
/*

        JSONObject createUser(){
            User makeUser = new User;
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
return newUser;
        }*/

    private boolean checkEmail(String email) {
        {
            Pattern pattern;
            Matcher matcher;
            final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }

    public boolean isNameValid(String name){
        String regx = "[a-zA-Z]+\\.?";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);

    }
