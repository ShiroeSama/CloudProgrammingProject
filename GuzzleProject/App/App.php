<?php
	
	/**
	 * --------------------------------------------------------------------------
	 *   @Copyright : License MIT 2017
	 *
	 *   @Author : Alexandre Caillot
	 *   @WebSite : https://www.shiros.fr
	 *
	 *   @File : App.php
	 *   @Created_at : 24/11/2016
	 *   @Update_at : 03/12/2017
	 * --------------------------------------------------------------------------
	 */
	
	use ShirOSBundle\ApplicationKernel;

	class App extends ApplicationKernel
	{
		/* ------------------------ Autoload ------------------------ */

			/**
			 * Fonction permetant d'appeler les Autoloaders pour charger tout nos fichiers
			 */
			public static function load()
			{
				require ROOT . '/App/Autoloader.php';
				App\Autoloader::register();

				require ROOT . '/Core/Core.php';
				Core\Core::register();

				ShirOSBundle\Utils\Session\Session::getInstance()->initSession();
			}
	}
?>