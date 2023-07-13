<?php
header('Access-Control-Allow-Origin: *');
$server ="localhost"; $user="id19897253_dragonkingl6"; $pass= "Duccute@123"; $db="id19897253_android_duc_md17310";

$con= new mysqli($server, $user, $pass, $db);


$sql="select * from MyGuests";
$result = $con->query($sql);
while( $row[]=  $result->fetch_assoc()){
    $json=json_encode(($row));
}
echo '"MyGuests":{'.$json.'}';
$con->close();
?>