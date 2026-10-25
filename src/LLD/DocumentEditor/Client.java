package LLD.DocumentEditor;

import LLD.DocumentEditor.services.data_save_service.SaveToFile;
import LLD.DocumentEditor.services.element_service.Element;
import LLD.DocumentEditor.services.data_save_service.Persistence;
import LLD.DocumentEditor.services.*;
import LLD.DocumentEditor.services.element_service.ImageElement;
import LLD.DocumentEditor.services.element_service.TabElement;
import LLD.DocumentEditor.services.element_service.TextElement;

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
