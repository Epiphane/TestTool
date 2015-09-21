#!/bin/bash

set -e

function getNode() {
    case $OSTYPE in
        darwin*)
            echo "Installing brew and node"
            command -v brew &> /dev/null || ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
            brew install node
            ;;
        *)
            echo "Please install npm and retry"
            exit 1
    esac
}

command -v npm &> /dev/null || getNode

echo "Installing bower"
npm install -g bower

echo "Installing gulp"
npm install -g gulp

echo "Installing dependencies"
npm install -g
bower install && npm install

echo 'Now run gulp and you should be good to go!'
