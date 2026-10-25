package LLD.DocumentEditor.services;

import LLD.DocumentEditor.interfaces.Element;

import java.util.List;

public class DocumentRenderer {

    public String render(List<Element> elements) {
        String renderedData = "\n";
        if (!elements.isEmpty()) {
            for (Element element : elements) {
                renderedData =  renderedData + element.render() + "\n";
            }
        }
        System.out.println(renderedData);
        return renderedData;
    }
}
