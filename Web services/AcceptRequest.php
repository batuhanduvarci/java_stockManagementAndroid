<?php
include_once 'Authentication.php';
use user\Authentication;

	$auth = new Authentication();
	$auth->prepareAccept($_POST);
	$deleteOrder = $auth->acceptRequest();

	$json['success'] = 1;
	$json['message'] = 'İstek Onaylandı';

	

echo json_encode($json);