package christmas;

import christmas.common.exception.ExceptionHandler;
import christmas.common.retryHandler.RetryHandler;
import christmas.controller.ControllerFacade;
import christmas.controller.eventController.EventController;
import christmas.controller.orderControler.DefaultOrderController;
import christmas.controller.orderControler.OrderController;
import christmas.controller.orderControler.OrderControllerRetryProxy;
import christmas.controller.purchaseController.PurchaseController;
import christmas.io.input.InputHandler;
import christmas.io.input.InputParser;
import christmas.io.input.InputValidator;
import christmas.io.output.OutputHandler;
import christmas.io.output.OutputParser;
import christmas.io.reader.MissionUtilsReader;
import christmas.io.reader.Reader;
import christmas.io.writer.SystemWriter;
import christmas.io.writer.Writer;
import christmas.service.dateProvider.DefaultDateProvider;

public class Application {
    public static void main(String[] args) {
        getControllerFacade().run();
    }
    
    public static ControllerFacade getControllerFacade() {
        Writer writer = new SystemWriter();
        Reader reader = new MissionUtilsReader();
        InputHandler inputHandler = new InputHandler(writer, reader, new InputParser(), new InputValidator());
        OutputHandler outputHandler = new OutputHandler(writer, new OutputParser());
        DefaultDateProvider dateProvider = new DefaultDateProvider();
        OrderController defaultOrderController = new DefaultOrderController(inputHandler, dateProvider);
        OrderController orderControllerRetryProxy = new OrderControllerRetryProxy(defaultOrderController, new RetryHandler(new ExceptionHandler(writer)));
        return new ControllerFacade(
                orderControllerRetryProxy,
                new EventController(dateProvider),
                new PurchaseController(outputHandler)
        );
    }
}
