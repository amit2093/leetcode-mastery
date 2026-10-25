package LLD.DocumentEditor;

import LLD.DocumentEditor.interfaces.Element;
import LLD.DocumentEditor.interfaces.Persistence;
import LLD.DocumentEditor.services.*;

public class Client {

    public static void main(String[] args) {

        Element text = new TextElement("Hi, this is document editor");
        Element image = new ImageElement("pictures.png");
        Element tab = new TabElement("Tab added");

        DocumentEditor documentEditor = new DocumentEditor();
        documentEditor.addDocumentElement(text);
        documentEditor.addDocumentElement(image);
        documentEditor.addDocumentElement(tab);

        String renderedData = documentEditor.render();

        Persistence save = new SaveToFile();
        save.save(renderedData);
    }
}
