package stepsDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import drivers.GoogleChromeDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import org.junit.Before;
import tasks.BuscarProducto;
import uis.DekosasPage;

import java.io.IOException;

public class DekosasStepsDefinitions {

    Actor actor = new Actor("Juliana");

    @Given("^que me encuentro en la pagina Dekosas$")
    public void queMeEncuentroEnLaPaginaDekosas() {
        actor.can(BrowseTheWeb.with(GoogleChromeDriver.chromeHisBrowserWeb().on("https://dekosas.com/co/")));
        //  OnStage.theActorCalled("").can(BrowseTheWeb.with(GoogleChromeDriver.chromeHisBrowserWeb().on("https://dekosas.com/co/")));

    }

    @When("^busque el producto (.*)$")
    public void busqueElProductoSeparadorDeYemasGreenfDKSWorldwide(String producto) {
        actor.attemptsTo(BuscarProducto.enDekosas(producto));
    }

    @Then("^podre ver (.*) en pantalla$")
    public void podreVerSeparadorDeYemasGreenfDKSWorldwideEnPantalla(String producto) {
        actor.should(GivenWhenThen.seeThat(WebElementQuestion.the(DekosasPage.TXT_ELEMENTO_BUSQUEDA.of(producto)), WebElementStateMatchers.containsText(producto)));
    }
}

