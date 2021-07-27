select id,placa,cilindraje,fecha_entrada,tarifa,estado
from mantenimiento
where UPPER(estado)=UPPER('A')