<?php
	include("connect.php");
    
    $email = $_REQUEST["Email"];

	//$sql = "select Nombre, Apellido, Edad from usuario ";
	$sql = "CALL listarReservaciones('$email')";
	$datos = array();
	
	$rs = mysql_query($sql, $con) or die('Mi error es: '.mysql_error());  
	
	while($row=mysql_fetch_array($rs)){
		//faltan las columnas
		$datos['id'] = $row['idUsuario'];
		$datos['Nombre'] = $row['Nombre'];
		$datos['correo'] = $row['correo'];

	}
	echo json_encode($datos);

	//http://localhost/turuta/vreservaciones.php?Email=dreammicro7@gmail.com
?>
