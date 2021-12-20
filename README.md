# Automatización para la pagina Web [Dekosas](https://dekosas.com/co/)
En este proyecto se desarrollo la automatización donde se desarrollaran los siguiente puntos 
* Buscar los 5 productos no desde el excel sino desde el feature con examples
* Utilizar un Background para realizar como mínimo 2 escenarios.
* Realizar un escenario fallido y uno exitoso sin examples.

## Estructura del proyecto 📂
_En esta sección encontrara los pasos básicos para el desarrollo del proyecto_

* Se crea un proyecto **Gradle** en el entorno de desarrollo.

* Para este proyecto se crearán paquetes y directorios para la automatización. Comencemos creando cuatro paquetes en nuestro directorio **main/java** para los drivers del navegador, los elementos que se utilizaran de la página **tasks** y los pasos que se automatizaran en la página Dekosas **uis** (Diseño de interfaz de usuario).

![src](https://github.com/KaJuCave/imagenesDekosas/blob/master/src.PNG)

Dentro del fichero **test/java** se creara los paquetes para las clase de ejecución y definición de los pasos dentro de la pagina Dekosas

![test](https://github.com/KaJuCave/imagenesDekosas/blob/master/test.PNG)

Por último en el directorio **resources** se creara la clase con la extensión **.feature**, la cual contiene la descripción de prueba que se va a ejecutar.

![resourse](https://github.com/KaJuCave/imagenesDekosas/blob/master/features.PNG)

_Nota: En las siguientes secciones se explicarán con más detalle la codificación de cada una de sus clases_ 

* Adicionalmente se debe descargar el [chromedriver]( https://chromedriver.chromium.org/downloads) de acuerdo a la versión del navegador, este le permitira que implementa el protocolo de WebDriver para Chromium. Por último, se agrega este driver y el archivo de Excel (con el nombre de los productos) a los archivos del proyecto.

![driverExcel](https://github.com/KaJuCave/imagenesDekosas/blob/master/driversExcel.PNG)

## Codificación del proyecto 📂💻

En esta sesión se explicará detalladamente la codificación que se implementó para automatización de los productos de la página Dekosas.

### Agregar dependencias

Para que el correcto funcionamiento de algunas utilidades en el proyecto se debe agregar las dependencias en el archivo ```build.gradle``` que se muestran a continuación 

```gradle
apply plugin: 'java-library'
apply plugin: 'net.serenity-bdd.aggregator'
apply plugin: 'eclipse'

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()

}

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()

    }
    dependencies {
        classpath("net.serenity-bdd:serenity-gradle-plugin:2.0.80")
    }
}

dependencies {
    implementation 'net.serenity-bdd:serenity-junit:2.0.80'
    implementation 'net.serenity-bdd:serenity-cucumber:1.9.45'
    implementation 'net.serenity-bdd:serenity-core:2.0.80'
    implementation 'org.slf4j:slf4j-simple:1.7.7'
    implementation group: 'org.apache.poi', name: 'poi', version: '3.17'
    implementation group: 'org.apache.poi', name: 'poi-ooxml', version: '3.17'
}

test {
    ignoreFailures = true
}
gradle.startParameter.continueOnFailure = true

```
### Características ChromeDriver

Para este proyecto se utilizará el navegador **Google Chrome**, por esto creamos unos drivers que nos permitirán utilizar este navegador. Para realizar se creó la clase ```GoogleChromeDriver.java``` y se instanció un objeto de la interfaz **WebDriver** 

``` java
public static WebDriver driver;
```

Seguidamente se crea un método donde se especifican las opciones que tendrá el navegador como al iniciar la automatización la ventana este maximizada y por ultimo se implementa el método ```driver.get(url) ```  el cual nos permite navegar a la URL pasada como argumento y espera hasta que se cargue la página.


```java
public class GoogleChromeDriver {

    public static WebDriver driver;

    public static void chomeWebDriver(String url) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-infobars");
        driver = new ChromeDriver(options);
        driver.get(url);
    }
}

```
### Elementos y pasos en la página Dekosas

Como la página que utilizamos para la automatización es [Dekosas.com](https://dekosas.com/co/) creamos una clase ```DekosasPage.java ``` en la cual creamos como atributos de la clase **By** los botones y los textos de los elementos a buscar en la página.

```java
public class DekosasPage {

        public static final Target TXT_BUSCADOR = Target.the("").locatedBy("//input[@id='search' and @name='q']");
        public static final Target BTN_BUSCADOR = Target.the("").locatedBy("//button[@class='amsearch-loupe' and @title='Buscar']");
        public static final Target BTN_ELEMENTO_BUSQUEDA = Target.the("").locatedBy("//a[contains(text(),'{0}')]");
        public static final Target TXT_ELEMENTO_BUSQUEDA = Target.the("").locatedBy("//span[contains(text(),'{0}')]");
        public static final Target BTN_CARRITO = Target.the("").locatedBy("//button[@class='action primary tocart' and @title='Agregar al Carrito']");
        public static final Target LB_CARRITO = Target.the("").locatedBy("//div[@data-bind='html: message.text']");
        public static final Target BTN_MUEBLES = Target.the("").locatedBy("//a[@class='level-top' and @href='https://dekosas.com/co/muebles-accesorios']");
}
```

**DekosasSteps**

Dentro de la clase ``` DekosasSteps.java``` especificaremos los pasos que la página realizara en la automatización. 

```java
Public class DekosasStepsDefinitions {

    Actor actor = new Actor("Juliana");

    @Given("^que me encuentro en la pagina Dekosas$")
    public void queMeEncuentroEnLaPaginaDekosas() {
        actor.can(BrowseTheWeb.with(GoogleChromeDriver.chromeHisBrowserWeb().on("https://dekosas.com/co/")));
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
```

```java
public class DekosasEscenariosStepsDefinitions {
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
```

```java
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
```
### Caracteriscticas de la automatización (feature)

Se crea un archivo con nombre ``` DekosasBuscador``` y con extensión ```.feature``` donde se describen las siguientes características de la prueba. 

``` Feature: ```  nombre de la funcionalidad de la prueba. Con el caso de usuario que se va a probar 
``` Scenario: ``` se realiza para especificar la funcionalidad la búsqueda de productos 
``` Given: ```  se describe el contexto, las precondiciones.
``` When: ```  se especifican las acciones que se van a ejecutar.
``` Then: ```  y acá se especifica el resultado esperado, las validaciones a realizar.

```feature
Feature: HU-001 Buscador Dekosas
  Yo como usuario en la pagina web Dekosas
  Quiero buscar los productos en la plataforma
  Para ver las caracteristicas de los producto

  Scenario Outline: Buscar productos
    Given que me encuentro en la pagina Dekosas
    When busque el producto <NombreProducto>
    Then podre ver <NombreProducto> en pantalla

    Examples:
      |NombreProducto|
      |Separador De Yemas Greenf - DKS Worldwide|
      |Set de Desayuno - Snoopy|
      |Raqueta Niña Talla 23 - Zoom Sports|
      |Portavasos Silueta Navidad – Mulikka|
      |Mesa Plegable Madera - Tramontina|
      |Retablo Bat Man - Decasa|

```
```feature
Feature: HU-002 Agregar al carrito
  Yo como usuario de Dekosas
  quiero buscar un producto en la pagina Dekosas
  para agregarlo al carrito de compras

  Background:
    Given Dado que me encuentro en la pagina

  Scenario: Seleccionar el producto a buscar
    When seleccione el producto que quiero encontrar "Retablo Bat Man - Decasa"
    Then vemos producto "Retablo Bat Man - Decasa" el producto al carrito de compras

  Scenario: Seleccionar la categoria de muebles
    When selecciono en la pantalla principal la categoria muebles
    Then visualizo todos los productos de la categoria en Dekosas
```
```feature
Feature: HU-002 Escenarios Exitosos y fallidos Dekosas
  Yo como usuario de Dekosas
  quiero buscar un producto que este
  disponiple  y agotado

  Background:
    Given Me encuentro en la pagina de Dekosas

  Scenario: Buscar producto disponible
    When busco el nombre del producto "Juego De Habilidad Jenga - Landik" disponible en la pagina
    Then visualizo detalles del producto "Juego De Habilidad Jenga - Landik" en pantalla
  Scenario: Buscar producto no disponibles
    When busco el nombre del producto " Zapato Ruedas Talla 29 - Minions"que no esta en la pagina
    Then visualizo "Zapato Ruedas Talla 29 - Minions" no disponible
```
```feature
Feature: HU-003 Escenarios Exitosos y fallidos Dekosas
  Yo como usuario de Dekosas
  quiero buscar un producto que este
  disponiple  y agotado

  Background:
    Given Me encuentro en la pagina de Dekosas

  Scenario: Buscar producto disponible
    When busco el nombre del producto "Juego De Habilidad Jenga - Landik" disponible en la pagina
    Then visualizo detalles del producto "Juego De Habilidad Jenga - Landik" en pantalla
  Scenario: Buscar producto no disponibles
    When busco el nombre del producto " Zapato Ruedas Talla 29 - Minions"que no esta en la pagina
    Then visualizo "Zapato Ruedas Talla 29 - Minions" no disponible
```

## Ejecución 💻

Después de realizar la codificación que se explicó anteriormente se  _ejecutar_ el proyecto en desde la clase **DekosasBuscadorRunner.java**, donde se definió los siguientes parámetros:

* @RunWith :  Es el runner de cucumber con serenity ejecutará todas las funciones que se encuentran en la ruta de clase en el mismo paquete que esta clase.

*  @CucumberOptions :  Es propio de cucumber. Tiene varias opciones de configuración, para este proyecto utilizamos:

	* features: Se coloca la dirección con la ubicar del archivo de características en la carpeta del proyecto.
	* glue: Es muy parecido a la opción anterior, pero la diferencia es que ayuda a Cucumber a localizar el archivo con la **definición de pasos**. Para este proyecto es la clase **stepsDefinitions**.
	* snippets: Es el formato de los fragmentos del código que genera Cucumber, para este caso se elige le tipo CAMELCASE.

``` java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src\\test\\resources\\features\\DekosasBuscador.feature",
        glue = "stepsDefinitions",
        snippets = SnippetType.CAMELCASE
)
public class DekosasBuscadorRunner {
}

```

``` java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src\\test\\resources\\features\\DekosasCarrito.feature",
        glue = "stepsDefinitions",
        snippets = SnippetType.CAMELCASE
)
public class DekosasRunnerH2 {
}


```

``` java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src\\test\\resources\\features\\DekosasEscenarios.feature",
        glue = "stepsDefinitions",
        snippets = SnippetType.CAMELCASE
)
public class DekosasRunnerH3 {
}

```
## Construido con 🛠️

_En este proyecto se utilizaron las siguientes herramientas_

* [IntelliJ IDEA ](https://www.jetbrains.com/es-es/idea/) - El entorno de desarrollo usado
* [Gradle](https://gradle.org/) - Sistema de automatización  
* [Cucumber](https://cucumber.io/) - Software que aplica el desarrollo impulsado por el comportamiento
* [Selenium](https://www.selenium.dev/) - Entorno de pruebas de software para aplicaciones basadas en la web

---
Elaborado por: [Juliana Cano Vega](https://github.com/KaJuCave)💻👩🏻
