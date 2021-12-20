package uis;
import net.serenitybdd.screenplay.targets.Target;
public class DekosasPage {

        public static final Target TXT_BUSCADOR = Target.the("").locatedBy("//input[@id='search' and @name='q']");
        public static final Target BTN_BUSCADOR = Target.the("").locatedBy("//button[@class='amsearch-loupe' and @title='Buscar']");
        public static final Target BTN_ELEMENTO_BUSQUEDA = Target.the("").locatedBy("//a[contains(text(),'{0}')]");
        public static final Target TXT_ELEMENTO_BUSQUEDA = Target.the("").locatedBy("//span[contains(text(),'{0}')]");
        public static final Target BTN_CARRITO = Target.the("").locatedBy("//button[@class='action primary tocart' and @title='Agregar al Carrito']");
        public static final Target LB_CARRITO = Target.the("").locatedBy("//div[@data-bind='html: message.text']");
        public static final Target BTN_MUEBLES = Target.the("").locatedBy("//a[@class='level-top' and @href='https://dekosas.com/co/muebles-accesorios']");


}
