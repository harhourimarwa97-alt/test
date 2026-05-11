Feature: Authentification utilisateur

Scenario: Connexion réussie avec des identifiants valides

Given utilisateur est sur la page de connexion
When il saisit un email valide "harhourimarwa97@gmail.com" et un mot de passe valide "Marwa@2026!"
And il clique sur option "college"
Then il est redirigé vers le tableau de bord qui contient l option "college"


