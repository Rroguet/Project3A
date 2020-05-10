# Application Android : Galaxies les plus connues
Cette application présente les 15 galaxies les plus connues et indique une description de chacune.

---

## L'application possède les fonctionnalités suivantes:
* les galaxies sont présentées sous forme de liste

<img src="https://github.com/Rroguet/Project3A/blob/master/screen/list.PNG" width="200">

* cliquer sur un élément permet de visualiser le détail de celui-ci. Un bouton de retour à la liste est présent sur le détail 

<img src="https://github.com/Rroguet/Project3A/blob/master/screen/detail.PNG" width="200">

* l'application utilise une api située dans le projet git (api créée à partir des informations trouvées sur wikipedia)

[Lien vers l'api](https://github.com/Rroguet/Project3A/blob/master/galaxies.json)

* lors de la première utilisation les données des galaxies sont stockées dans le cache

<img src="https://github.com/Rroguet/Project3A/blob/master/screen/save.PNG" width="200">

* l'application peut recevoir des notifications push par firebase 
(le projet présent sur git ne contient pas le ficher google-services.json car celui-ce contient des informations personnelles)

<img src="https://github.com/Rroguet/Project3A/blob/master/screen/notification.PNG" width="200">

---

Autre information
--

* l'application possède une icone et un nom

<img src="https://github.com/Rroguet/Project3A/blob/master/screen/icon.PNG" width="200">

* les principes d'architecture MVC, SOLID, design patterns et Singleton ont été utilisés
* du gitflow a été utilisé c'est à dire plusieurs branches ont été créées puis ont été merge sur la branch master
* l'application a un design en adéquation avec les galaxies
