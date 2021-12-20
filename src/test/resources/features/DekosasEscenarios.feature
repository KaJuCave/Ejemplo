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