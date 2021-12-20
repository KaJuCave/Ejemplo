package tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;
import uis.DekosasPage;

public class DekosasEscenarios implements Task {

    private String producto;

    public DekosasEscenarios(String producto) {
        this.producto = producto;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(producto).into(DekosasPage.TXT_BUSCADOR).thenHit(Keys.ENTER),
                Click.on(DekosasPage.BTN_ELEMENTO_BUSQUEDA.of(producto))
        );
    }

    public static DekosasCarrito enDekosas(String producto){
        return Instrumented.instanceOf(DekosasCarrito.class).withProperties(producto);
    }
}
