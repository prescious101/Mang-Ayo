<?php
if ($_SERVER['REQUEST_METHOD']=='POST') {
    $db = mysqli_connect('localhost', 'root', "", 'mangayo_db');
    $user_id = trim($_POST['id']);
	$response = mysqli_query($db, "SELECT * FROM vehicle WHERE user_id = $user_id");
    //echo json_encode(array("vehicles" => $row));
    //$rows = $row;
    //echo json_encode($rows); 
    $result = array();
    $result['data'] = array();
    if (mysqli_num_rows($response) > 0){
        while($row = mysqli_fetch_assoc($response)) {
            $index['vehicle_brand'] = $row['vehicle_brand'];
            $index['vehicle_type'] = $row['vehicle_type'];
            $index['plate_num'] = $row['plate_num'];
            $index['vehicle_model'] = $row['vehicle_model'];
            $index['chassis_num'] = $row['chassis_num'];
            $index['fuel_type'] = $row['fuel_type'];
            $index['vehicle_image'] = $row['vehicle_image'];
            array_push($result['data'], $index);
        }
        echo json_encode($result); 
    }else{
        echo json_encode(array("vehicles" => "vehicles not found"));
    }exit();
}
    
?>