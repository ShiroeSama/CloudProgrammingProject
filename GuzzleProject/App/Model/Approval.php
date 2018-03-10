<?php
	
	/**
	 * --------------------------------------------------------------------------
	 *   @Copyright : License MIT 2017
	 *
	 *   @Author : Alexandre Caillot
	 *   @WebSite : https://www.shiros.fr
	 *
	 *   @File : Approval.php
	 *   @Created_at : 10/03/2018
	 *   @Update_at : 10/03/2018
	 * --------------------------------------------------------------------------
	 */
	
	namespace App\Model;
	
	class Approval
	{
		/** @var int */
		protected $id;
		
		/** @var int */
		protected $date;
		
		/** @var Account */
		protected $account;
		
		/** @var Response */
		protected $response;
		
		/**
		 * @return int
		 */
		public function getId(): int
		{
			return $this->id;
		}
		
		/**
		 * @param int $id
		 */
		public function setId(int $id): void
		{
			$this->id = $id;
		}
		
		/**
		 * @return string
		 */
		public function getDate(): string
		{
			$date = new \DateTime();
			$date->setTimestamp($this->date);
			
			return $date->format('d/m/Y');
		}
		
		/**
		 * @param int $date
		 */
		public function setDate(int $date): void
		{
			$this->date = $date;
		}
		
		/**
		 * @return Account
		 */
		public function getAccount(): Account
		{
			return $this->account;
		}
		
		/**
		 * @param Account $account
		 */
		public function setAccount(Account $account): void
		{
			$this->account = $account;
		}
		
		/**
		 * @return Response
		 */
		public function getResponse(): Response
		{
			return $this->response;
		}
		
		/**
		 * @param Response $response
		 */
		public function setResponse(Response $response): void
		{
			$this->response = $response;
		}
	}
?>