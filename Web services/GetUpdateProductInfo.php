<?php
include_once 'Authentication.php';
use user\Authentication;

	$auth = new Authentication();
	$auth->prepareUpdateProduct($_POST);

	$json = $auth->getUpdateProductInfo();

	echo json_encode($json);