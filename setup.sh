#!/bin/bash

brew install node

echo "Installing bower"
npm install -g bower

echo "Installing gulp"
npm install -g gulp

echo "Installing despendencies"
npm install -g
bower install && npm install

echo "Now run gulp and you should be good to go\!"