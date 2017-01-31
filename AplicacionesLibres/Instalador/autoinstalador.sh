#!/bin/sh
echo ""
echo "AutoInstalador Facturacion Aplicaciones Libres"
echo ""

# Creamos un directorio temporal donde lo extraeremos, aquí no tocamos nada.
export WRKDIR=`mktemp -d /tmp/selfextract.XXXXXX`

#Cambiamos el nombre exacto de nuestro archivo comprimido tar.gz,  que podemos ver en negrilla

SKIP=`awk '/^facturacionLibres.tar.gz/ { print NR + 1; exit 0; }' $0`


tail -n +$SKIP $0 | tar xvz -C $WRKDIR


PREV=`pwd`
cd $WRKDIR
sh install.sh

cd $PREV
rm -rf $WRKDIR

exit 0



# volvemos agregar el nombre exacto de nuestro archivo comprimido tar.gz

facturacionLibres.tar.gz
