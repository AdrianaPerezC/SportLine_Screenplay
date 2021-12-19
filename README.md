# SportLineScreenPlay
Realiza la automatización en la pagina SportLine en los requistos de Buscar Producto, Seleccionar sección de promociones y validar escenarios exitosos y fallido de la búsqueda de un producto en la página, herramientas utilizadas en este proyecto son: Java, Cucumber, SerenityBDD y Screenplay.

## Estructura del Proyecto
![](https://user-images.githubusercontent.com/50307550/146684200-c72706d6-100f-43e9-9161-c5399d3ea97b.png)

## Drivers
En `GoogleChromeDriver` se crea y se inicializa un objeto `WebDriver`, el cual es una herramienta de código abierto para pruebas automatizadas de aplicaciones web en muchos navegadores. Proporciona capacidades para navegar a páginas web, entrada de usuarios, ejecución de JavaScript.

En el constructor de la clase se recibe como parámetro la url de la página a navegar:

```java
public class GoogleChromeDriver {
    public static WebDriver driver;

    public static GoogleChromeDriver chromeHisBrowserWeb(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-infobars");
        driver = new ChromeDriver(options);
        return new GoogleChromeDriver();
    }

    public WebDriver on(String url){
        driver.get(url);
        return driver;
    }
}
```
### Tasks
##### `BuscarProducto`
Realiza la busqueda de un producto en la pagina de SportLine, esta tarea implementa la interfaz Task y se sobreescribe su metodo, tambien recibe un parámetro de tipo String que corresponde al producto a buscar.

```java
public class BuscarProducto implements Task {
    private String producto;

    public BuscarProducto(String producto) {
        this.producto = producto;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(producto).into(SportLinePage.TXT_BUSCADOR),
                Hit.the(Keys.ENTER).into(SportLinePage.TXT_BUSCADOR),
                Click.on(SportLinePage.BTN_ELEMENTO_BUSQUEDA.of(producto))
        );
    }
    public static BuscarProducto enSportLine(String producto){
        return Instrumented.instanceOf(BuscarProducto.class).withProperties(producto);
    }
}
```
##### `BuscarProductoExitosoYFallido`
Realiza la busqueda de un producto en la pagina de SportLine(Para caso exitoso o fallido en la página), esta tarea implementa la interfaz Task y se sobreescribe su metodo, se establece una variable tipo String en la cual se define el nombre del producto a buscar.

```java 
public class BuscarProductoExitososYFallido implements Task {
    private String producto;

    public BuscarProductoExitososYFallido(String producto) {
        this.producto = producto;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(producto).into(SportLinePage.TXT_BUSCADOR),
                Hit.the(Keys.ENTER).into(SportLinePage.TXT_BUSCADOR),
                Click.on(SportLinePage.BTN_ELEMENTO_BUSQUEDA.of(producto))
        );
    }
    public static BuscarProducto enSportLine(String producto){
        return Instrumented.instanceOf(BuscarProducto.class).withProperties(producto);
    }
```
##### `SeleccionarPromociones`
Realiza seleccionar en la pagina de SportLine la sección de promociones, esta tarea implementa la interfaz Task y se sobreescribe su metodo, se establece una variable tipo String en la cual se define el nombre del producto a buscar.

```java
public class SeleccionarPromociones implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(SportLinePromociones.BTN_PROMOCIONES),
                Click.on(SportLinePromociones.BTN_PROM_ARTICULOS)
        );
    }
    public static SeleccionarPromociones enSportLine(){
        return Instrumented.instanceOf(SeleccionarPromociones.class).withProperties();
    }
}
```

### Uip (User Interface Page)
##### `SportLinePage`
Esta clase contiene todos los mapeos de los elementos de la pagina principal de SportLine, para realizar los mapeos se utilizó `Xpath`.
```java
public class SportLinePage {
    public static final Target TXT_BUSCADOR= Target.the("Campo de busqueda").locatedBy("//input[@id='search' and @class='input-text']");
    public static final Target BTN_ELEMENTO_BUSQUEDA= Target.the("").locatedBy("//strong[@class='product name product-item-name']//a[contains(text(),'{0}')]");
    public static final Target TXT_ELEMENTO_BUSQUEDA= Target.the("").locatedBy("//div[@class='product-info-main']/div[@class='page-title-wrapper']/h1[@class='page-title']//span[contains(text(),'{0}')]");
    public static final Target TXXT_ELEMENTO_NO_ENCONTRADO=Target.the("").locatedBy("//div[@class=\"no-results\"]/p");
}
```
##### `SportLinePromociones`
Esta clase contiene todos los mapeos de los elementos de la página de SportLine, en la sección promociones, para realizar los mapeos se utilizó `Xpath`.
```java
public class SportLinePromociones {
    public static final Target BTN_PROMOCIONES= Target.the("").locatedBy("//div[@class='top_links']/ul/li/a[@tabindex=\"0\" and @href=\"https://sportline.com.co/promociones/\"]");
    public static final Target BTN_PROM_ARTICULOS= Target.the("").locatedBy("//a/img [@class=\"pagebuilder-mobile-hidden\" and @alt=\"\" and @title=\"\" and @data-element=\"desktop_image\" and @data-pb-style=\"QL3FS4L\"]");
    public static final Target TXT_PROMOCION=Target.the("").locatedBy("(//div[@class=\"__featured_tag\"])[1]");
    public static final Target BTN_INFO_PRODUCTO=Target.the("").locatedBy("(//div[@class=\"slick-slide slick-active\"]/div/li[@class=\"product-item\"]/div[@class=\"product-item-info\"]/a)[1]");
    public static final Target TXT_INFO_PRODUCTO=Target.the("").locatedBy("(//th[@class=\"col label\" and @scope=\"row\"])[2]");
}
```
### Runners
##### `SportLineBuscadorRunner`
Como Cucumber usa Junit , necesitamos tener una clase SportLineBuscadorRunner. Esta clase usará la anotación de Junit @RunWith () , que le dice a JUnit cuál es la clase de corredor de prueba. Es un punto de partida para que Junit comience a ejecutar sus pruebas.
```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src\\test\\resources\\features\\SportLine.feature",
        glue = "stepsDefinitions",
        snippets = SnippetType.CAMELCASE
)
public class SportLineBuscadorRunner {
}
```

##### `SportLineBusquedaExitosaYFallidaRunner`
Este runner ejecuta llama los pasos asignados en el feature SportLineBusquedaExitosaYFallida.feature, en donde busca y ejecuta los metodos correspondientes en el paquete de stepDefinitios para realizar la ejecucion.
```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src\\test\\resources\\features\\SportLineBusquedaExitosaYFallida.feature",
        glue = "stepsDefinitions",
        snippets = SnippetType.CAMELCASE
)

public class SportLineBusquedaExitosaYFallidaRunner {
}
```

##### `SportLineSeleccionarPromociones`
Este runner ejecuta llama los pasos asignados en el feature SportLineSeccionPromociones.feature, en donde busca y ejecuta los metodos correspondientes en el paquete de stepDefinitios para realizar la ejecucion.
```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src\\test\\resources\\features\\SportLineSeccionPromociones.feature",
        glue = "stepsDefinitions",
        snippets = SnippetType.CAMELCASE
)
public class SportLineSeleccionarPromociones {
}
```
### StepsDefinitions
Gestiona los fragmentos de código definidos en el Given, Then, When de los escenarios de los archivos .feature escritos en lenguaje Gherkin. Cada método mapea las instrucciones del escenario:

##### `SportLineBuscadorStepDefinitions`

Define los pasos a realizar definidos en lenguaje Gherkin en `SportLine.feature` para la búsqueda de unos productos definidos en este escenario.

```java
public class SportLineBuscadorStepDefinitions {
    @Before
    public void before(){
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^Dado que me encuentro en la pagina de SportLine$")
    public void dadoQueMeEncuentroEnLaPaginaDeMercadoLibre() {
        OnStage.theActorCalled("Cliente").can(BrowseTheWeb.with(GoogleChromeDriver.chromeHisBrowserWeb().on("https://sportline.com.co/")));
    }

    @When("^busque el producto (.*)$")
    public void busqueElProducto(String producto) {
        OnStage.theActorInTheSpotlight().attemptsTo(BuscarProducto.enSportLine(producto));
    }

    @Then("^puedo ver (.*) en pantalla$")
    public void puedoVerElProductoEnPantalla(String producto) { WebElementStateMatchers.containsText(producto.replace("  "," "))));
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(WebElementQuestion.the(SportLinePage.TXT_ELEMENTO_BUSQUEDA.of(producto)), WebElementStateMatchers.containsText(producto.replace("  "," "))));

    }
}
```

##### `SportLineBusquedaExitosaYFallidaRunnerStepDefinitions`
Define los pasos a realizar definidos en lenguaje Gherkin en `SportLineBusquedaExitosaYFallida.feature` para la búsqueda de un producto (especificando un escenario exitosos y un escenario fallido).

```java
public class SportLineBusquedaExitosaYFallidaStepDefinitions {
    String productoExitoso="Reebok Hiit Tr Dynred";
    String productoFallido="Mouse";
    @Before
    public void before(){
        OnStage.setTheStage(new OnlineCast());
    }
    @When("^busco el nombre de producto existente en la pagina$")
    public void buscoElNombreDeProductoExistenteEnLaPagina() {
        OnStage.theActorInTheSpotlight().attemptsTo(BuscarProducto.enSportLine(productoExitoso));
    }
    @Then("^visualizo detalles del producto existente en pantalla$")
    public void visualizoDetallesDelProductoExistenteEnPantalla() {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(WebElementQuestion.the(SportLinePage.TXT_ELEMENTO_BUSQUEDA.of(productoExitoso)), WebElementStateMatchers.containsText(productoExitoso.replace("  "," "))));
    }
    @When("^busco el nombre del producto que no esta en la pagina$")
    public void buscoElNombreDelProductoQueNoEstaEnLaPagina() {
        OnStage.theActorInTheSpotlight().attemptsTo(BuscarProducto.enSportLine(productoFallido));
    }
    @Then("^visualizo un mensaje de no encontrado$")
    public void visualizoUnMensajeDeNoEncontrado() {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(WebElementQuestion.the(SportLinePage.TXXT_ELEMENTO_NO_ENCONTRADO), WebElementStateMatchers.containsText("no ha devuelto".replace("  "," "))));
    }
}
```

##### `SportLineSeleccionarPromocionesStepDefinitions`
Define los pasos a realizar definidos en lenguaje Gherkin en `SportLineSeccionPromociones.feature` para la verificación del funcionamiento de la sección promociones en la página SportLine, seleccionando el primer producto que esté en promoción.

```java
public class SportLineSeleccionarPromocionesStepsDefinitions {
    @Given("^Dado que me encuentro en la pagina$")
    public void dadoQueMeEncuentroEnLaPagina() {
        OnStage.theActorCalled("Cliente").can(BrowseTheWeb.with(GoogleChromeDriver.chromeHisBrowserWeb().on("https://sportline.com.co/")));
    }
    @When("^seleccione la seccion promociones de la pagina SportLine$")
    public void seleccioneLaSeccionPromocionesDeLaPaginaSportLine() {
        OnStage.theActorInTheSpotlight().attemptsTo(SeleccionarPromociones.enSportLine());
    }
    @Then("^vemos los productos en promocion$")
    public void vemosLosProductosEnPromocion() {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(WebElementQuestion.the(SportLinePromociones.TXT_PROMOCION), WebElementStateMatchers.containsText("% menos en carrito".replace("  "," "))));
    }
    @When("^selecciono el primer producto que aparace")
    public void seleccionoElPrimerProductoQueAparace() {
        OnStage.theActorInTheSpotlight().attemptsTo( Click.on(SportLinePromociones.BTN_INFO_PRODUCTO));
    }
    @Then("^visualizo las caracteristicas del producto seleccionado$")
    public void visualizoLasCaracteristicasDelProductoSeleccionado() {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(WebElementQuestion.the(SportLinePromociones.TXT_INFO_PRODUCTO), WebElementStateMatchers.containsText("Modelo")));
    }
}
```

### Features
Contiene los escenarios búsqueda de un producto en la página utilizando el lenguaje Gherkin.

##### `SportLine.feature`
Contiene el escenario `outline` de buscar un producto en la página SportLine con unos ejemplos de los productos a digitar, está escrito en lenguaje Gherking. 
```Gherkin
Feature: HU-001 Buscador SportLine
  Yo como usuario de SportLine
  quiero buscar un producto en la plataforma
  para ver el nombre del producto

  Scenario Outline: Buscar producto.
    Given Dado que me encuentro en la pagina de SportLine
    When busque el producto <NombreProducto>
    Then puedo ver <NombreProducto> en pantalla
    Examples:
      |NombreProducto  |
      |Reebok Hiit Tr Dynred |
      |Advanced Trainette|
      |Downshifter 9     |
      |Lebron Witness 4  |
      |Court Borough Low 2|
```

##### `SportLineBusquedaExitosaYFallidaRunner.feature`
Contiene los escenarios exitosos y alternos de buscar un producto en la página SportLine, cada escenario (exitoso y fallido) fue escrito en lenguaje Gherking.
A su vez tiene unos pasos definidos en `Background` son los pasos que se repiten en los dos escenarios.

```Gherkin
Feature: HU-002 Buscador SportLine
  Yo como usuario de SportLine
  quiero buscar un producto
  existente y no existente

  Background:
    Given Dado que me encuentro en la pagina de SportLine

  Scenario: Buscar producto existente
    When busco el nombre de producto existente en la pagina
    Then visualizo detalles del producto existente en pantalla

  Scenario: Buscar producto no existente
    When busco el nombre del producto que no esta en la pagina
    Then visualizo un mensaje de no encontrado
```

##### `SportLineSeccionPromociones.feature`
Contiene los escenarios para veirifcar el funcionamiento de la sección de promociones en la página SportLine, cada escenario fue escrito en lenguaje Gherking.
A su vez tiene unos pasos definidos en `Background` son los pasos que se repiten en los dos escenarios.

```Gherkin
Feature: HU-003 Visualizar promociones SportLine
  Yo como usuario de SportLine
  quiero visualizar las promociones de la pagina SportLine

  Background:
    Given Dado que me encuentro en la pagina

  Scenario: Seleccionar promociones
    When seleccione la seccion promociones de la pagina SportLine
    Then vemos los productos en promocion

  Scenario: seleccionar el primer producto que aparezca en promociones
    When selecciono el primer producto que aparace
    Then visualizo las caracteristicas del producto seleccionado
```
