package repository;

import domain.MessageTask;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import validation.IValidation;
import validation.ValidationException;

import javax.swing.text.DateFormatter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class XMLRepository extends MessageTaskRepository{
    private String fisier;
    public XMLRepository(IValidation<MessageTask> validator, String numeFisier) {
        super(validator);
        this.fisier = numeFisier;
        loadFromFile();
    }

    private void loadFromFile(){
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fisier);

            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            for(int i=0; i<list.getLength(); i++){
                Node node = list.item(i);
                if(node instanceof Element){
                    save(createFromElement((Element)node));
                }
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void save(MessageTask mt){
        try {
            super.save(mt);
            saveToFile();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile(){
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            doc.appendChild(doc.createElement("tasks"));
            Element root = doc.getDocumentElement();
            findAll().forEach(mesaj ->
            {
                Element msg = createFromMessage(mesaj, doc);
                root.appendChild(msg);
            });
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(fisier));
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }


    }

    private MessageTask createFromElement(Element e){
        String idTask = e.getAttributeNode("id").getValue();
        String descriere = e.getElementsByTagName("descriere").item(0).getTextContent();
        String mesaj = e.getElementsByTagName("mesaj").item(0).getTextContent();
        String from = e.getElementsByTagName("from").item(0).getTextContent();
        String to = e.getElementsByTagName("to").item(0).getTextContent();
        String data = e.getElementsByTagName("data").item(0).getTextContent();
        return new MessageTask(idTask, descriere, mesaj, to, from, data);
    }

    private Element createFromMessage(MessageTask message, Document doc){
        Element root = doc.getDocumentElement();
        Element e = doc.createElement("task");
        e.setAttribute("id", message.getId());
        e.appendChild(createElement("descriere", doc, message.getDescriere()));
        e.appendChild(createElement("mesaj", doc, message.getMesaj()));
        e.appendChild(createElement("from", doc, message.getFrom()));
        e.appendChild(createElement("to", doc, message.getTo()));
        e.appendChild(createElement("data", doc, message.getDate().toString()));
        return e;
    }

    private Element createElement(String tag, Document doc, String value){
        Element e = (Element) doc.createElement(tag);
        e.setTextContent(value);
        Element root = doc.getDocumentElement();
        return e;
    }
}
