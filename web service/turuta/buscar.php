<?php
	include("connect.php");
    
    $destino = $_REQUEST["dst"];
    $paquete = $_REQUEST["pqt"];
    $oferta = $_REQUEST["off"];
    $tipobusqueda = $_REQUEST["tbsq"]; //destino es 0, oferta es 1, paquete es 2
    $lugar = $_REQUEST["lgr"];// local o internacional
    
	//$sql = "select Nombre, Apellido, Edad from usuario ";
	$sql = "CALL buscar('$destino', '$paquete', '$oferta', '$tipobusqueda', '$lugar')";
	$datos = array();
	
	$rs = mysql_query($sql, $con) or die('Mi error es: '.mysql_error());  
	
	while($row=mysql_fetch_array($rs)){
		//faltan las columnas
		
		$datos['id'] = $row['idUsuario'];
		$datos['Nombre'] = $row['Nombre'];
		$datos['correo'] = $row['correo'];

	}
	echo json_encode($datos);

	//http://localhost/turuta/buscar.php?dst=peru&pqt=1

?>
