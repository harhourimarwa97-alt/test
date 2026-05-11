Feature: Home Page Erudaxis

  Background:
    Given utilisateur est sur la page d'accueil

  Scenario Outline: Navigation menus

    Given utilisateur est connecté avec "harhourimarwa97@gmail.com" et "Marwa@2026!"
    And   utilisateur choisit l'option "College"
    When  utilisateur clique sur le menu "<menu>"
    And   utilisateur clique sur le sous-menu "<submenu>"
    And   utilisateur clique sur le sous-sous-menu "<subsubmenu>"
    And   utilisateur clique sur le sous-sous-sous-menu "<subsubsubmenu>"
    Then  le titre de la page est "<title>"

  Examples:
    | menu           | submenu  | subsubmenu | subsubsubmenu | title              |
    | Administration | Finances | Charges    | Salaires      | Liste des salaires |
    
    
  