package com.infoshareacademy.jjdd1.kiomi.app.statistics;

import java.math.BigInteger;
import java.util.Map;

public class StringBuilderTable {

    public String createHtmlTop(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(
                "<head>\n" +
                        "    <title>Title</title>\n" +
                        "    <style type=\"text/css\">\n" +
                        "        div {\n" +
                        "            display: inline-block;\n" +
                        "            float:left;\n" +
                        "            margin-right:5px;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>"+
                "<html>" +
                "<body>"+
                "This mail was sent by <b>'Kiomi Autoparts'</b>. Here is daily statistics report:<br><br>"
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
                "<div>"+
                "<table border='1'>" +
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

        stringBuilder.append("</table></div>");

        String htmlContent = stringBuilder.toString();

        return htmlContent;
    }
}
