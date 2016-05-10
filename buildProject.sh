#!/usr/bin/env bash

# Définition couleur des messages du script

    ROUGE="\\033[31m"
    VERT="\\033[1;32m"
    JAUNE="\\033[1;33m"
    NORMAL="\\033[0m"

    set -a

# Netoyage des anciennes versions de Maven

    echo ""
    echo "---- Nettoyage de la version précedente de maeven---"
    mvn clean
    echo ""
    echo -e  "[${VERT} Project nettoyé : [OK] ${NORMAL}]"