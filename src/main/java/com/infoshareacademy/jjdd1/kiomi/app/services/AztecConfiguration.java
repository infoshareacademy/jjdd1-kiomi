package com.infoshareacademy.jjdd1.kiomi.app.services;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arek50 on 2017-05-05.
 */
public class AztecConfiguration {
    private static final String csvFile = "AztecMap.csv";

    public Map<String, String> getMapFromCsv() throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    this.getClass().getResourceAsStream("/" + csvFile)));
            String delimiter = ":";
            Map<String, String> resultMap = new HashMap<>();

            bufferedReader.lines().filter(line -> line.contains(delimiter))
                    .filter(line -> !line.substring(line.length() - 1).equals(delimiter))
                    .filter(line -> !line.substring(line.length() - 2).equals("\"\""))
                    .forEach(line -> resultMap.putIfAbsent(line.split(delimiter)[0].trim(), line.split(delimiter)[1].trim()));
            return resultMap;
        }catch (NullPointerException e) {
            return new HashMap<>();
        }
    }

    public BufferedReader replaceFromMap(BufferedReader stream) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = stream.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] finalStream = new String[]{sb.toString()};
        getMapFromCsv().forEach((String key, String value) -> {if(value!=null) {
            finalStream[0] = finalStream[0].replaceFirst(value, key);
        }});

        InputStream is = new ByteArrayInputStream(finalStream[0].getBytes());
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        return br;
    }
//public String replaceFromMap(String testy) throws IOException {
//        String[] test= new String[]{testy};
//        getMapFromCsv().forEach((String key, String value) -> {if(value!=null) {
//            test[0] = test[0].toString().replaceFirst(value, key);
//        }});
//        return test[0].toString();
//    }


}
