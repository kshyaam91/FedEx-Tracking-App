<?php
//$con = mysql_connect("localhost","root","student");
mysql_connect("localhost","dk47","dk47");
mysql_select_db("packetdata");

//$Shipping_id='51';
$Shipping_id=$_POST["trackid"];


$result=mysql_query("SELECT * FROM packdb WHERE id ='$Shipping_id'");

if (mysql_num_rows($result) == 0) {
    echo "Id doesn't exist";
    exit;
}

while ($row = mysql_fetch_assoc($result)) {


print($row["sCity"]."-".$row["dCity"]."-".$row["presCity"]."-".$row["status"]."-".$row["startDate"]."-".$row["endDate"]."-"."EOF");
}

mysql_close();
?>
