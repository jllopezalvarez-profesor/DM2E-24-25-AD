package es.jllopezalvarez.accesodatos.ejemplos.ejemplossax.simple;

import org.w3c.dom.Attr;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SimpleSaxHandler extends DefaultHandler {

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        System.out.println("Apertura: " + qName);
        if (attributes.getLength() > 0){
            System.out.println("El elemento tiene atributos");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("Cierre: " + qName);
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // Esto no es correcto, hay que tener en cuenta los índices para extraer los datos
        System.out.println("Caracteres recibidos"+ new String(ch));
    }
}
