<?php
    $db = mysqli_connect('localhost', 'root', "", 'mangayo_db');
	$result = mysqli_query($db, "SELECT * FROM vehicle");
    //echo json_encode(array("vehicles" => $row));
    //$rows = $row;
    //echo json_encode($rows); 

    if (mysqli_num_rows($result) > 0){
        while($row = mysqli_fetch_assoc($result)) {
            $rows[] = $row;
        }
        echo json_encode($rows); 
    }else{
        echo json_encode(array("vehicles" => "vehicles not found"));
    }exit();

    
?>