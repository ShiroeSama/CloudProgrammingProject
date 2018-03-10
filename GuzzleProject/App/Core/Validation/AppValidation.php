<?php
	
	/**
	 * --------------------------------------------------------------------------
	 *   @Copyright : License MIT 2017
	 *
	 *   @Author : Alexandre Caillot
	 *   @WebSite : https://www.shiros.fr
	 *
	 *   @File : AppValidation.php
	 *   @Created_at : 10/03/2018
	 *   @Update_at : 10/03/2018
	 * --------------------------------------------------------------------------
	 */
	
	namespace App\Core\Validation;
	
	use ShirOSBundle\Utils\Validation\Validation;
	
	
	/**
	 * Controller des Validations de Champs
	 */
	
	class AppValidation extends Validation
	{
		/** DEFAULT ERROR MESSAGE */
		
		protected const ERROR_MESSAGE_EMPTY = "Le champ doit être renseigné";
	}
	?>