package com.example.a21650521.appwebservice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private class EjecutarWS extends AsyncTask<String, Void, Void> {
        private String resultado;
        TextView tv;

        @Override
        protected void onPreExecute() {
            tv = (TextView)findViewById(R.id.tvResultado);
        }

        @Override
        protected Void doInBackground(String... strings) {
            URL url = null;
            HttpURLConnection conn = null;
            BufferedReader br = null;
            try {
                url = new URL(strings[0]);
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuffer sb = new StringBuffer();
                String line = null;

                // Read Server Response
                while((line = br.readLine()) != null)
                {
                    sb.append(line + " ");
                }
                resultado = sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (br != null)
                    try {
                        br.close();
                    } catch (IOException e) {}

            }
            return null;
        }
        protected void onPostExecute(Void v) {
            tv.setText(resultado);
        }

    }
    public void consumirWS(View v) {
        String url = "https://pokeapi.co/api/v2/pokemon/";
        new EjecutarWS().execute(url);
    }
}
