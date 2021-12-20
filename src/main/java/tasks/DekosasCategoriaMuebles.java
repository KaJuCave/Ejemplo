package tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;
import uis.DekosasPage;

public class DekosasCategoriaMuebles implements Task {

    private String producto;

    public DekosasCategoriaMuebles(String producto) {
        this.producto = producto;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(DekosasPage.BTN_MUEBLES.of(producto))

        );
    }

    public static DekosasCategoriaMuebles enDekosas(String producto){
        return Instrumented.instanceOf(DekosasCategoriaMuebles.class).withProperties(producto);
    }
}
