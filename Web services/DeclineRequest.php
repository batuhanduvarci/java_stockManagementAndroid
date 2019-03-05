<?php
include_once 'Authentication.php';
use user\Authentication;

	$auth = new Authentication();
	$auth->prepareDecline($_POST);
	$auth->declineRequest();

	$json['success'] = 1;
	$json['message'] = 'İstek Reddedildi';

	

echo json_encode($json);