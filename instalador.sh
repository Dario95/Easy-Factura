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
� �/~X �YKoG��Y+�:N���Bl'�$��(�
dJrS��rF4�-���43�n{�?��-`�^ �}�8��������g(R�d�X+A�<��ꯪ���yX�q��{ڱ5�q��ػ��3ޫV.�.�e�\�J�Z��[��\��3i�R�?BSz��#y��Q�/���L��v[�v��=]��{!$"I	m��M����D��e�g!�1�<�s�'_ư)���A( J��@첈��]�Jv�a���z6�)��jx����$��/dh��SCK�#I�o'��͡��U6+Yr�(�x^y�.�R?a�$"ٍX��d>�r X,�{bS��q?��@$_�Oh�sHX�xDt?Hw8Y)���#
���/Ci��dF4�� 삄x���G����A�u�>t��>:�G�DB(��vP��(ni�=<,tpU@Lld"�UEf&H��A@�4��&C?r\ �I �� ,�r"�4�T� @qn8���ŘB2m��OK�/��i�F$D���d����G#+�D����0�,��C6����ȵ�E5�0m��2o@ ڣ�HZ3���A���#�<�K�_��!
}4	7d��|,r-D"!D3�ߓE}2]�<���T��@�6{(�q���2���?��N����`�Ǐ9��0�v>��9�J��r���o�?	�k<ꋐ��\_f}��}a����l��I��8�c�)�}L���� j,"\��{�f|�Ҙ�*17(#������H�Y�Ġ�{���Hv�{����d	�b�qk���=J�dg�0;;�lDXl��������i�hq�d[!K�+Iq���s�S������A��{}فZ�v$��H����b���2��fʨ.���k����|��=�@m�O�}����q���[:�JdA%�U��(�xQ��o�?ӶW)Ǩ��?�����ϩ֪ez��x������`��f~�(����xw�ns��+��V��l�n�UӶ�=�6����\�^X�
p��$�i���ڲ�ʖ�����zm��IW��V'�\��A�<d퀛m�?�F2;�bȷ��?|~���~��_O�~����⸱t%��M��(�[�NM�-���f�;
�6��F����0bdZ.2����A�n�^��lq�ƀ%=@ۖ\�l����j`���S�盎U3�j�y_�<�])��PE*�/Y%�QS�Q�(�Ĩ��<��8B�9B�p�jT���s�Y����Iz��|Ǭ �G��u�7��4F�4a���z�r�H�At��~=�#p��{|4�����1��u�����)x3���n�NŪ�xJ�V�@�2�����52G�(S6)"R���@�T'ڌr��2H�Ȧ��Bu���&����̪q�KDW�!:���q e�.-�]e���ZV|9Vih2f ^��%���b�(z*���2J*�STϦ���V��r�竳�P�ʤ�L�^��Qs�8N�� ܪJ~W�Zc���Pm���)��(Fy�zt ��Rxk*;�h2r�w�M]cƸH(��P$�/A7!CJff�^R}�jx�ԫ^�תQ�����!�1��������͜�����S�sf[^�����-L@r�2n[]�^��XYf%���T~�����X�J�3�)����T�U�g�AǺ�zT�ާ��ecC%PV�O)�ɜ���XW�G���J��6-5I«u�R�"����{���\��W_��j��z��gL����a������� �tW����QN��_8_��#��K�����[s<��[u���?J[�s��;_д/�ݏ�g^��hKks���E��o�E����������bt\�cOtin����ꚵ���B����̿OM��3�υc#s����c89e�&t�����ys��k���f�q��݈����2R_>��|���\�-r&�O��ݥ���iXb"4�,l��m���`i"���gA�m����3��LCO��/$��ϖ�lB��狾@�-���}s��?^8��x��On��I����k�/k�ӵ����+�����y����o
ڹ"�lA;}��zA;Ӕ^�^k��/��6���u��]nI�m�E����3IO����gp����7�=h��@;`a�^M"vg�2uq��L4}U���)zu��^��i�Q�FA��޾��V{����]i��~�����EC�v�����(�+,Dm��{r�)���d��]��ɶ�)��a2�zv��m�]L�*���Y��5�ƾ�������Rӯ��958��}1�~�gT/ګ���x.���v��h��=�y����v	{�FI^^�S8u��ᅙ�K������t���B�����C��y�G��H�S؟���=��#͟�S+wΞ#�3��_�8}��u�����}�:�~X�T��������ox�/yG��qF�~�?i'���r�� J��v $  