package LLD.DocumentEditor.services.element_service;

public class TabElement implements Element {

    String text;
    public TabElement(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return "\t" + text;
    }
}
