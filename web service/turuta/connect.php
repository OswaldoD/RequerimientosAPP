<?php
	include("connection.php");

	$con  = mysql_connect($server, $username, $password) or die("Sin conexion");
	mysql_select_db($database);




?>