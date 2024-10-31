package es.jllopezalvarez.accesodatos.ejemplos.ejemplossax.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PlantsCatalogXmlHandler extends DefaultHandler {
    // Objeto para almacenar el resultado del análisis
    private StringBuilder output = new StringBuilder();
    private boolean enNodoCommon = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //System.out.printf("Abriendo %s\n", qName);
        if (qName.equalsIgnoreCase("common")) {
            // Estamos empezando un nodo "common"
            enNodoCommon = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (enNodoCommon) {
            output.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        System.out.printf("Cerrando %s\n", qName);

        if (qName.equalsIgnoreCase("common") && enNodoCommon) {
            output.append("\n");
            enNodoCommon = false;
        }

    }

    public String getText() {
        return output.toString();
    }
}
