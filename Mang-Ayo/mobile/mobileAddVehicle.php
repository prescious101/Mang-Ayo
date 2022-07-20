<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
	$db = mysqli_connect('localhost','root','','mangayo_db');$ok;
	$date = date("dmYHis");
	$vehicle_brand = trim($_POST['brand']);
	$vehicle_model = trim($_POST['model']);
	$vehicle_image = trim($_POST['image']);
	$fuel_type = trim($_POST['fuel']);
	$path = "uploads/IMG_$date.png";
	$actualpath = "http://10.0.2.2/mobile/$path";

	$ok = mysqli_query($db, "INSERT INTO vehicle (vehicle_image, vehicle_brand, vehicle_model, fuel_type) 
		VALUES ('$actualpath', '$vehicle_brand', '$vehicle_model', '$fuel_type')");

	if($ok){
		file_put_contents($path,base64_decode($vehicle_image));
		echo $message = "success";
	} else {
		echo $message = "failed";
	}
	}

?>