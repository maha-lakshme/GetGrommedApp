package in.nandhu.getgrommedapp.Models;

import java.io.Serializable;

public class Product_colors implements Serializable {

    String hex_value;
    String colour_name;

    public String getHex_value() {
        return hex_value;
    }

    public void setHex_value(String hex_value) {
        this.hex_value = hex_value;
    }

    public String getColour_name() {
        return colour_name;
    }

    public void setColour_name(String colour_name) {
        this.colour_name = colour_name;
    }


    @Override
    public String toString() {
        return "Product_colors{" +
                "hex_value='" + hex_value + '\'' +
                ", colour_name='" + colour_name + '\'' +
                '}';
    }
}
