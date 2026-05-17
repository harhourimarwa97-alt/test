Feature: Consulter detail d'un  salaire 

Background: 
  
    Given utilisateur est sur la page d'accueil
    And   utilisateur est connecté avec "harhourimarwa97@gmail.com" et "Marwa@2026!"
    And   utilisateur choisit l option  "College"
    When  utilisateur clique sur le menu "Administration"
    And   utilisateur clique sur le sous-menu "Finances"
    And   utilisateur clique sur le sous-sous-menu "Charges"
    And   utilisateur clique sur le sous-sous-sous-menu "Salaires"

Scenario: Voir detail d'un salaire
 
 When est accede a la page de liste des salaires
 
 And  il clique sur l'icone de detail d'un salaire "baragoui Aycha"
 
 Then le detail du salaire est affiche
 


