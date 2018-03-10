<?php
	
	/**
	 * --------------------------------------------------------------------------
	 *   @Copyright : License MIT 2017
	 *
	 *   @Author : Alexandre Caillot
	 *   @WebSite : https://www.shiros.fr
	 *
	 *   @File : HomeController.php
	 *   @Created_at : 10/03/2018
	 *   @Update_at : 10/03/2018
	 * --------------------------------------------------------------------------
	 */

	namespace App\Controller;
	
	
	class HomeController extends AppController
	{
		/* ------------------------ Route ------------------------ */
			
			/**
			 * Homepage
			 */
			public function index()
			{
				$pages = [
					'Account' => [
						'NAME' => 'Comptes',
						'URL'  => 'Account'
					],
					'Approval' => [
						'NAME' => 'Approbation de crédit',
						'URL'  => 'Approval'
					],
					'Loan' => [
						'NAME' => 'Demande de crédit',
						'URL'  => 'Loan'
					],
				];
				
				$vars = compact('pages');
				$this->render('Home.index', $vars);
			}
	}
?>