<?php
	
	/**
	 * --------------------------------------------------------------------------
	 *   @Copyright : License MIT 2017
	 *
	 *   @Author : Alexandre Caillot
	 *   @WebSite : https://www.shiros.fr
	 *
	 *   @File : Account.php
	 *   @Created_at : 10/03/2018
	 *   @Update_at : 10/03/2018
	 * --------------------------------------------------------------------------
	 */
	
	namespace App\Model;
	
	class Account
	{
		/** @var int */
		protected $id;
		
		/** @var string */
		protected $name;
		
		/** @var string */
		protected $lastname;
		
		/** @var string */
		protected $firstname;
		
		/** @var double */
		protected $amount;
		
		/** @var Risk */
		protected $risk;
		
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
		public function getName(): string
		{
			return $this->name;
		}
		
		/**
		 * @param string $name
		 */
		public function setName(string $name): void
		{
			$this->name = $name;
		}
		
		/**
		 * @return string
		 */
		public function getLastname(): string
		{
			return $this->lastname;
		}
		
		/**
		 * @param string $lastname
		 */
		public function setLastname(string $lastname): void
		{
			$this->lastname = $lastname;
		}
		
		/**
		 * @return string
		 */
		public function getFirstname(): string
		{
			return $this->firstname;
		}
		
		/**
		 * @param string $firstname
		 */
		public function setFirstname(string $firstname): void
		{
			$this->firstname = $firstname;
		}
		
		/**
		 * @return float
		 */
		public function getAmount(): float
		{
			return $this->amount;
		}
		
		/**
		 * @param float $amount
		 */
		public function setAmount(float $amount): void
		{
			$this->amount = $amount;
		}
		
		/**
		 * @return Risk
		 */
		public function getRisk(): Risk
		{
			return $this->risk;
		}
		
		/**
		 * @param Risk $risk
		 */
		public function setRisk(Risk $risk): void
		{
			$this->risk = $risk;
		}
	}
?>