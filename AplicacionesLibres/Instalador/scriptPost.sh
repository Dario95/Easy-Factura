#!/bin/bash
#################
# Change this values
#################

psqluser="appfacturacion"   # Database username
psqlpass="facturacion01"  # Database password
psqldb="facturas"   # Database name

#################
# Dependicies
#################
sudo apt-get update
sudo apt-get install postgresql postgresql-contrib

#################
# Database
#################

sudo -u postgres createuser -s $psqluser
sudo -u postgres psql -c "ALTER USER $psqluser WITH PASSWORD '$psqlpass';"
sudo -u postgres createdb $psqldb -O $psqluser
sudo -u postgres psql -c "GRANT ALL PRIVILEGES ON DATABASE $psqldb TO $psqluser;"
wget https://raw.githubusercontent.com/Dario95/NetBeansProjects/master/AplicacionesLibres/bdd/Facturacion.sql
sudo -u postgres psql -d $psqldb -f Facturacion.sql

exit
