package ua.kovalev;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.sql-ex.ru/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if (httpURLConnection.getResponseCode() != 200) {
                return;
            }
            List<String> list = ServiceFilter.getUrlList(httpURLConnection);
            System.out.println("list = " + list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
