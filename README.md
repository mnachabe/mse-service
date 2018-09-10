# City Street Explorer Service
The City Street Explorer is a routing problem that was suggested in my dissertation at the University of Edinburgh. Given a street network the task is to find a collection of routes, from a set source to a set destination and under a distance constraint, that cover all reachable streets of this network.   

This repo contains Java parsers for OpenStreetMap (OSM). The project is presented as an API that has unfortunately not been deployed yet. The main functionality is parsing data received by the Overpass API to a graph. A custom Graph implementation is provided here as well to fit the problem requirements. Some routing algorithms (conventional and unconventional) have been provided as well with adapters that work with MapBox. 
This project provides implementations that have been made from scratch, and it has not undergone enough testing to be used on a live production system. 

More complex routing algorithm that solve the City Street Explorer Problem have been implemented in python. Please refer to the repo main page. 
