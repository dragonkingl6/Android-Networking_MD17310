<?php
$server ="localhost"; $user="id19897253_dragonkingl6"; $pass= "Duccute@123"; $db="id19897253_android_duc_md17310";

$con= new mysqli($server, $user, $pass, $db);


$sql="insert into MyGuests (firstname, lastname, email)
values ('firstname2','lastname1','email11')";

if($con->query($sql)===true){
    echo "them thanh cong roi nha12";
}else{
    echo "them that bai".$con->error;
}

$con->close();
?>