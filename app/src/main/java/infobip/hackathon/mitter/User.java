/**
 * Created by emir on 10/16/2015.
 */

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class User {
    //BEGIN atributi
    private int ID;
    private String name, surname, password, email;
    private List<User> prijatelji = new ArrayList<User>();

    //END atributi

    //BEGIN Geteri i setteri
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getSurname() {
        return surname;
    }
    public List<User> getPrijatelji() {
        return prijatelji;
    }
    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    //END Gettera i settera

    //BEGIN Metode
    public String registerUser(String ime, String prezime, String email, String password){

        //poziv Ohrijevog servisa koji spasava podatke (parametri iz konstruktora) u bazu...
        //Hashiranje passworda se vrsi na servisu kod Ohrija

        String apiRegServis = "http://duckapi.eestec-sa.ba/api.php?api=register&ime="+ime+"&prezime="+prezime+"&email="+email+"&password="+password;
        String rez="";

        try {
            rez = httpGet(apiRegServis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(rez.equals("0")){
            return "Ne postoji korisnik u bazi";
        }
        else if(rez.equals("-1")) {
            return "Korisnik ve? postoji u bazi!";
        }

        return "Ok";
    }

    public User isLogged(String email, String password){

        //poziv Ohrijevog servisa za provjeru... saljem varijable email i password
        //JSON formatirani podaci o korisniku koji su vraceni iz Ohrijevog servisa, moze biti u null

        String apiLoginServis = "http://duckapi.eestec-sa.ba/interstellar/api.php?api=login&email="+email+"&pass="+password;

        String rez="";

        try {
            rez = httpGet(apiLoginServis);
            if (rez.equals("null")) rez=null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(rez!=null) {
            try {
                JSONObject json_user = new JSONObject(rez);
                setName(json_user.getString("ime"));
                setSurname(json_user.getString("prezime"));
                setEmail(json_user.getString("email"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }
        return null;
    }

    public String addFriend(User user){
        //posaljem this.user i user na servis, gdje se popunjava tabela koja sadrzi prijatelje za this.user-a

        String apiDodajPrijateljaServis = "http://duckapi.eestec-sa.ba/interstellar/api.php?api=add&ko="+this.getName()+"&koga="+user.getName();
        String rez="";
        try {
            rez = httpGet(apiDodajPrijateljaServis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(rez.equals("1")){
            getPrijatelji().add(user);
            return "Prijatelj uspješno dodan u kontakte";
        }
        else {
            return "Neuspjelo dodavanje";
        }
    }

    public ArrayList<User> uzmiPrijatelje(){
        ArrayList<User> mojiPrijatelji = new ArrayList<User>();
        String apiUzmiPrijateljaServis = "http://duckapi.eestec-sa.ba/interstellar/api.php?api=friends&me="+this.getID();

        String rez="";
        try {
            rez = httpGet(apiUzmiPrijateljaServis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!rez.equals("0") && !rez.equals("")){
            try {
                JSONObject obj = new JSONObject(rez);
                JSONArray prijatelji = obj.getJSONArray("friends");
                for (int i = 0; i<prijatelji.length(); i++){

                    int ID = Integer.parseInt(prijatelji.getJSONObject(i).getString("id"));
                    String ime = prijatelji.getJSONObject(i).getString("ime");
                    String prezime = prijatelji.getJSONObject(i).getString("prezime");
                    String email = prijatelji.getJSONObject(i).getString("email");

                    User prijatelj = new User();
                    prijatelj.setName(ime); prijatelj.setSurname(prezime); prijatelj.setEmail(email); prijatelj.setID(ID);

                    mojiPrijatelji.add(prijatelj);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mojiPrijatelji;
    }
    //END Metode


    //BEGIN Helper Metode
    public static String httpGet(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        if (conn.getResponseCode() != 200) {
            throw new IOException(conn.getResponseMessage());
        }

        // Buffer the result into a string
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();

        conn.disconnect();
        return sb.toString();
    }
    //END Helper Metode
}
