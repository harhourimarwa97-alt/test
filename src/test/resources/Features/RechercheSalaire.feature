Feature: Recherche un salaire

  Background:
    Given utilisateur est sur la page d'accueil
    And   utilisateur est connecté avec "harhourimarwa97@gmail.com" et "Marwa@2026!"
    And   utilisateur choisit l option  "College"
    When  utilisateur clique sur le menu "Administration"
    And   utilisateur clique sur le sous-menu "Finances"
    And   utilisateur clique sur le sous-sous-menu "Charges"
    And   utilisateur clique sur le sous-sous-sous-menu "Salaires"

  Scenario Outline: Rechercher un salaire existe dans la liste
    When  utilisateur saisit "<recherche>" dans le champ recherche
    Then  les résultats affichés contiennent "<recherche>"

    Examples:
      | recherche      |
      | Harhouri Marwa |