<?php
include_once 'Authentication.php';
use user\Authentication;

	$auth = new Authentication();
	$auth->prepareDeleteProduct($_POST);
	$deleteProduct = $auth->deleteProduct();
	$productStatus = $auth->deleteProductControl();

	if($productStatus){
		$json['success'] = 1;
		$json['message'] = 'Ürün silme işlemi tamamlandı';
	}else{
		$json['success'] = 0;
		$json['message'] = 'Ürün silerken hata oluştu!';
	}

echo json_encode($json);