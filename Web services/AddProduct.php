<?php

include_once 'Authentication.php';
use user\Authentication;

	$auth = new Authentication();
	$auth->prepareProduct($_POST);
	$productStatus = $auth->addProductControl();

	if($productStatus){
		$auth->addProduct();
		$json['success'] = 1;
		$json['message'] = 'Ürün ekleme işlemi tamamlandı';

	}else{
		$json['success'] = 0;
		$json['message'] = 'Ürün kodu kullanılmakta !';
	}

	echo json_encode($json);