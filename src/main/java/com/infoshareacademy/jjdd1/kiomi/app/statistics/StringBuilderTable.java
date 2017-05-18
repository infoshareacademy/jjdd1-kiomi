package com.infoshareacademy.jjdd1.kiomi.app.statistics;

import java.math.BigInteger;
import java.util.Map;

public class StringBuilderTable {

    public String createHtmlTop(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(
                "<html>" +
                "<body>"
        );
        String htmlTop = stringBuilder.toString();
        return htmlTop;
    }

    public String createHtmlBottom(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(
                "</body>" +
                "</html>");
        String htmlBottom = stringBuilder.toString();
        return htmlBottom;
    }

    public String createTableContent(Map<String, BigInteger> map,String nameOfColumn1 ){

        StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append(
                "</br><table border='1'>" +
                "<tr>" +
                "<th>"+nameOfColumn1+"</th>" +
                "<th>Total count</th>" +
                "</tr>");

        for (Map.Entry<String, BigInteger> entry : map.entrySet()) {
            String key = entry.getKey();
            BigInteger value = entry.getValue();
            System.out.println("--> " + key + " " + value);
            stringBuilder.append("<tr><td>")
               .append(key)
               .append("</td><td>")
               .append(value)
               .append("</td></tr>");
        }

        stringBuilder.append("</table></br></br>"+"</br>"
              );

        String htmlContent = stringBuilder.toString();

        return htmlContent;
    }
}
