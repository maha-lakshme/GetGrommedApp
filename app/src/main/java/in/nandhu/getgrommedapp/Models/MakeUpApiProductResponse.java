package in.nandhu.getgrommedapp.Models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class MakeUpApiProductResponse implements Serializable {

    String id;
    String brand;
    String name;
    String price;
    String price_sign;
    String currency;
    String image_link;
    String product_link;
    String website_link;
    String description;
    String rating;
    String category;
    String product_type;
    String[] tag_list;
    String created_at;
    String updated_at;
    String product_api_url;
    String api_featured_image;
    List<Product_colors> product_colors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_sign() {
        return price_sign;
    }

    public void setPrice_sign(String price_sign) {
        this.price_sign = price_sign;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getProduct_link() {
        return product_link;
    }

    public void setProduct_link(String product_link) {
        this.product_link = product_link;
    }

    public String getWebsite_link() {
        return website_link;
    }

    public void setWebsite_link(String website_link) {
        this.website_link = website_link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String[] getTag_list() {
        return tag_list;
    }

    public void setTag_list(String[] tag_list) {
        this.tag_list = tag_list;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getProduct_api_url() {
        return product_api_url;
    }

    public void setProduct_api_url(String product_api_url) {
        this.product_api_url = product_api_url;
    }

    public String getApi_featured_image() {
        return api_featured_image;
    }

    public void setApi_featured_image(String api_featured_image) {
        this.api_featured_image = api_featured_image;
    }

    public List<Product_colors> getProduct_colors() {
        return product_colors;
    }

    public void setProduct_colors(List<Product_colors> product_colors) {
        this.product_colors = product_colors;
    }

    @Override
    public String toString() {
        return "MakeUpApiProductResponse{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", price_sign='" + price_sign + '\'' +
                ", currency='" + currency + '\'' +
                ", image_link='" + image_link + '\'' +
                ", product_link='" + product_link + '\'' +
                ", website_link='" + website_link + '\'' +
                ", description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                ", category='" + category + '\'' +
                ", product_type='" + product_type + '\'' +
                ", tag_list=" + Arrays.toString(tag_list) +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", product_api_url='" + product_api_url + '\'' +
                ", api_featured_image='" + api_featured_image + '\'' +
                ", product_colors=" + product_colors +
                '}';
    }
}
