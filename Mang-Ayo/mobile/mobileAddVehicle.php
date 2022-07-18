<?php

	$db = mysqli_connect('localhost','root','','mangayo_db');$ok;
	$t=time();
	$date = date("Y_m_d",$t);
	$vehicle_brand = trim($_GET['brand']);
	$vehicle_model = trim($_GET['model']);
	$vehicle_image = trim($_GET['vehicle_image']);
	$fuel_type = trim($_GET['fuel']);
	$name= "sample";
	$path = "uploads/$name.png";
	$actualpath = "http://10.0.2.2/mobile/$path";

	$ok = mysqli_query($db, "INSERT INTO vehicle (vehicle_brand, vehicle_model, fuel_type) 
		VALUES ('$vehicle_brand', '$vehicle_model', '$fuel_type')");

	$message = ($ok)?"SUCCESSFULLY ADDED":"ADDING DATA FAILED";
	// if($ok){
	// 	file_put_contents($path,base64_decode($vehicle_image));
	// 	$message = $ok;
	// } else {
	// 	$message = $ok;
	// }
	echo $message;



?>