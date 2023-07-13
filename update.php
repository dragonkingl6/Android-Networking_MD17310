<?php
$server ="localhost"; $user="id19897253_dragonkingl6"; $pass= "Duccute@123"; $db="id19897253_android_duc_md17310";

$con= new mysqli($server, $user, $pass, $db);


$sql="update MyGuests set firstname='firstname update'
where id=185";

if($con->query($sql)===true){
    echo "them thanh cong";
}else{
    echo "them that bai";
}

$con->close();
?>