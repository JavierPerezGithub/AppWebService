package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 21650521 on 17/01/2018.
 */

public class Pokemon {
    private String url;
    private String nombre;

    public String getUrl() {
        return url;
    }

    public String getNombre() {
        return nombre;
    }

    public Pokemon(JSONObject objetoJSON) {
        try {
            url = objetoJSON.getString("url");
            nombre = objetoJSON.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String toString(){
        return " url  : " + url + "\n name : " + nombre
                + "\n--------------------------------------------------------------------------\n";
    }
}
