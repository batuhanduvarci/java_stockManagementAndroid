<?php

include_once 'Authentication.php';
use user\Authentication;

	$auth = new Authentication();
	$auth->prepareOrder($_POST);

	$auth->orderProduct();

	$json['success'] = 1;
	$json['message'] = 'Sipariş Verildi';

	echo json_encode($json);