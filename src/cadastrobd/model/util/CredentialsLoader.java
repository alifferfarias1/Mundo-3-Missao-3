package cadastrobd.model.util;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.XMLConstants;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import java.util.logging.Logger;
import java.util.logging.Level;

public class CredentialsLoader {

    private static final Logger LOGGER = Logger.getLogger(CredentialsLoader.class.getName());
    private final String FILE_PATH = "resources/credentials.xml";
    
    private String databaseHost;
    private String databaseName;
    private String databaseUser;
    private String databasePassword;

    public CredentialsLoader() {
        loadCredentials();
    }

    private void loadCredentials() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(FILE_PATH));
            doc.getDocumentElement().normalize();

            NodeList userList = doc.getElementsByTagName("user");
            if (userList.getLength() > 0) {
                Element userElement = (Element) userList.item(0);
                databaseHost = userElement.getElementsByTagName("databaseHost").item(0).getTextContent();
                databaseName = userElement.getElementsByTagName("databaseName").item(0).getTextContent();
                databaseUser = userElement.getElementsByTagName("databaseUser").item(0).getTextContent();
                databasePassword = userElement.getElementsByTagName("databasePassword").item(0).getTextContent();
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading credentials: " + e.getMessage(), e);
        }
    }

    public String getDatabaseHost() {
        return databaseHost;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDatabaseUser() {
        return databaseUser;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }
}