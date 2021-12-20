Feature: HU-001 Buscador Dekosas
  Yo como usuario en la pagina web Dekosas
  Quiero buscar los productos en la plataforma
  Para ver las caracteristicas de los producto

  Scenario Outline: Buscar productos
    Given que me encuentro en la pagina de ML
    When busque el producto <NombreProducto>
    Then podre ver <NombreProducto> en pantalla
    Examples:
      |Separador De Yemas Greenf - DKS Worldwide|
      |Escritorio Repisa - Madera Natural|
      |Correa Para Mascota - Marvel|


