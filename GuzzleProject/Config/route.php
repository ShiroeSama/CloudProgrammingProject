<?php
	
	/**
	 * --------------------------------------------------------------------------
	 *   @Copyright : License MIT 2017
	 *
	 *   @Author : Alexandre Caillot
	 *   @WebSite : https://www.shiros.fr
	 *
	 *   @File : route.php
	 *   @Created_at : 02/12/2017
	 *   @Update_at : 02/12/2017
	 * --------------------------------------------------------------------------
	 */
	
	$Route = [
	
		/*
		|--------------------------------------------------------------------------
		| Root Folder
		|--------------------------------------------------------------------------
		|
		| Racine du dossier des Controlleurs.
		|
		*/
	
		'ROOT_FOLDER' => 'App/Controller',
	
	
		/*
		|--------------------------------------------------------------------------
		| Routing
		|--------------------------------------------------------------------------
		|
		| Définition des routes pour l'acces au page
		|
		*/
		
		'ROUTES' => [
			'Homepage' => [
				'Rule'   => '/',
				'Action' => 'HomeController.index',
			],
			
			'Account' => [
				'Rule'   => '/account',
				'Action' => 'AccountController.index',
			],
			
			'Account-Add' => [
				'Rule'   => '/account/add',
				'Action' => 'AccountController.add',
			],
			
			'Account-Delete' => [
				'Rule'   => '/account/delete/:id',
				'Action' => 'AccountController.delete',
			],
			
			'Approval' => [
				'Rule'   => '/approval',
				'Action' => 'ApprovalController.index',
			],
			
			'Approval-Delete' => [
				'Rule'   => '/approval/delete/:id',
				'Action' => 'ApprovalController.delete',
			],
			
			'Loan' => [
				'Rule'   => '/loan',
				'Action' => 'LoanController.index',
			],
		]
	
	
	/*
	|--------------------------------------------------------------------------
	| End Config
	|--------------------------------------------------------------------------
	|
	*/
	
	];

	return $Route;
?>