<?php

    $db = mysqli_connect('localhost', 'root', "", 'mangayo_db'); $ok;

	$result = mysqli_query($db, "SELECT * FROM mechanic_location");

    if (mysqli_num_rows($result) > 0){
        while($row = mysqli_fetch_assoc($result)) {
            $rows[] = $row;
        }
         echo json_encode($rows); 

    }else{
         echo json_encode(array("transactions" => "No Transaction(s) found"));
    }exit();

?>