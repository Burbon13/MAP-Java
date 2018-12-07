package Repo;

import Task.MessageTask;
import Validator.IValidator;
import Validator.ValidationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

public class XMLRepo extends MTRepository {
    private String xmlFile;

    public XMLRepo(IValidator<MessageTask> v, String xmlFile) {
        super(v);
        this.xmlFile = xmlFile;
        loadData();
    }

    void loadData() {
        try {
            Document docXml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
            Element root = docXml.getDocumentElement();
            NodeList list = root.getChildNodes();
            for(int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if(node.getNodeType() == Element.ELEMENT_NODE) {
                    try {
                        super.save(getFromNode((Element)node));
                    } catch (ValidationException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try {
            Document docXml = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = docXml.createElement("tasks");
            docXml.appendChild(root);

            entityes.values().forEach(mt -> root.appendChild(getElementFromMT(mt, docXml)));
            Transformer transXml = TransformerFactory.newInstance().newTransformer();
            transXml.transform(new DOMSource(docXml), new StreamResult(xmlFile));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private Element createElement(Document docXml, String tag, String value) {
        Element el = docXml.createElement(tag);
        el.setTextContent(value);
        return el;
    }

    private Element getElementFromMT(MessageTask messageTask, Document docXml) {
        Element e = docXml.createElement("task");
        e.setAttribute("id", messageTask.getId());
//        docXml.appendChild(e);
        e.appendChild(createElement(docXml, "descriere", messageTask.getDescriere()));
        e.appendChild(createElement(docXml, "mesaj", messageTask.getMesaj()));
        e.appendChild(createElement(docXml, "from", messageTask.getFrom()));
        e.appendChild(createElement(docXml, "to", messageTask.getTo()));
        e.appendChild(createElement(docXml, "data", messageTask.getDate().toString().replace('T', ' ')));
        return e;
    }

    @Override
    public void save(MessageTask entity) throws ValidationException {
        super.save(entity);
        saveToFile();
    }

    private MessageTask getFromNode(Element node) {
        String id = node.getAttributeNode("id").getValue();
        String descriere = node.getElementsByTagName("descriere").item(0).getTextContent();
        String mesaj = node.getElementsByTagName("mesaj").item(0).getTextContent();
        String from = node.getElementsByTagName("from").item(0).getTextContent();
        String to = node.getElementsByTagName("to").item(0).getTextContent();
        String data = node.getElementsByTagName("data").item(0).getTextContent();
        return new MessageTask(id, descriere, mesaj, from,to, data);
    }
}
