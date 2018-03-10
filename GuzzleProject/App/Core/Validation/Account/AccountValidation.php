<?php
	
	/**
	 * --------------------------------------------------------------------------
	 *   @Copyright : License MIT 2017
	 *
	 *   @Author : Alexandre Caillot
	 *   @WebSite : https://www.shiros.fr
	 *
	 *   @File : AccountValidation.php
	 *   @Created_at : 10/03/2018
	 *   @Update_at : 10/03/2018
	 * --------------------------------------------------------------------------
	 */
	
	namespace App\Core\Validation\Account;
	
	
	use App\Core\Validation\AppValidation;
	use ShirOSBundle\Utils\Validation\Type\DoubleType;
	use ShirOSBundle\Utils\Validation\ValidationBuilder;
	use ShirOSBundle\Utils\Validation\Type\StringType;
	
	class AccountValidation extends AppValidation
	{
		protected function buildValidation(ValidationBuilder $builder)
		{
			$builder
				->add(StringType::type(), $this->ConfigModule->get('Fields.Name.AccountName'))
				->add(StringType::type(), $this->ConfigModule->get('Fields.Name.Lastname'))
				->add(StringType::type(), $this->ConfigModule->get('Fields.Name.Firstname'))
				->add(DoubleType::type(), $this->ConfigModule->get('Fields.Name.Amount'))
			;
		}
	}
	?>