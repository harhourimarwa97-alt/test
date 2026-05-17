Feature:  Modifier un salaire

Background: 
  
    Given utilisateur est sur la page d'accueil
    And   utilisateur est connecté avec "harhourimarwa97@gmail.com" et "Marwa@2026!"
    And   utilisateur choisit l option  "College"
    When  utilisateur clique sur le menu "Administration"
    And   utilisateur clique sur le sous-menu "Finances"
    And   utilisateur clique sur le sous-sous-menu "Charges"
    And   utilisateur clique sur le sous-sous-sous-menu "Salaires"
    

Scenario: Modifier  un salaire permanent
  Given je suis sur la page de consultation des salaires

  When je cherche une ligne avec le type "Permanent"

  Then l’icone de modification est visible

  When je clique sur l’icone de modification

  Then la page de modification du salaire s’ouvre
  
  
  