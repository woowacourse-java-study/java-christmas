package christmas;

import christmas.controller.ChristmasController;
import christmas.io.input.InputHandler;
import christmas.io.input.InputParser;
import christmas.io.output.OutputHandler;
import christmas.io.reader.MissionUtilsReader;
import christmas.io.reader.Reader;
import christmas.io.writer.SystemWriter;
import christmas.io.writer.Writer;

public class Application {
    public static void main(String[] args) {
        Writer writer = new SystemWriter();
        Reader reader = new MissionUtilsReader();
        new ChristmasController(
                new InputHandler(writer, reader, new InputParser()),
                new OutputHandler(writer)
        ).start();
    }
}
