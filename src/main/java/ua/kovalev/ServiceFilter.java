package ua.kovalev;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class ServiceFilter {
    public static List<String> getUrlList(HttpURLConnection httpURLConnection) {
        List<String> list = new ArrayList<>();
        String hrefVar = "href";
        int maxLentgh = 10485760;
        try (BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream())) {
            if (bis.available() > maxLentgh) {
                System.out.println("Слишком большой сайт");
                return list;
            }
            byte[] bytes = bis.readAllBytes();
            String site = new String(bytes);
            int indEnd = 0;
            while (true) {
                int ind = site.indexOf(hrefVar, indEnd);
                if (ind == -1) {
                    break;
                }
                int indStart = site.indexOf("\"", ind);
                if (indStart == -1) {
                    break;
                }
                indEnd = site.indexOf("\"", indStart + 1);
                if (indEnd == -1) {
                    break;
                }

                String link = site.substring(indStart, indEnd);
                list.add(link);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
