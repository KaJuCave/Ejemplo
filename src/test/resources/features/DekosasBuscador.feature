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




