<?php

	//$con  = mysql_connect("localhost", "root", "") or die("Sin conexion");
	//mysql_select_db("webservice");

	include("connect.php");
    
    //$edad = $_REQUEST["Edad"];
    //$edad .= "@gmail.com";

	$sql = "select Nombre, Apellido, Edad from usuario ";
	//$sql = "CALL buscaUser('$edad')";
	$datos = array();
	
	$rs = mysql_query($sql, $con);  
	
	while($row=mysql_fetch_object($rs)){
		//echo $row;+
		$datos[] = $row;
	}
	//
	echo json_encode($datos);
//echo $edad;

/*

		$data = array('nombre' => 'Oswaldo', 'blog'=> 'Fk reque');
		print (json_encode($data));*/
?>
