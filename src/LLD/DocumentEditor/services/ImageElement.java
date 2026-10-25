package LLD.DocumentEditor.services;

import LLD.DocumentEditor.interfaces.Element;


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
