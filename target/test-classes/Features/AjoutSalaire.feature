Feature: Ajouter un salaire

  Background:
    Given utilisateur est sur la page d'accueil

  Scenario Outline: Ajouter un salaire

    Given utilisateur est connecté avec "harhourimarwa97@gmail.com" et "Marwa@2026!"
    And   utilisateur choisit l option  "College"
    When  utilisateur clique sur le menu "Administration"
    And   utilisateur clique sur le sous-menu "Finances"
    And   utilisateur clique sur le sous-sous-menu "Charges"
    And   utilisateur clique sur le sous-sous-sous-menu "Salaires"
    And   utilisateur clique sur le bouton "Ajouter un salaire"
    And   utilisateur choisit le statut "<statut>"
    And   utilisateur choisit le nom "<nom>"
    And   utilisateur saisit la date "<date>"
    And   utilisateur saisit le salaire "<salaire>"
    And   utilisateur saisit les frais "<frais>"
    And   utilisateur clique sur le bouton "Ajouter"
    Then  le salaire est ajouté avec succès

  Examples:
    | nom            | statut | date       | salaire | frais |
    | Harhouri Marwa | Neutre | 02/02/2026 | 2500    | 10    |