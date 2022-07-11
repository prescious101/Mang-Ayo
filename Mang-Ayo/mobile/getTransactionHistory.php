<?php

	$userID = $_GET["userID"];

    $db = mysqli_connect('localhost', 'root', "", 'mangayo_db');$ok;
	$result = mysqli_query($db, "SELECT * FROM transaction WHERE user_id ='$userID'");

    if (mysqli_num_rows($result) > 0){
        while($row = mysqli_fetch_assoc($result)) {
            $rows[] = $row;
        }
         echo json_encode(array("transaction" => $rows)); 

    }else{
         echo json_encode(array("transactions" => "No Transaction(s) found"));
    }exit();

?>