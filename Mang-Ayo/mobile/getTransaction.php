<?php

	$db = mysqli_connect('localhost', 'root', "", 'mangayo_db'); $ok;
	
	$mechanic_id = trim($_GET['mechanic_id']);
	$user_id = trim($_GET['user_id']);

	$result = mysqli_query($db, "SELECT * FROM service WHERE mechanic_id = '$mechanic_id' AND user_id ='user_id'");

    if (mysqli_num_rows($result) > 0){
        while($row = mysqli_fetch_assoc($result)) {
            $rows[] = $row;
        }
         echo json_encode(array("transaction" => $rows)); 

    }else{
         echo json_encode(array("transaction" => "No Transaction(s) found"));
    }exit();

?>