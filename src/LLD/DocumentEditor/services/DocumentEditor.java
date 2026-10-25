package LLD.DocumentEditor.services;

import LLD.DocumentEditor.services.element_service.Element;

import java.util.ArrayList;
import java.util.List;

public class DocumentEditor {

    DocumentRenderer renderer = new DocumentRenderer();
    List<Element> elements;

    public DocumentEditor() {
        this.elements = new ArrayList<>();
    }

    public void addDocumentElement(Element element) {
        this.elements.add(element);
    }

    public String render() {
        return renderer.render(this.elements);
    }
}
