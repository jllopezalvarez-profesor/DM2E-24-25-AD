package es.jllopezalvarez.accesodatos.ejemplos.ejemplossax.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BooksCatalogHandler extends DefaultHandler {

    private StringBuilder text = new StringBuilder();
    boolean enNodoAuthor = false;
    int cuantos = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("author")){
            enNodoAuthor = true;
            cuantos++;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("author")){
            enNodoAuthor = false;
        }
        if (qName.equalsIgnoreCase("catalog")){
            text.insert(0, String.format("El número de autores es: %s\n", cuantos));
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (enNodoAuthor){
            String nombreAutor = String.valueOf(ch, start, length);
            text.append(String.format("Autor: %s\n", nombreAutor));
        }
    }

    public String getText() {
        return text.toString();
    }
}
