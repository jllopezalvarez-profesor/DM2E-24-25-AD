package es.jllopezalvarez.accesodatos.ejemplos.ejemplossax.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WeatherHandler extends DefaultHandler {

    boolean enCity = false;
    boolean enTemperature = false;
    boolean enHumidity = false;
    boolean enPressure = false;
    String cityId;
    String cityName;
    String stringHumidity = "";


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName.toLowerCase()) {
            case "city":
                enCity = true;
                cityId = attributes.getValue("id");
                cityName = attributes.getValue("name");

                break;
            case "temperature":
                enTemperature = true;
                break;
            case "humidity":
                stringHumidity= "";
                enHumidity = true;
                break;
            case "pressure":
                enPressure = true;
//                System.out.printf("Inicio de: %s \n %s\n\n", uri, qName);

                break;
        }


    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName.toLowerCase()) {
            case "city":
                enCity = false;
                System.out.println("Ciudad finalizada");
                System.out.println("Nombre: " + cityName);
                System.out.println("Id: " + cityId);
                System.out.println();
                System.out.println();
                break;
            case "temperature":
                enTemperature = false;
                break;
            case "humidity":
                int valorHumidity = Integer.parseInt(stringHumidity);
                enHumidity = false;
                break;
            case "pressure":
                enPressure = false;
//                System.out.printf("Inicio de: %s \n %s\n\n", uri, qName);

                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (enHumidity)
            stringHumidity += new String(ch, start, length);
    }
}
