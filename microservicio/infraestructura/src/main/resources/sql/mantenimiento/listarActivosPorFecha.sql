select id,placa,cilindraje,fecha_entrada,tarifa,estado
from mantenimiento
where date(fecha_entrada)= date(:fecha) and UPPER(estado)=UPPER('A')