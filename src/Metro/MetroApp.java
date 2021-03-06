package Metro;

import Exceptions.BadFileException;

import java.io.IOException;

public class MetroApp {

    Controller controller;
    MetroMapParser metroMapParser;

    public MetroApp() {
        try {
            metroMapParser = new MetroMapParser("resources/bostonmetro.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            controller = new Controller(metroMapParser.generateGraphFromFile());
            controller.run();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (BadFileException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, BadFileException {
        MetroApp metroApp = new MetroApp();
    }
}
