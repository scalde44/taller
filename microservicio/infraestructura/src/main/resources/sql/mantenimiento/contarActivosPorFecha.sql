select count(id)
from mantenimiento
where 
EXTRACT(YEAR FROM fecha_entrada)= EXTRACT(YEAR FROM :fecha)
and EXTRACT(MONTH FROM fecha_entrada)= EXTRACT(MONTH FROM :fecha)
and EXTRACT(DAY FROM fecha_entrada)= EXTRACT(DAY FROM :fecha)
and UPPER(estado)=UPPER('A')