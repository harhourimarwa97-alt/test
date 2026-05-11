Feature: Suppression d un salaire

  Background:
    Given utilisateur est sur la page d'accueil
    And   utilisateur est connecté avec "harhourimarwa97@gmail.com" et "Marwa@2026!"
    And   utilisateur choisit l option  "College"
    When  utilisateur clique sur le menu "Administration"
    And   utilisateur clique sur le sous-menu "Finances"
    And   utilisateur clique sur le sous-sous-menu "Charges"
    And   utilisateur clique sur le sous-sous-sous-menu "Salaires"

  Scenario Outline: Supprimer un salaire existant avec confirmation
    When  utilisateur parcourt la liste et clique sur supprimer du salaire "<NOM>"
    Then  une popup de confirmation s affiche avec le message "<MSG_CONFIRMATION>"
    When  utilisateur confirme la suppression
    Then  le salaire de "<NOM>" n existe plus dans la liste

    Examples:
      | NOM            | MSG_CONFIRMATION |
      | Harhouri Marwa | Êtes-vous sûr(e) |
      
   Scenario Outline: Supprimer un salaire existant avec annulation
   
       When  utilisateur parcourt la liste et clique sur supprimer du salaire "<NOM>"        Then  une popup de confirmation s affiche avec le message "<MSG_CONFIRMATION>"        When  utilisateur annule la suppression        Then  le salaire de "<NOM>" existe toujours dans la liste            Examples:        | NOM            | MSG_CONFIRMATION |        | Harhouri Marwa | Êtes-vous sûr(e) |