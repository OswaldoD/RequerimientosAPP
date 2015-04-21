<?php
	include("connect.php");
    $email = $_REQUEST["Email"];
    $pais_dest = $_REQUEST["pdst"];
    $nombre_dest = $_REQUEST["ndst"];
    $personas = $_REQUEST["pps"];
    $costo = $_REQUEST["cst"];

	//$sql = "select Nombre, Apellido, Edad from usuario ";
	$sql = "CALL crearReservacion('$email', '$pais_dest', '$nombre_dest', '$personas', '$costo')";
	$datos = array();
	
	$rs = mysql_query($sql, $con) or die('Mi error es: '.mysql_error());  
	
	while($row=mysql_fetch_array($rs)){
		//falta filas de columna
		$datos['id'] = $row['idUsuario'];
		$datos['Nombre'] = $row['Nombre'];
		$datos['correo'] = $row['correo'];
	}
	echo json_encode($datos);
	//http://localhost/turuta/main.php?Edad=dreammicro7@gmail.com
?>
