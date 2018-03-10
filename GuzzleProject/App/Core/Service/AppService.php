<?php
	
	/**
	 * --------------------------------------------------------------------------
	 *   @Copyright : License MIT 2017
	 *
	 *   @Author : Alexandre Caillot
	 *   @WebSite : https://www.shiros.fr
	 *
	 *   @File : AppService.php
	 *   @Created_at : 10/03/2018
	 *   @Update_at : 10/03/2018
	 * --------------------------------------------------------------------------
	 */
	
	namespace App\Core\Service;
	
	use \App;
	
	use ShirOSBundle\Services\Service;
	
	class AppService extends Service
	{
		/**
		 * Instance de la Classe de gestion de l'application
		 * @var App
		 */
		protected $ApplicationModule;
		
		/**
		 * AppService constructor.
		 */
		public function __construct()
		{
			parent::__construct();
			$this->ApplicationModule = App::getInstance();
		}
	}
	?>