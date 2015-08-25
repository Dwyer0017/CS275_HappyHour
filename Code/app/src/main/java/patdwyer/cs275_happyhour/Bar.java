package patdwyer.cs275_happyhour;

import android.graphics.Bitmap;

/**
 * Created by patrickdwyer on 8/25/15.
 */
public class Bar {

    private String name;
    private String address;
    private String neighborhood;
    private String city;
    private String state;
    private String phone;
    private int distance;
    private double lat;
    private double lng;
    private String[] categories;
    private int rating;
    private String imageURL;
    private Bitmap pic;

    public Bar(String name, String address, String city, String state, int distance, int rating, String imageURL) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.distance = distance;
        this.rating = rating;
        this.imageURL = imageURL;
    }

    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }
}
