package LLD.DocumentEditor.services.element_service;

public class TextElement implements Element {

    String text;
    public TextElement(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return "Text: " + this.text;
    }
}
