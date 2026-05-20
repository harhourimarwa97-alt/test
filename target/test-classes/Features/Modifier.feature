Feature:  Modifier un salaire

Background: 
  
    Given utilisateur est sur la page d'accueil
    And   utilisateur est connecté avec "harhourimarwa97@gmail.com" et "Marwa@2026!"
    And   utilisateur choisit l option  "College"
    When  utilisateur clique sur le menu "Administration"
    And   utilisateur clique sur le sous-menu "Finances"
    And   utilisateur clique sur le sous-sous-menu "Charges"
    And   utilisateur clique sur le sous-sous-sous-menu "Salaires"
    

 

  Scenario Outline: Modifier le montant d un salaire de type Permanent en statut En cours

Given un salaire de nom "<nom>" de type "<type>" et de statut "<statut>" est visible dans la liste
When  l'utilisateur clique sur l'icône de modification de ce salaire
And   l'utilisateur efface le montant actuel et saisit "<nouveau_montant>"
And   l'utilisateur clique sur le bouton "Modifier"

Then  un message de succès "Salaire modifié" est affiché
And l'utilisateur ferme le popup de succès


Examples:
  | nom             | type      | statut   | nouveau_montant  |
  | Harhouri Marwa  | Permanent | En cours | 3000.00          |
  
  
  Scenario Outline: Modifier le montant d un salaire avec une valeur tres élevée
  Given un salaire de nom "<nom>" de type "<type>" et de statut "<statut>" est visible dans la liste
  When  l'utilisateur clique sur l'icône de modification de ce salaire
  And   l'utilisateur efface le montant actuel et saisit "<nouveau_montant>"
  And   l'utilisateur clique sur le bouton "Modifier"

  Then  un message d'erreur "Montant trop élevé" est affiché
  And l'utilisateur ferme le popup d'erreur
  
  Examples:
    | nom             | type      | statut   | nouveau_montant  |
    | Nidhal Jlassi   | Permanent  | En cours | 1000000.00      |
    
    
    Scenario Outline: Cliquer sur bouton Annuler lors de la modification d un salaire
    Given un salaire de nom "<nom>" de type "<type>" et de statut "<statut>" est visible dans la liste
    When  l'utilisateur clique sur l'icône de modification de ce salaire
    And   l'utilisateur efface le montant actuel et saisit "<nouveau_montant>"
    And   l'utilisateur clique sur le bouton "Annuler"

    Then  le montant du salaire reste inchangé
    And l'utilisateur est redirigé vers la liste des salaires
   Examples:
   	    | nom             | type       | statut   | nouveau_montant  |
        | Harhouri Marwa  | Permanent  | En cours | 3500.00          |


    
    
    
    
    
    
    
