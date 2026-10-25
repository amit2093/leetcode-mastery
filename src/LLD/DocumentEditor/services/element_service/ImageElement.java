package LLD.DocumentEditor.services.element_service;


public class ImageElement implements Element {

    String image;
    public ImageElement(String image) {
        this.image = image;
    }

    @Override
    public String render() {
        return "Image: " + this.image;
    }
}
