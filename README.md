# ncfProject

## Pointeuse Etudiants

Groupe : Tom Phily, Marina Helie-Zadeh, Stephanie Carrié

Conception d'une pointeuse pour les étudiants dans le cadre d'un projet scolaire sur le nfc.

## Mise en place de la base de données :

Configuration requise : avoir les variables d'environement pour executer les commandes mysql et sh.

Executer en ligne de comande sh DataBuild.sh

## Lancement du serveur jetty sous eclipse

Configurer ecplipse : Allez dans Window -> preference -> java -> jre installer -> edit jre par default -> directory -> selectionner jdk au lieu de jre.

Configurer maven et lancer le serveur : Click droit sur le projet -> run as -> run configuration -> nouvelle configuration maven -> mettre dans goal "clean install jetty:run" et dans base directory le repertoire du projet -> run.

## Lancement du serveur nodeJS

Allez dans ClientNodeJs, ouvrir un terminal, executer "npm istall", puis "node server.js"