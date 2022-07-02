<?php
	$db = mysqli_connect('localhost','root','','mangayo_db');$ok;
	
	$insertName = trim($_GET['insertName']);
	$insertDescription = trim($_GET['insertDescription']);
	$insertCost = trim($_GET['insertCost']);

	$ok = mysqli_query($db, "INSERT INTO service (service_name, description, service_cost) VALUES ('$insertName',
		'$insertDescription','$insertCost')");

	$message = ($ok)?"BOOKED MECHANIC":"FAILED TO BOOK MECHANIC";

	$ch = curl_init('https://textbelt.com/text');
    $data = array(
      'phone' => '+639950925464',
      'message' => 'BOOKING REQUEST',
      'key' => 'textbelt',
    );

    curl_setopt($ch, CURLOPT_POST, 1);
    curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($data));
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    $response = curl_exec($ch);
    curl_close($ch);
    // Process your =>  echo $response;

	echo $message;


?>				