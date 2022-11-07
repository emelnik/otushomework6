package data;

public enum CountryCity {

    RUSSIA("Россия", "Москва"),
    KAZAKHSTAN("Казахстан", "Астана");

    private String city;
    private String country;

    CountryCity(String country, String city){
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity(){
        return city;
    }
}
