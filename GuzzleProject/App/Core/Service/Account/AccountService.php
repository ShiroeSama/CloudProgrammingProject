<?php
	
	/**
	 * --------------------------------------------------------------------------
	 *   @Copyright : License MIT 2017
	 *
	 *   @Author : Alexandre Caillot
	 *   @WebSite : https://www.shiros.fr
	 *
	 *   @File : AccountService.php
	 *   @Created_at : 10/03/2018
	 *   @Update_at : 10/03/2018
	 * --------------------------------------------------------------------------
	 */
	
	namespace App\Core\Service\Account;
	
	use App\Core\Service\AppService;
	use App\Model\Account;
	use App\Model\Risk;
	use ShirOSBundle\Utils\Exception\ShirOSException;
	
	class AccountService extends AppService
	{
		/**
		 * @param array $body
		 * @return array
		 * @throws ShirOSException
		 */
		public function responseProcessing(array $body): array
		{
			$accounts = [];
			
			foreach ($body as $element) {
				try {
					$account = $this->createAccount($element);
					array_push($accounts, $account);
				} catch (ShirOSException $shirOSException) {
					throw new ShirOSException("Clé manquant pour l'objet {$objectId} dans le flux /api/accounts", 500, $shirOSException);
				}
			}
			
			return $accounts;
		}
		
		/**
		 * @param array $body
		 * @return array
		 * @throws ShirOSException
		 */
		public function responseProcessingForLoan(array $body): array
		{
			$accounts = [];
			
			$idField = $this->ConfigModule->get('Fields.Name.Id');
			$accountNameField = $this->ConfigModule->get('Fields.Name.AccountName');
			
			foreach ($body as $element) {
				if (property_exists($element, $idField)
					&& property_exists($element, $accountNameField)
				){
					$accounts[$element->$idField] = $element->$accountNameField;
				} else {
					throw new ShirOSException();
				}
			}
			
			return $accounts;
		}
		
		/**
		 * @param $account
		 * @return Account
		 * @throws ShirOSException
		 */
		public function createAccount($account): Account
		{
			$idField = $this->ConfigModule->get('Fields.Name.Id');
			$accountNameField = $this->ConfigModule->get('Fields.Name.AccountName');
			$lastnameField = $this->ConfigModule->get('Fields.Name.Lastname');
			$firstnameField = $this->ConfigModule->get('Fields.Name.Firstname');
			$amountField = $this->ConfigModule->get('Fields.Name.Amount');
			$riskField = $this->ConfigModule->get('Fields.Name.Risk');
			$riskValueField = $this->ConfigModule->get('Fields.Name.RiskValue');
			
			if (property_exists($account, $idField)
				&& property_exists($account, $accountNameField)
				&& property_exists($account, $lastnameField)
				&& property_exists($account, $firstnameField)
				&& property_exists($account, $amountField)
				&& property_exists($account, $riskField)
			){
				$risk = $account->$riskField;
				
				if (property_exists($risk, $idField)
					&& property_exists($account, $riskValueField)
				){
					$accountModel = new Account();
					$riskModel = new Risk();
					
					$riskModel->setId($risk->$idField);
					$riskModel->setName($risk->$riskValueField);
					
					$accountModel->setId($account->$idField);
					$accountModel->setName($account->$accountNameField);
					$accountModel->setLastname($account->$lastnameField);
					$accountModel->setFirstname($account->$firstnameField);
					$accountModel->setAmount($account->$amountField);
					$accountModel->setRisk($riskModel);
					
					return $accountModel;
				}
			}
			throw new ShirOSException();
		}
	}
?>