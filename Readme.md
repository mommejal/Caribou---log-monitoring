# Caribou : architecture de monitoring pour micro-services

## Problematique

* Récupérer, centraliser et traiter les logs générés par les micro-services.
Les logs doivent être récupérés dynamiquement par une application web utilisant AJAX. Il s'agit à terme de pouvoir traiter les logs d'exceptions, du garbage collector et enfin des logs de l'OS.


## Etat de l'art

La gestion des logs est un enjeu de plus en plus grand pour les entreprises. Dans le cas du monitoring de micro-services, cela permet d'isoler le plus rapidement possible les problemes au sein de l'application. 

* Stack ELK
  Maintenant appelé Elastic stack, comprend :
  * Elasticsearch, qui permet le sotckage  et l'indexation des requÃªtes. Il est basé sur le moteur de recherche Apache Lucene et a pour principales caractéristiques l'utilisation d'une base de données NoSQL, une forte scalabilité, l'utilisation d'une API REST et des temps de réponse compris entre 20ms et 300ms.
  * Logstash, qui assure l'analyse, le filtrage et le découpage des logs
  * Kibana est un dashboard permettant l'affichage des logs. 

* Splunk




## Architecture

Notre proposition d'architecture est pensée pour être la plus propre et modulable possible avec l'idée de "screaming architecture" en tête. Nous avons fait un schéma UML le plus parlant possible pour que l'organisation de ce projet reste claire. Ainsi, le projet se sépare en plusieurs parties distinctes :

* Gestion des agents :

Caribou peut soutenir plusieurs agents fonctionnant en parallèle et retient la source de chaque log.

* Gestion des logs :

L'application permet d'afficher et de trier les logs selon des expressions régulières entierement choisies par l'utilisateur. Des types de logs peuvent etre préconfigurés mais l'utilisateur peut aussi créer un log personnalisé

* Application web :

Bien que Caribou fonctionne en local pour l'instant, une application web a déjà été codée avec spring MVC pour la partie Controller et Thymeleaf pour la partie de gestion des templates. Différents onglets permettent de gérer les différents paramètres.

* Base de données : 

Les logs sont stockés sous une forme minimale LightLog pour économiser de l'espace dans une base de données de type NoSQL orientée documents MongoDB mais on peut facilement implémenter un autre type de base de données.

