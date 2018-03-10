<!DOCTYPE html>
<html lang="fr">

<!-- Debut En-tête -->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <?php if (!empty($MetaDataModule->getTitle())): ?>
            <meta name="title" content="<?= $MetaDataModule->getTitle(); ?>">
        <?php endif ?>
        
        <?php if (!empty($MetaDataModule->getDescription())): ?>
            <meta name="description" content="<?= $MetaDataModule->getDescription(); ?>">
        <?php endif ?>
        
        <?php if (!empty($MetaDataModule->getKeywords())): ?>
            <meta name="keywords" content="<?= $MetaDataModule->getKeywords(); ?>">
        <?php endif ?>
    
        <meta name="author" content="<?= $ConfigModule->get("Server.Author"); ?>">
        <meta name="generator" content="<?= $ConfigModule->get("Server.Author"); ?>">
        
        <?php if (empty($MetaDataModule->getTitle())): ?>
            <title><?= $ConfigModule->get("Server.Name"); ?></title>
        <?php else: ?>
            <title><?= $MetaDataModule->getTitle() . ' - ' . $ConfigModule->get("Server.Name"); ?></title>
        <?php endif ?>
    
        <!-- Css -->
            <?= App::getInstance()->getCss("public/css"); ?>
    </head>
<!-- END En-tête -->

    <body>
        <header id="Header">
            <div id="Menu">
                <nav>
                    <a href="<?= $UrlModule->getUrl() ?>"><span class="glyphicon glyphicon-globe"></span><?= $ConfigModule->get("Server.Name"); ?></a>
                    <a href="<?= $UrlModule->getUrl('Account') ?>">Comptes</a>
                    <a href="<?= $UrlModule->getUrl('Approval') ?>">Approbation de crédit</a>
                    <a href="<?= $UrlModule->getUrl('Loan') ?>">Demande de crédit</a>
                    
                    <a href="javascript:void(0);" id="IconMenu" onclick="menu()"><span class="glyphicon glyphicon-menu-hamburger"></span></a>
                </nav>
            </div>
        </header>
        
        <!-- Corps de Site -->
            <div id="Page" class="p-0">
                <div class="container">