#!/bin/bash
docker build -t plants-react-forntend .
docker run -p 3000:3000 plants-react-forntend