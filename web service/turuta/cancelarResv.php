<?php
	include("connect.php");
    $email = $_REQUEST["Email"];
    $pais_dest = $_REQUEST["pdst"];
    $nombre_dest = $_REQUEST["ndst"];
    $fecha_creac = $_REQUEST["fcrc"];

	//$sql = "select Nombre, Apellido, Edad from usuario ";
	$sql = "CALL cancelarReservacion('$email', '$pais_dest', '$nombre_dest', '$fecha_creac')";
	$datos = array();
	
	$rs = mysql_query($sql, $con) or die('Mi error es: '.mysql_error());  
	
	while($row=mysql_fetch_array($rs)){
		//faltan las columnas, es cancelacion hay que manejarlo diferente
		$datos['id'] = $row['idUsuario'];
		$datos['Nombre'] = $row['Nombre'];
		$datos['correo'] = $row['correo'];
	}
	echo json_encode($datos);
	//http://localhost/turuta/main.php?Edad=dreammicro7@gmail.com
?>
