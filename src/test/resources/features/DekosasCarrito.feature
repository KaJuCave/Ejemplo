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