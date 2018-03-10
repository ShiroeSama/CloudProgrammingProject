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
			
			foreach ($body as $objectId => $element) {
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
			
			foreach ($body as $objectId => $element) {
				if (isset($element[$this->ConfigModule->get('Fields.Name.Id')])
					&& isset($element[$this->ConfigModule->get('Fields.Name.AccountName')])
				) {
					$accounts[$element[$this->ConfigModule->get('Fields.Name.Id')]] = $element[$this->ConfigModule->get('Fields.Name.AccountName')];
				} else {
					throw new ShirOSException();
				}
			}
			
			return $accounts;
		}
		
		/**
		 * @param array $account
		 * @return Account
		 * @throws ShirOSException
		 */
		public function createAccount(array $account): Account
		{
			if (isset($account[$this->ConfigModule->get('Fields.Name.Id')])
				&& isset($account[$this->ConfigModule->get('Fields.Name.AccountName')])
				&& isset($account[$this->ConfigModule->get('Fields.Name.Lastname')])
				&& isset($account[$this->ConfigModule->get('Fields.Name.Firstname')])
				&& isset($account[$this->ConfigModule->get('Fields.Name.Amount')])
				&& isset($account[$this->ConfigModule->get('Fields.Name.Risk')])
				&& isset($account[$this->ConfigModule->get('Fields.Name.Risk')][$this->ConfigModule->get('Fields.Name.Id')])
				&& isset($account[$this->ConfigModule->get('Fields.Name.Risk')][$this->ConfigModule->get('Fields.Name.RiskValue')])
			) {
				$accountModel = new Account();
				$risk = new Risk();
				
				$risk->setId($account[$this->ConfigModule->get('Fields.Name.Risk')][$this->ConfigModule->get('Fields.Name.Id')]);
				$risk->setName($account[$this->ConfigModule->get('Fields.Name.Risk')][$this->ConfigModule->get('Fields.Name.RiskValue')]);
				
				$accountModel->setId($account[$this->ConfigModule->get('Fields.Name.Id')]);
				$accountModel->setName($account[$this->ConfigModule->get('Fields.Name.AccountName')]);
				$accountModel->setLastname($account[$this->ConfigModule->get('Fields.Name.Lastname')]);
				$accountModel->setFirstname($account[$this->ConfigModule->get('Fields.Name.Firstname')]);
				$accountModel->setAmount($account[$this->ConfigModule->get('Fields.Name.Amount')]);
				$accountModel->setRisk($risk);
				
				return $accountModel;
			} else {
				throw new ShirOSException();
			}
		}
	}
?>