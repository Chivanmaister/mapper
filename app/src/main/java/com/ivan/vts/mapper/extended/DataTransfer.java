package com.ivan.vts.mapper.extended;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Chiefster on 10/4/2017.
 */

public class DataTransfer {

//    private String dataString;
//    private URLConnection connection;
//    private String url;
//
//    public DataTransfer(String url) {
//        this.url = url;
//    }
//
//    public String getResponse() {
//
//        BufferedInputStream stream;
//        try {
//            URL httpUrl = new URL(url);
//            connection = httpUrl.openConnection();
//            stream = new BufferedInputStream(connection.getInputStream());
//            dataString = IOUtils.toString(stream, "UTF-8");
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return dataString;
//    }


}
