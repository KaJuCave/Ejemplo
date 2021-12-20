package stepsDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import drivers.GoogleChromeDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import org.junit.Before;
import tasks.BuscarProducto;
import tasks.DekosasCarrito;
import tasks.DekosasCategoriaMuebles;
import uis.DekosasPage;

public class DekosasCarritoStepsDefinitions {

    Actor actor = new Actor("Juliana");

    @Given("^Dado que me encuentro en la pagina$")
    public void dadoQueMeEncuentroEnLaPagina() {
        actor.can(BrowseTheWeb.with(GoogleChromeDriver.chromeHisBrowserWeb().on("https://dekosas.com/co/")));
    }

    @When("^seleccione el producto que quiero encontrar \"([^\"]*)\"$")
    public void seleccioneElProductoQueQuieroEncontrar(String producto) {
        actor.attemptsTo(BuscarProducto.enDekosas(producto));

    }
    @Then("^vemos producto \"([^\"]*)\" el producto al carrito de compras$")
    public void vemos_producto_el_producto_al_carrito_de_compras(String producto) {
        actor.should(GivenWhenThen.seeThat(WebElementQuestion.the(DekosasPage.TXT_ELEMENTO_BUSQUEDA.of(producto))));
    }


    @When("^selecciono en la pantalla principal la categoria muebles$")
    public void selecciono_en_la_pantalla_principal_la_categoria_muebles(String producto) {
        actor.attemptsTo(BuscarProducto.enDekosas(producto));
    }

    @Then("^visualizo todos los productos de la categoria en Dekosas $")
    public void visualizo_todos_los_productos_de_la_categoria_en_Dekosas() {
        actor.should(GivenWhenThen.seeThat(WebElementQuestion.the(DekosasPage.BTN_MUEBLES)));
    }

}
