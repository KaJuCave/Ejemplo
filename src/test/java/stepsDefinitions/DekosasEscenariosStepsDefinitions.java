package stepsDefinitions;
import cucumber.api.java.Before;
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
import tasks.BuscarProducto;
import tasks.DekosasCarrito;
import uis.DekosasPage;

public class DekosasEscenariosStepsDefinitions {
    String productoExitoso = "Reebok Hiit Tr Dynred";
    String productoFallido = "Mouse";

    Actor actor = new Actor("Juliana");

    @Given("^Me encuentro en la pagina de Dekosas$")
    public void me_encuentro_en_la_pagina_de_Dekosas() {
        actor.can(BrowseTheWeb.with(GoogleChromeDriver.chromeHisBrowserWeb().on("https://dekosas.com/co/")));    }


    @When("^busco el nombre del producto \"([^\"]*)\" disponible en la pagina$")
    public void busco_el_nombre_del_producto_disponible_en_la_pagina(String producto) {
        actor.attemptsTo(DekosasCarrito.enDekosas(producto));
    }


    @Then("^visualizo detalles del producto \"([^\"]*)\" en pantalla$")
    public void visualizo_detalles_del_producto_en_pantalla(String producto) {
        actor.should(GivenWhenThen.seeThat(WebElementQuestion.the(DekosasPage.TXT_ELEMENTO_BUSQUEDA.of(producto))));
    }

    @When("^busco el nombre del producto \"([^\"]*)\"que no esta en la pagina$")
    public void busco_el_nombre_del_producto_que_no_esta_en_la_pagina(String producto) {
        actor.attemptsTo(DekosasCarrito.enDekosas(producto));
    }

    @Then("^visualizo \"([^\"]*)\" no disponible$")
    public void visualizo_no_disponible(String producto) {
        actor.should(GivenWhenThen.seeThat(WebElementQuestion.the(DekosasPage.TXT_ELEMENTO_BUSQUEDA.of(producto))));
    }

}

