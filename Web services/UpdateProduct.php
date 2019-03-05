<?php
include_once 'Authentication.php';
use user\Authentication;

	$auth = new Authentication();
	$auth->prepareProduct($_POST);

	$auth->updateProduct();

	$json['success'] = 1;
	$json['message'] = 'Güncelleme işlemi tamamlandı';

	echo json_encode($json);