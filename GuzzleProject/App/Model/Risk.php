<?php
	
	/**
	 * --------------------------------------------------------------------------
	 *   @Copyright : License MIT 2017
	 *
	 *   @Author : Alexandre Caillot
	 *   @WebSite : https://www.shiros.fr
	 *
	 *   @File : Risk.php
	 *   @Created_at : 10/03/2018
	 *   @Update_at : 10/03/2018
	 * --------------------------------------------------------------------------
	 */
	
	namespace App\Model;
	
	class Risk
	{
		/** @var int */
		protected $id;
		
		/** @var string */
		protected $name;
		
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
	}
	?>