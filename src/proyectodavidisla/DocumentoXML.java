/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectodavidisla;

import Datos.Alumno;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class DocumentoXML {

    public void escribo(String nombreDocumento, ArrayList alumnos) {
        if (alumnos.isEmpty()) {
            System.out.println("ERROR datos de entrada no validos");
        } else {
            //sin datos, sólo el elemento raiz
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;

            try {
                db = dbf.newDocumentBuilder();
                //Creamos el documento XML y le pasamos la etiqueta raiz
                DOMImplementation implementation = db.getDOMImplementation();
                Document document = implementation.createDocument(null, nombreDocumento, null);
                document.setXmlVersion("1.0");
                //Main Node: Primer ejemplos, sólo con el elemento raíz
                Element raiz = document.getDocumentElement();
                System.out.println("Raiz: " + raiz.getNodeName());
                //Ahora creamos un elemento con los datos del array
                Iterator<Alumno> v1 = alumnos.iterator();
                while (v1.hasNext()) {
                    System.out.println("Entramos al bucle");
                    Alumno n1 = v1.next();
                    //Creamos la Etiqueta alumno
                    Element etiquetaAlumno = document.createElement("Alumno");
                    //Creamos la Etiqueta nombre
                    Element etiquetaNombre = document.createElement("Nombre");
                    Text valorNombre = document.createTextNode(n1.getNombre());
                    etiquetaNombre.appendChild(valorNombre);
                    //Creamos la Etiqueta Apellido
                    Element etiquetaApellido = document.createElement("Apellido");
                    Text valorApellido = document.createTextNode(n1.getApellido());
                    etiquetaApellido.appendChild(valorApellido);
                    //Creamos la Etiqueta Direccion
                    Element etiquetaDireccion = document.createElement("Direccion");
                    Text valorDireccion = document.createTextNode(n1.getDireccion());
                    etiquetaDireccion.appendChild(valorDireccion);

                    //Añadimos las etiquetas nombre, apellidos y direccion
                    //a la etiqueta alumno
                    etiquetaAlumno.appendChild(etiquetaNombre);
                    etiquetaAlumno.appendChild(etiquetaApellido);
                    etiquetaAlumno.appendChild(etiquetaDireccion);
                    //Añadimos la etiqueta alumno a la raiz 
                    raiz.appendChild(etiquetaAlumno);
                    System.out.println(n1.getNombre() + " " + n1.getApellido() + " " + n1.getDireccion());

                }

                //Generate XML
                Source source = new DOMSource(document);
                //Indicamos donde lo queremos almacenar
                //No tiene porque coincidir el Nombre de la etiqueta Raiz con la Etiqueta Raiz
                Result result = new StreamResult(new java.io.File("fichero/" + nombreDocumento + ".xml"));
                System.out.println("hasta aqui llegamos");
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.transform(source, result);
                JOptionPane.showMessageDialog(null, "Documento creado correctamente");
            } catch (ParserConfigurationException ex) {
                System.out.println("Error escribiendo Fichero");
            } catch (TransformerConfigurationException ex) {
                System.out.println("Error escribiendo Fichero");
            } catch (TransformerException ex) {
                System.out.println("Error escribiendo Fichero");
            }

        }

    }
}
