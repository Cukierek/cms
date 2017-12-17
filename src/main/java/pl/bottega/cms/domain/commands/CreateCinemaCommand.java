package pl.bottega.cms.domain.commands;

public class CreateCinemaCommand implements Command {

    private String name;
    private String city;

    public CreateCinemaCommand(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
