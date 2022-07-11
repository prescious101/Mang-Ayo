<?php

	$db = mysqli_connect('localhost','root','','mangayo_db');$ok;

	
	$service_id = trim($_GET['service_id']);
	$vehicle_id = trim($_GET['vehicle_id']);
	$mechanic_id = trim($_GET['mechanic_id']);
	$user_id = trim($_GET['user_id']);
	$booking_status = trim($_GET['booking_status']);
	$latitude = trim($_GET['latitude']);
	$longitude = trim($_GET['longitude']);

	$ok = mysqli_query($db, "INSERT INTO booking (service_id, mechanic_id, user_id, vehicle_id, booking_status, longitude, latitude) 
			VALUES ( '$service_id', '$mechanic_id', '$user_id', '$vehicle_id', '$booking_status', '$longitude', '$latitude') ");
	
	$message = ($ok)?"SUCCESSFULLY ADDED BOOKING":"FAILED TO ADD BOOKING";

	$ch = curl_init('https://textbelt.com/text');
    $data = array(
      'phone' => '+639950925464',
      'message' => 'You have a Customer!',
      'key' => 'textbelt',
    );

    curl_setopt($ch, CURLOPT_POST, 1);
    curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($data));
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    $response = curl_exec($ch);
    curl_close($ch);

	echo $message;
	



?>